package ipower.edu.document.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import ipower.edu.document.dao.IProcessInstanceDao; 
import ipower.edu.document.dao.IStepTaskInstanceDao;
import ipower.edu.document.domain.Condition;
import ipower.edu.document.domain.Parameter;
import ipower.edu.document.domain.ParameterMapping;
import ipower.edu.document.domain.Process;
import ipower.edu.document.domain.ProcessInstance;
import ipower.edu.document.domain.Step;
import ipower.edu.document.domain.StepTaskInstance;
import ipower.edu.document.domain.StepUser;
import ipower.edu.document.domain.Transition;
import ipower.edu.document.pageModel.ProcessInstanceInfo;
import ipower.edu.document.service.IProcessInstanceService;
import ipower.edu.document.service.IProcessService;
import ipower.edu.document.service.IStepService;

/**
 * 流程实例服务实现类。
 * @author 杨勇。
 * @since 2013-12-19。
 * */
public class ProcessInstanceServiceImpl extends DataServiceBaseImpl<ProcessInstance,ProcessInstanceInfo> implements IProcessInstanceService {
	private static Logger logger = Logger.getLogger(ProcessInstanceServiceImpl.class);
	private IProcessInstanceDao processInstanceDao;
	private IStepTaskInstanceDao stepTaskInstanceDao;
	private IProcessService processService;
	private IStepService stepService;
	
	@Override
	public void setProcessInstanceDao(IProcessInstanceDao processInstanceDao) {
		this.processInstanceDao = processInstanceDao;
	}
	
	@Override
	public void setStepTaskInstanceDao(IStepTaskInstanceDao stepTaskInstanceDao) {
		this.stepTaskInstanceDao = stepTaskInstanceDao;
	}

	@Override
	public void setProcessService(IProcessService processService) {
		this.processService = processService;
	}
	
	@Override
	public void setStepService(IStepService stepService) {
		this.stepService = stepService;
	}

	@Override
	protected List<ProcessInstance> find(ProcessInstanceInfo info) {
		String hql = "from ProcessInstance p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			if(info.getSort().equalsIgnoreCase("processName")){
				info.setSort("process.name");
			}
			hql += " order by p."+ info.getSort() +" "+ info.getOrder();
		}
		return this.processInstanceDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected ProcessInstanceInfo changeModel(ProcessInstance data) {
		ProcessInstanceInfo info = new ProcessInstanceInfo();
		if(data != null){
			BeanUtils.copyProperties(data, info);
			if(data.getProcess() != null){
				info.setProcessId(data.getProcess().getId());
				info.setProcessName(data.getProcess().getName());
			}
		}
		return info;
	}

	@Override
	protected Long total(ProcessInstanceInfo info) {
		String hql = "select count(*) from ProcessInstance p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.processInstanceDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(ProcessInstanceInfo info, String hql, Map<String, Object> parameters) {
		if(info.getProcessId() != null && !info.getProcessId().trim().isEmpty()){
			hql += " and p.process.id = :processId";
			parameters.put("processId", info.getProcessId());
		}
		if(info.getProcessName() != null && !info.getProcessName().trim().isEmpty()){
			hql += " and p.process.name like :processName";
			parameters.put("processName", "%"+info.getProcessName()+"%");
		}
		if(info.getName() != null && !info.getName().trim().isEmpty()){
			hql += " and p.name like :name";
			parameters.put("name", "%"+info.getName()+"%");
		}
		if(info.getStatus() != null){
			hql += " and p.status = :status ";
			parameters.put("status", info.getStatus());
		}
		return hql;
	}

	@Override
	public ProcessInstanceInfo update(ProcessInstanceInfo info) {
		if(info != null){
			boolean isAdded = false;
			ProcessInstance data = (info.getId() == null || info.getId().trim().isEmpty()) ? null : this.processInstanceDao.load(ProcessInstance.class, info.getId());
			if(isAdded = (data == null)){
				data = new ProcessInstance();
				info.setId(UUID.randomUUID().toString());
			}
			BeanUtils.copyProperties(info, data);
			if(info.getProcessId() != null && (data.getProcess() == null || data.getProcess().getId().equalsIgnoreCase(info.getId()))){
				Process process = this.processService.loadProcess(info.getProcessId());
				if(process != null){
					data.setProcess(process);
					info.setProcessName(process.getName());
				}
			}
			if(isAdded)this.processInstanceDao.save(data);
		}
		return info;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0)return;
		for(int i = 0; i < ids.length; i++){
			ProcessInstance data = this.processInstanceDao.load(ProcessInstance.class, ids[i]);
			if(data != null) this.processInstanceDao.delete(data);
		}
	}
	/**
	 * 流程实例初始化。
	 * @param processId
	 * 	流程ID。
	 * @param instanceName
	 * 	流程实例名。
	 * @param remarks
	 * 	流程实例备注。
	 * @return 流程实例ID。
	 * */
	@Override
	public synchronized String initFlow(String processId, String instanceName, String remarks) throws Exception {
		String err =  null;
		Process process = this.processService.loadProcess(processId);
		if(process == null){
			logger.info(err = "[processId:"+ processId +"]未找到流程对象！");
			throw new Exception(err);
		}
		ProcessInstance instance = new ProcessInstance();
		instance.setId(UUID.randomUUID().toString());
		instance.setName(instanceName);
		instance.setBegin(new Date());
		instance.setProcess(process);
		instance.setRemarks(remarks);
		instance.setStatus(ProcessInstance.CONST_STATUS_RUN);
		this.processInstanceDao.save(instance);
		return instance.getId();
	}
	/**
	 * 提交任务。
	 * @param stepSign
	 * 当前流程步骤标示。
	 * @param instanceId
	 * 	流程实例ID。
	 * @param taskId
	 * 当前流程步骤任务ID。
	 * @param userId
	 * 	当前审批用户ID。
	 * @param userName
	 * 	当前审批用户名。
	 * @param approvalViews
	 * 	审批意见。
	 * @param parameters
	 * 	参数集合。
	 * @param toPendingUsers
	 * 	待办用户集合。
	 * @param toBereadUsers
	 *  待阅用户集合。
	 *  @return
	 *  反馈结果，true-提交成功，false-提交失败。
	 * */
	@Override
	public synchronized boolean CommitFlow(String stepSign, String instanceId, String taskId, String userId, String userName, String approvalViews, 
			Map<String, Object> parameters, Map<String, String> toPendingUsers, Map<String, String> toBereadUsers) throws Exception {
		String err = null;
		if(stepSign == null || stepSign.trim().isEmpty()){
			logger.info(err = "当前流程步骤标示[stepSign]为空！");
			throw new Exception(err);
		}
		if(instanceId == null || instanceId.trim().isEmpty()){
			logger.info(err = "当前流程步骤标示[instanceId]为空！");
			throw new Exception(err);
		}
		Step step = this.stepService.loadStepSign(stepSign);
		if(step == null){
			logger.info(err = "[stepSign:"+stepSign+"]未能加载步骤对象！");
			throw new Exception(err);
		}
		ProcessInstance processInstance = this.processInstanceDao.load(ProcessInstance.class, instanceId);
		if(processInstance == null){
			logger.info(err = "[instanceId:"+instanceId+"]未能加载流程实例对象！");
			throw new Exception(err);
		}
		if(processInstance.getStatus() == ProcessInstance.CONST_STATUS_END){
			logger.info(err = "该流程已经结束！");
			throw new Exception(err);
		}
		if(processInstance.getStepTaskInstances() == null){
			processInstance.setStepTaskInstances(new HashSet<StepTaskInstance>());
		}
		StepTaskInstance stepTaskInstance = null;
		if(taskId == null || taskId.trim().isEmpty()){
			if(step.getType() != Step.StartStepTypeValue){
				logger.info(err = "当前步骤不是开始步骤，请输入参数taskId值！");
				throw new Exception(err);
			}
			stepTaskInstance = new StepTaskInstance();
			stepTaskInstance.setId(UUID.randomUUID().toString());
			stepTaskInstance.setName("[" + step.getName() + "]开始步骤实例");
			stepTaskInstance.setData(approvalViews);
			stepTaskInstance.setProcessInstance(processInstance);
			stepTaskInstance.setStatus(StepTaskInstance.CONST_STATUS_COMPLETE);
			stepTaskInstance.setStep(step);
			stepTaskInstance.setTime(new Date());
			stepTaskInstance.setType(StepTaskInstance.CONST_TYPE_PENDING);
			stepTaskInstance.setUrl(step.getUrl());
			stepTaskInstance.setUserId(userId);
			stepTaskInstance.setUserName(userName);
			processInstance.getStepTaskInstances().add(stepTaskInstance);			
		}else{
			stepTaskInstance = this.stepTaskInstanceDao.load(StepTaskInstance.class, taskId);
			if(stepTaskInstance == null){
				logger.info(err = "[taskId:"+taskId+"]未能加载步骤任务实例对象！");
				throw new Exception(err);
			}
			ProcessInstance stepTaskInstance_processInstance = null;
			if((stepTaskInstance_processInstance = stepTaskInstance.getProcessInstance()) == null){
				logger.info(err = "[taskId:"+taskId+"]所属流程实例对象为空！");
				throw new Exception(err);
			}
			if(!stepTaskInstance_processInstance.getId().equalsIgnoreCase(instanceId)){
				logger.info(err = "[taskId:"+taskId+"]所属流程实例对象[instanceId:"+ stepTaskInstance_processInstance.getId() +"]与参数[instanceId:"+instanceId+"]不一致！");
				throw new Exception(err);
			}
			Step stepTaskInstance_step = null;
			if((stepTaskInstance_step = stepTaskInstance.getStep()) == null){
				logger.info(err = "[taskId:"+taskId+"]所属流程步骤对象为空！");
				throw new Exception(err);
			}
			if(!stepTaskInstance_step.getId().equalsIgnoreCase(step.getId())){
				logger.info(err = "[taskId:"+taskId+"]所属流程步骤对象[stepSign:"+ stepTaskInstance_step.getSign() +"]与参数[stepSign:"+stepSign+"]不一致！");
				throw new Exception(err);
			}
			if(stepTaskInstance.getStatus() == StepTaskInstance.CONST_STATUS_COMPLETE || stepTaskInstance.getStatus() == StepTaskInstance.CONST_STATUS_IGNORE){
				logger.info(err = "该步骤已经处理！");
				throw new Exception(err);
			}
			if(userId != null && !userId.trim().isEmpty() && userName != null && !userName.trim().isEmpty()){
				 stepTaskInstance.setUserId(userId);
				 stepTaskInstance.setUserName(userName);
			}
			stepTaskInstance.setData(approvalViews);
			//待阅。
			if(stepTaskInstance.getType() == StepTaskInstance.CONST_TYPE_BEREAD){
				stepTaskInstance.setStatus(StepTaskInstance.CONST_STATUS_COMPLETE);
				return true;
			}
			//终结步骤。
			if(step.getType() == Step.EndStepTypeValue){
				processInstance.setEnd(new Date());
				processInstance.setStatus(ProcessInstance.CONST_STATUS_END);
				stepTaskInstance.setStatus(StepTaskInstance.CONST_STATUS_COMPLETE);
				return true;
			}
		}
		//下一步骤处理。
		this.nextStepTaskInstance(processInstance, step, parameters, toPendingUsers, toBereadUsers);
		//步骤执行完毕。
		stepTaskInstance.setStatus(StepTaskInstance.CONST_STATUS_COMPLETE);	
		return true;
	}
	
	private void nextStepTaskInstance(ProcessInstance processInstance,Step step,Map<String,Object> parameters,Map<String, String> toPendingUsers, Map<String, String> toBereadUsers) throws Exception{
		String err = null;
		Set<Transition> transitions = processInstance.getProcess().getTransitions(); 
		if(transitions == null || transitions.size() == 0){
			logger.info(err = "未找到流程步骤变迁集合！");
			throw new Exception(err);
		}
		List<Transition> list = new ArrayList<>();
		for(Transition trans : transitions){
			if(trans.getFrom() != null && trans.getFrom().getId().equalsIgnoreCase(step.getId())){
				list.add(trans);
			}
		}
		if(list.size() == 0){
			logger.info(err = "未找到流程步骤[stepId:"+step.getId()+","+step.getSign()+"]变迁规则！");
			throw new Exception(err);
		}
		Transition transition = null;
		Step nextStep = null;
		for(int i = 0; i < list.size(); i++){
			if((nextStep = this.calculatedNextStep(list.get(i), parameters)) != null){
				transition = list.get(i);		
				break;
			}
		}
		if(transition == null || nextStep == null){
			logger.info(err = "未找到流程步骤[stepId:"+step.getId()+","+step.getSign()+"]满足变迁规则的变迁路径！");
			throw new Exception(err);
		}
		//
		String url = this.createUrlParameters(transition, step, nextStep, parameters);
		//待办
		if(toPendingUsers != null && toPendingUsers.size() > 0){
			for(String userId : toPendingUsers.keySet()){
				this.createStepTaskInstance(processInstance, nextStep, userId, toPendingUsers.get(userId), StepTaskInstance.CONST_TYPE_PENDING, url);
			}
		}else if(nextStep.getStepUsers() != null && nextStep.getStepUsers().size() > 0) {
			for(StepUser su : nextStep.getStepUsers()){
				this.createStepTaskInstance(processInstance, nextStep, su.getUserId(), su.getUserName(),
						su.getType() == StepTaskInstance.CONST_TYPE_PENDING ? StepTaskInstance.CONST_TYPE_PENDING : StepTaskInstance.CONST_TYPE_BEREAD, 
						url);
			}
		}else {
			logger.info(err = "未找到步骤[stepId:"+nextStep.getId()+",stepSign:"+nextStep.getSign()+"]的待办用户！");
			throw new Exception(err);
		}
		if(toBereadUsers != null && toBereadUsers.size() > 0){
			for(String userId : toBereadUsers.keySet()){
				this.createStepTaskInstance(processInstance, nextStep, userId, toBereadUsers.get(userId), StepTaskInstance.CONST_TYPE_BEREAD, url);
			}
		}	
	}
	private void createStepTaskInstance(ProcessInstance instance,Step step,String userId, String userName,Integer stepTaskInstanceType, String url){
		StepTaskInstance stepTaskInstance = new StepTaskInstance();
		stepTaskInstance.setId(UUID.randomUUID().toString());
		stepTaskInstance.setName("[" + step.getName() + "]实例");
		stepTaskInstance.setProcessInstance(instance);
		stepTaskInstance.setStatus(StepTaskInstance.CONST_STATUS_NO);
		stepTaskInstance.setTime(new Date());
		stepTaskInstance.setType(stepTaskInstanceType);
		stepTaskInstance.setUserId(userId);
		stepTaskInstance.setUserName(userName);
		stepTaskInstance.setUrl(url);
		instance.getStepTaskInstances().add(stepTaskInstance);
	}
	
	private String createUrlParameters(Transition transition,Step preStep, Step nextStep, Map<String, Object> parameters) throws Exception{
		StringBuilder urlsBuilder = new StringBuilder();
		if(parameters != null && parameters.size() > 0 && nextStep.getParameters() != null && nextStep.getParameters().size() > 0){
			Set<ParameterMapping> mappings = transition.getMappings();
			if(mappings == null || mappings.size() == 0){
				String err = "步骤[stepId:"+preStep.getId()+",stepSign:"+preStep.getSign()+",stepName:"+preStep.getName()+"]与步骤[stepId:"+nextStep.getId()+",stepSign:"+nextStep.getSign()+",stepName:"+nextStep.getName()+"]未进行参数映射！";
				logger.info(err);
				throw new Exception(err);
			}
			for(Parameter p : nextStep.getParameters()){
				for(ParameterMapping pm : mappings){
					if(pm.getTo().getId().equalsIgnoreCase(p.getId())){
						Object value = parameters.get(pm.getFrom().getName());
						String strValue = (value == null ? p.getValue() : value.toString());
						if(strValue != null && !strValue.trim().isEmpty()){
							urlsBuilder.append("&"+ p.getName() + "=" + strValue);
						}
						break;
					}
				}
			}
		}
		return nextStep.getUrl() + (urlsBuilder.length() > 0 ?  "?" + urlsBuilder.substring(1) : "");
	}
	private Step calculatedNextStep(Transition transition, Map<String,Object> parameters) throws Exception{
		if(transition.getConditions() == null || transition.getConditions().size() == 0) 
			return transition.getTo();
		if(parameters == null || parameters.size() == 0){
			String err = null;
			logger.info(err = "参数parameters为空，无法进行变迁条件计算！");
			throw new Exception(err);
		}
		if(transition.getRule() == Transition.RULE_Union_RUN){
			boolean result = false;
			for(Condition c: transition.getConditions()){
				Object v = parameters.get(c.getParameter().getName());
				if(v == null) v = c.getParameter().getValue();
				if(!(result = this.calculated(v.toString(), c.getCompareValue()))){
					break;
				}
			}
			if(result) return transition.getTo();
		}else {
			boolean result = false;
			for(Condition c: transition.getConditions()){
				Object v = parameters.get(c.getParameter().getName());
				if(v == null) v = c.getParameter().getValue();
				if(result = this.calculated(v.toString(), c.getCompareValue())){
					break;
				}
			}
			if(result) return transition.getTo();
		}
		return null;
	}
	private boolean calculated(String source,String compareValue){
		if(source == null || source.trim().isEmpty()) return false;
		if(compareValue == null || compareValue.trim().isEmpty()) return false;
		if(compareValue.startsWith(">=")){
			String cv = compareValue.substring(2);
			return Float.parseFloat(source) >= Float.parseFloat(cv);
		}else if(compareValue.startsWith("<=")){
			String cv = compareValue.substring(2);
			return Float.parseFloat(source) <= Float.parseFloat(cv);
		}else if(compareValue.startsWith("==")){
			String cv = compareValue.substring(2);
			return source == cv;
		}else if(compareValue.startsWith(">")){
			String cv = compareValue.substring(2);
			return Float.parseFloat(source) > Float.parseFloat(cv);
		}else if(compareValue.startsWith("<")){
			String cv = compareValue.substring(2);
			return Float.parseFloat(source) < Float.parseFloat(cv);
		}else {
			String cv = compareValue.substring(1);
			return source == cv;
		}
	}
}