package ipower.edu.document.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.edu.document.dao.IParameterDao;
import ipower.edu.document.domain.Parameter;
import ipower.edu.document.domain.Step;
import ipower.edu.document.pageModel.ParameterInfo;
import ipower.edu.document.service.IParameterService;
import ipower.edu.document.service.IStepService;

/**
 * 步骤参数服务实现类。
 * @author 杨勇。
 * @since 2013-12-17。
 * */
public class ParameterServiceImpl extends DataServiceBaseImpl<Parameter,ParameterInfo> implements IParameterService {
	private IParameterDao parameterDao;
	private IStepService stepService;
	@Override
	public void setParameterDao(IParameterDao parameterDao) {
		this.parameterDao = parameterDao;
	}

	@Override
	public void setStepService(IStepService stepService) {
		this.stepService = stepService;
	}
	
	@Override
	public Parameter loadParameter(String parameterId) {
		return this.parameterDao.load(Parameter.class, parameterId);
	}

	@Override
	public List<Parameter> allParameters(String stepId) {
		final String hql = "from Parameter p where p.step.id = :stepId";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("stepId", stepId);
		return this.parameterDao.find(hql, parameters, null, null);
	}

	@Override
	protected List<Parameter> find(ParameterInfo info) {
		String hql = "from Parameter p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		hql += " order by p.step.process.name,";
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql += " p."+ info.getSort() +" "+ info.getOrder() + ",";
		}
		hql += " p.step.orderNo";
		return this.parameterDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected ParameterInfo changeModel(Parameter data) {
		ParameterInfo info = new ParameterInfo();
		BeanUtils.copyProperties(data, info);
		if(data.getStep() != null){
			info.setStepId(data.getStep().getId());
			info.setStepName(data.getStep().getName());
			if(data.getStep().getProcess() != null){
				info.setProcessId(data.getStep().getProcess().getId());
				info.setProcessName(data.getStep().getProcess().getName());
			}
		}
		return info;
	}

	@Override
	protected Long total(ParameterInfo info) {
		String hql = "select count(*) from Parameter p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.parameterDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(ParameterInfo info, String hql, Map<String, Object> parameters) {
		if(info.getProcessId() != null && !info.getProcessId().trim().isEmpty()){
			hql += " and p.step.process.id = :processId ";
			parameters.put("processId", info.getProcessId().trim());
		}
		if(info.getStepId() != null && !info.getStepId().trim().isEmpty()){
			hql += " and p.step.id = :stepId ";
			parameters.put("stepId", info.getStepId().trim());
		}else if(info.getStepName() != null && !info.getStepName().trim().isEmpty()){
			hql += " and p.step.name like :stepName ";
			parameters.put("stepName", "%" + info.getStepName().trim() + "%");
		}
		if(info.getName() != null && !info.getName().trim().isEmpty()){
			hql += " and p.name like :name ";
			parameters.put("name", "%" + info.getName().trim() + "%");
		}
		return hql;
	}

	@Override
	public ParameterInfo update(ParameterInfo info) {
		if(info != null){
			boolean isAdded = false;
			Parameter data = (info.getId() == null || info.getId().trim().isEmpty()) ?  null : this.parameterDao.load(Parameter.class, info.getId());
			if(isAdded = (data == null)){
				info.setId(UUID.randomUUID().toString());
				data = new Parameter();
			}
			BeanUtils.copyProperties(info, data);
						
			if(info.getStepId() != null && (data.getStep() == null || !data.getStep().getId().equalsIgnoreCase(info.getStepId()))){
				Step step = this.stepService.loadStep(info.getStepId());
				if(step != null){
					data.setStep(step);
					info.setStepId(step.getId());
					info.setStepName(step.getName());
					if(step.getProcess() != null){
						info.setProcessId(step.getProcess().getId());
						info.setProcessName(step.getProcess().getName());
					}
				}
			}
			
			if(isAdded) this.parameterDao.save(data);
		}
		return info;
	}
	

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0)return;
		for(int i = 0; i < ids.length; i++){
			Parameter parameter = this.parameterDao.load(Parameter.class, ids[i]);
			if(parameter != null) this.parameterDao.delete(parameter);
		}
	}
}