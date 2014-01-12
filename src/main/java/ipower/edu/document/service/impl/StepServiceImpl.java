package ipower.edu.document.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.edu.document.dao.IStepDao;
import ipower.edu.document.domain.Process;
import ipower.edu.document.domain.Step;
import ipower.edu.document.pageModel.StepInfo;
import ipower.edu.document.service.IProcessService;
import ipower.edu.document.service.IStepService;

/**
 * 流程步骤服务实现类。
 * @author 杨勇。
 * @since 2013-12-15。
 * */
public class StepServiceImpl extends DataServiceBaseImpl<Step, StepInfo> implements IStepService {
	private IStepDao stepDao;
	private IProcessService processService;
	@Override
	public void setStepDao(IStepDao stepDao) {
		this.stepDao = stepDao;
	}
	@Override
	public void setProcessService(IProcessService processService) {
		this.processService = processService;
	}
	@Override
	protected List<Step> find(StepInfo info) {
		String hql = "from Step s where 1 = 1";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		hql += " order by s.process.name,";
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql += " s."+ info.getSort() +" "+ info.getOrder() + ",";
		}
		hql += " s.orderNo asc";
		return this.stepDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected StepInfo changeModel(Step data) {
		StepInfo info = new StepInfo();
		BeanUtils.copyProperties(data, info);
		Process process = null;
		if((process = data.getProcess()) != null){
			info.setProcessId(process.getId());
			info.setProcessName(process.getName());
		}
		return info;
	}

	@Override
	protected Long total(StepInfo info) {
		String hql = "select count(*) from Step s where 1 = 1";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.stepDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(StepInfo info, String hql,Map<String, Object> parameters) {
		if(info.getProcessId() != null && !info.getProcessId().trim().isEmpty()){
			hql += " and (s.process.id = :processId) ";
			parameters.put("processId", info.getProcessId());
		}
		if(info.getProcessName() != null && !info.getProcessName().trim().isEmpty()){
			hql += " and (s.process.name like :processName) ";
			parameters.put("processName", "%"+info.getProcessName()+"%");
		}
		if(info.getName() != null && !info.getName().trim().isEmpty()){
			hql += " and (s.name like :name) ";
			parameters.put("name", "%"+info.getName()+"%");
		}
		return hql;
	}

	@Override
	public StepInfo update(StepInfo info) {
		if(info != null){
			boolean isAdded = false;
			Step data = (info.getId() == null || info.getId().trim().isEmpty()) ? null : this.stepDao.load(Step.class, info.getId());
			if(isAdded = (data == null)){
				info.setId(UUID.randomUUID().toString());
				data = new Step();
				Process process = this.processService.loadProcess(info.getProcessId());
				if(process != null)data.setProcess(process);
			}
			BeanUtils.copyProperties(info, data);
			if(!isAdded && data.getProcess() != null && !data.getProcess().getId().equalsIgnoreCase(info.getProcessId())){
				Process process = this.processService.loadProcess(info.getProcessId());
				if(process != null)data.setProcess(process);
			}
			if(isAdded)this.stepDao.save(data);
		}
		return info;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0)return;
		for(int i = 0; i < ids.length; i++){
			Step step = this.stepDao.load(Step.class, ids[i]);
			if(step != null)this.stepDao.delete(step);
		}
	}
	@Override
	public Step loadStep(String stepId) {
		return this.stepDao.load(Step.class, stepId);
	}
	@Override
	public Step loadStepSign(String stepSign) {
		if(stepSign == null || stepSign.trim().isEmpty()) return null;
		final String hql = "from Step s where s.sign = :sign";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("sign", stepSign);
		List<Step> list = this.stepDao.find(hql, parameters, null, null);
		if(list == null || list.size() == 0)
			return null;
		return list.get(0);
	}
	
	@Override
	public List<Step> allSteps(String processId, String ignoreStepId) {
		String hql = "from Step s where 1 = 1";
		Map<String, Object> parameters = new HashMap<>();
		if(processId != null && !processId.trim().isEmpty()){
			hql += " and s.process.id = :processId ";
			parameters.put("processId", processId);
		}
		if(ignoreStepId != null && !ignoreStepId.trim().isEmpty()){
			hql += " and s.id != :ignoreStepId ";
			parameters.put("ignoreStepId", ignoreStepId);
		}
		return this.stepDao.find(hql, parameters, null, null);
	}
}