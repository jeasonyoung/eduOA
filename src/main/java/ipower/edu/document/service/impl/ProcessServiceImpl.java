package ipower.edu.document.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import ipower.edu.document.dao.IProcessDao;
import ipower.edu.document.domain.Parameter;
import ipower.edu.document.domain.ParameterMapping;
import ipower.edu.document.domain.Process;
import ipower.edu.document.domain.Step;
import ipower.edu.document.domain.Transition;
import ipower.edu.document.pageModel.ProcessInfo;
import ipower.edu.document.service.IProcessService;

/**
 * 流程服务实现类。
 * @author 杨勇。
 * @since 2013-12-14。
 * */
public class ProcessServiceImpl extends DataServiceBaseImpl<Process,ProcessInfo> implements IProcessService {
	private static Logger logger = Logger.getLogger(ProcessServiceImpl.class);
	private IProcessDao processDao;
	/**
	 * 设置流程数据访问。
	 * @param processDao
	 * 	数据访问接口。
	 * */
	@Override
	public void setProcessDao(IProcessDao processDao) {
		this.processDao = processDao;
	}
	
	@Override
	protected List<Process> find(ProcessInfo info) {
		String hql = "from Process p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql += "  order by p."+ info.getSort() +" "+ info.getOrder();
		}
		return this.processDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected ProcessInfo changeModel(Process data) {
		ProcessInfo info = new ProcessInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}

	@Override
	protected Long total(ProcessInfo info) {
		String hql = "select count(*) from Process p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.processDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(ProcessInfo info, String hql, Map<String, Object> parameters) {
		if(info.getName() != null && !info.getName().trim().isEmpty()){
			hql += " and p.name like :name ";
			parameters.put("name", "%"+ info.getName() +"%");
		}
		return hql;
	}

	@Override
	public ProcessInfo update(ProcessInfo info) {
		if(info != null){
			boolean isAdded = false;
			Process data = (info.getId() == null || info.getId().trim().isEmpty()) ?  null : this.processDao.load(Process.class,info.getId());
			if(isAdded = (data == null)){
				info.setId(UUID.randomUUID().toString());
				data = new Process();
			}
			BeanUtils.copyProperties(info, data);
			if(isAdded) this.processDao.save(data);			
		}
		return info;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0)return;
		for(int i = 0; i < ids.length; i++){
			Process process = this.processDao.load(Process.class,ids[i]);
			if(process != null) this.processDao.delete(process);
		}
	}

	@Override
	public Process loadProcess(String processId) {
		return this.processDao.load(Process.class, processId);
	}

	@Override
	public List<Process> allProcesses() {
		return this.processDao.find("from Process p", null, null, null);
	}

	@Override
	public void initProcesses() throws Exception {
		//公文收发。
		this.initReceiveDocumentProcess();
	}
	
	private void initReceiveDocumentProcess() throws Exception{
		logger.info("开始初始化公文收发流程...");
		String processId = "40288a8c42f6070a0142f60816840000";
		Process process = this.loadProcess(processId);
		boolean isAdded = false;
		if(process == null){
			isAdded = true;
			process = new Process();
			process.setId(processId);
		}
		process.setName("公文收发流程");
		process.setStatus(1);
		process.setDescription("主要负责接收公文的流程。");
		if(process.getTransitions() == null) process.setTransitions(new HashSet<Transition>());
		//1.接收公文
		Step step1 = this.createStep(process, "1.接收公文", "org.edu.doc.receive.first", 1, Step.StartStepTypeValue, "", "第一步接收外来公文。");
		Parameter instanceIdStep1Parameter = this.createParameter(step1, "instanceId", "", "流程实例ID"),
				  documentIdStep1Parameter = this.createParameter(step1, "documentId", "", "公文ID");
		//2.分送意见
		Step step2 = this.createStep(process, "2.分送意见","org.edu.doc.receive.second", 2, Step.MiddleStepTypeValue, "", "第二步分送意见。");
		Parameter instanceIdStep2Parameter = this.createParameter(step2, "instanceId", "", "流程实例ID"),
				  documentIdStep2Parameter = this.createParameter(step2, "documentId", "", "公文ID");
		this.createParameter(step2, "approval", "", "审批值");
		//3.转办意见
		Step step3 = this.createStep(process,"3.转办意见", "org.edu.doc.receive.third",3, Step.MiddleStepTypeValue, "", "第三步转办意见。");
		Parameter instanceIdStep3Parameter = this.createParameter(step3, "instanceId", "", "流程实例ID"),
				  documentIdStep3Parameter = this.createParameter(step3, "documentId", "", "公文ID");
		this.createParameter(step3, "approval", "", "审批值");
		//4.拟办意见
		Step step4 = this.createStep(process, "4.拟办意见","org.edu.doc.receive.fourth", 4, Step.MiddleStepTypeValue, "", "第四步拟办意见。");
		Parameter instanceIdStep4Parameter = this.createParameter(step4, "instanceId", "", "流程实例ID"),
				  documentIdStep4Parameter = this.createParameter(step4, "documentId", "", "公文ID");
		this.createParameter(step4, "approval", "", "审批值");
		//5. 领导批示
		Step step5 = this.createStep(process, "5.领导批示","org.edu.doc.receive.fifth", 5, Step.MiddleStepTypeValue, "", "第五步领导批示。");	
		Parameter instanceIdStep5Parameter = this.createParameter(step5, "instanceId", "", "流程实例ID"),
				  documentIdStep5Parameter = this.createParameter(step5, "documentId", "", "公文ID");
		this.createParameter(step5, "approval", "", "审批值");
		//6.办理记录
		Step step6 = this.createStep(process, "6.办理记录","org.edu.doc.receive.sixth", 6, Step.MiddleStepTypeValue, "", "第六步办理记录。");
		Parameter instanceIdStep6Parameter = this.createParameter(step6, "instanceId", "", "流程实例ID"),
				  documentIdStep6Parameter = this.createParameter(step6, "documentId", "", "公文ID");
		//7.结束步骤。
		Step step7 = this.createStep(process, "7.结束步骤","org.edu.doc.receive.seventh", 7, Step.EndStepTypeValue, "", "第七步结束步骤。");
		Parameter instanceIdStep7Parameter = this.createParameter(step7, "instanceId", "", "流程实例ID"),
				  documentIdStep7Parameter = this.createParameter(step7, "documentId", "", "公文ID");
		
		//1.接收公文 => 2.分送意见
		this.createTransition(process, "1.接收公文 => 2.分送意见", step1, step2,
				new Parameter[]{instanceIdStep1Parameter,documentIdStep1Parameter}, new Parameter[]{instanceIdStep2Parameter,documentIdStep2Parameter});
		
		//2.分送意见 => 3.转办意见
		this.createTransition(process, "2.分送意见 => 3.转办意见", step2, step3, 
				new Parameter[]{instanceIdStep2Parameter,documentIdStep2Parameter}, new Parameter[]{instanceIdStep3Parameter,documentIdStep3Parameter});
		//2.分送意见 =>1.接收公文
		this.createTransition(process, "2.分送意见 => 1.接收公文", step2, step1, 
				new Parameter[]{instanceIdStep2Parameter,documentIdStep2Parameter}, new Parameter[]{instanceIdStep1Parameter,documentIdStep1Parameter});
		
		//3.转办意见 => 4.拟办意见
		this.createTransition(process, "3.转办意见 => 4.拟办意见", step3, step4, 
				new Parameter[]{instanceIdStep3Parameter,documentIdStep3Parameter}, new Parameter[]{instanceIdStep4Parameter,documentIdStep4Parameter});
		//3.转办意见 => 1.接收公文
		this.createTransition(process, "3.转办意见 => 1.接收公文", step3, step1, 
				new Parameter[]{instanceIdStep3Parameter,documentIdStep3Parameter}, new Parameter[]{instanceIdStep1Parameter,documentIdStep1Parameter});
				
		//4.拟办意见 => 5.领导批示
		this.createTransition(process, "4.拟办意见 => 5.领导批示", step4, step5, 
				new Parameter[]{instanceIdStep4Parameter,documentIdStep4Parameter}, new Parameter[]{instanceIdStep5Parameter,documentIdStep5Parameter});
		//4.拟办意见 => 1.接收公文
		/*this.createTransition(process, "4.拟办意见 => 1.接收公文", step4, step1, 
				new Parameter[]{instanceIdStep4Parameter,documentIdStep4Parameter}, new Parameter[]{instanceIdStep1Parameter,documentIdStep1Parameter});
		*/
		//5.领导批示 => 6.办理记录
		this.createTransition(process, "5.领导批示 => 6.办理记录", step5, step6, 
				new Parameter[]{instanceIdStep5Parameter,documentIdStep5Parameter}, new Parameter[]{instanceIdStep6Parameter,documentIdStep6Parameter}); 
		//5.领导批示 => 1.接收公文
		this.createTransition(process, "5.领导批示 => 1.接收公文", step5, step1, 
				new Parameter[]{instanceIdStep5Parameter,documentIdStep5Parameter}, new Parameter[]{instanceIdStep1Parameter,documentIdStep1Parameter}); 
		
		//6.办理记录 => 7.结束步骤
		this.createTransition(process, "6.办理记录 => 7.结束步骤", step6, step7, 
				new Parameter[]{instanceIdStep6Parameter,documentIdStep6Parameter}, new Parameter[]{instanceIdStep7Parameter,documentIdStep7Parameter});
		//添加流程。
		if(isAdded)this.processDao.save(process);
	}
	
	private Step createStep(Process process,String stepName,String stepSign,Integer order, Integer type, String url,String description){
		if(process == null) return null;
		if(process.getSteps() == null) process.setSteps(new HashSet<Step>());
		Step step = null;
		for(Step s : process.getSteps()){
			if(s != null && s.getName().equalsIgnoreCase(stepName)){
				step = s;
				break;
			}
		}
		boolean isAdded = false;
		if(step == null){
			isAdded = true;
			step = new Step();
			step.setId(UUID.randomUUID().toString());
			step.setName(stepName);
			step.setProcess(process);
		}
		step.setSign(stepSign);
		step.setOrderNo(order);
		step.setType(type);
		step.setDescription(description);
		step.setUrl(url);
		
		if(isAdded)process.getSteps().add(step);
		return step;
	}
	private Parameter createParameter(Step step,String parameterName, String defaultValue,String description){
		if(step == null)return null;
		if(step.getParameters() == null)step.setParameters(new HashSet<Parameter>());
		Parameter parameter = null;
		for(Parameter p : step.getParameters()){
			if(p != null && p.getName().equalsIgnoreCase(parameterName)){
				parameter = p;
				break;
			}
		}
		boolean isAdded = false;
		if(parameter == null){
			isAdded = true;
			parameter = new Parameter();
			parameter.setId(UUID.randomUUID().toString());
			parameter.setName(parameterName);
			parameter.setStep(step);
		}
		parameter.setValue(defaultValue);
		parameter.setDescription(description);
		if(isAdded)step.getParameters().add(parameter);
		return parameter;
	}
	private void createTransition(Process process,String transitionName,Step from,Step to, Parameter[] fromParameters,  Parameter[]toParameters){
		if(process == null) return;
		if(process.getTransitions() == null) process.setTransitions(new HashSet<Transition>());
		Transition transition = null;
		for(Transition t : process.getTransitions()){
			if(t != null && t.getName().equalsIgnoreCase(transitionName)){
				transition = t;
				break;
			}
		}
		boolean isAdded = false;
		if(transition == null){
			isAdded = true;
			transition = new Transition();
			transition.setId(UUID.randomUUID().toString());
			transition.setName(transitionName);
			transition.setProcess(process);
		}
		transition.setFrom(from);
		transition.setTo(to);
		transition.setRule(Transition.RULE_Union_RUN);
		if(transition.getMappings() == null)transition.setMappings(new HashSet<ParameterMapping>());
		if(fromParameters != null && fromParameters.length > 0 && toParameters != null && toParameters.length > 0 && fromParameters.length >= toParameters.length){
			for(int i = 0; i < fromParameters.length; i++){
				ParameterMapping pm = new ParameterMapping(transition, fromParameters[i], toParameters[i]);
				boolean isMappingAdded = true;
				for(ParameterMapping mapping : transition.getMappings()){
					if(mapping != null && mapping.getFrom().getId() == pm.getFrom().getId() && mapping.getTo().getId() == pm.getTo().getId()){
						isMappingAdded = false;
						break;
					}
				}
				if(isMappingAdded)transition.getMappings().add(pm);
			}
		}
		if(isAdded)process.getTransitions().add(transition);
	}
}