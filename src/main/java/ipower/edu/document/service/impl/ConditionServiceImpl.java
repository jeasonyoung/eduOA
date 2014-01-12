package ipower.edu.document.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import ipower.edu.document.dao.IConditionDao;
import ipower.edu.document.domain.Condition;
import ipower.edu.document.domain.Parameter;
import ipower.edu.document.domain.Transition;
import ipower.edu.document.pageModel.ConditionInfo;
import ipower.edu.document.service.IConditionService;
import ipower.edu.document.service.IParameterService;
import ipower.edu.document.service.ITransitionService;

/**
 * 变迁条件服务实现类。
 * @author 杨勇。
 * @since 2013-12-21。
 * */
public class ConditionServiceImpl extends DataServiceBaseImpl<Condition,ConditionInfo> implements IConditionService {
	private IConditionDao conditionDao;
	private ITransitionService transitionService;
	private IParameterService parameterService;
	
	@Override
	public void setConditionDao(IConditionDao conditionDao) {
		this.conditionDao = conditionDao;
	}

	@Override
	public void setTransitionService(ITransitionService transitionService) {
		this.transitionService = transitionService;
	}
	
	@Override
	public void setParameterService(IParameterService parameterService) {
		this.parameterService = parameterService;
	}

	@Override
	protected List<Condition> find(ConditionInfo info) {
		String hql = "from Condition c where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			if(info.getSort().equalsIgnoreCase("processName")){
				info.setSort("transition.process.name");
			}else if(info.getSort().equalsIgnoreCase("transitionName")){
				info.setSort("transition.name");
			}else if(info.getSort().equalsIgnoreCase("fromParameterName")){
				info.setSort("parameter.name");
			}
			
			hql += " order by c."+ info.getSort() +" "+ info.getOrder();
		}
		return this.conditionDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected ConditionInfo changeModel(Condition data) {
		ConditionInfo info = new ConditionInfo();
		if(data != null){
			BeanUtils.copyProperties(data, info);
			if(data.getTransition() != null){
				info.setTransitionId(data.getTransition().getId());
				info.setTransitionName(data.getTransition().getName());
				if(data.getTransition().getProcess() != null){
					info.setProcessId(data.getTransition().getProcess().getId());
					info.setProcessName(data.getTransition().getProcess().getName());
				}
			}
			if(data.getParameter() != null){
				info.setFromParameterId(data.getParameter().getId());
				info.setFromParameterName(data.getParameter().getName());
			}
		}
		return info;
	}

	@Override
	protected Long total(ConditionInfo info) {
		String hql = "select count(*) from Condition c where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.conditionDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(ConditionInfo info, String hql, Map<String, Object> parameters) {
		if(info.getProcessId() != null && !info.getProcessId().trim().isEmpty()){
			hql += " and c.transition.process.id = :processId ";
			parameters.put("processId", info.getProcessId());
		}
		if(info.getProcessName() != null && !info.getProcessName().trim().isEmpty()){
			hql += " and c.transition.process.name like :processName ";
			parameters.put("processName", "%" + info.getProcessName() + "%");
		}
		if(info.getTransitionId() != null && !info.getTransitionId().trim().isEmpty()){
			hql += " and (c.transition.id = :transitionId or c.transition.name like :transitionName) ";
			parameters.put("transitionId", info.getTransitionId());
			parameters.put("transitionName", "%"+info.getTransitionId()+"%");
		}
		if(info.getTransitionName() != null && !info.getTransitionName().trim().isEmpty()){
			hql += " and c.transition.name like :transitionName";
			parameters.put("transitionName", "%" + info.getTransitionName() + "%");
		}
		if(info.getFromParameterName() != null && !info.getFromParameterName().trim().isEmpty()){
			hql += " and c.parameter.name like :fromParameterName";
			parameters.put("fromParameterName", "%" + info.getFromParameterName() + "%");
		}
		return hql;
	}

	@Override
	public ConditionInfo update(ConditionInfo info) {
		if(info != null){
			boolean isAdded = false;
			
			Condition data = this.loadCondition(info.getTransitionId(), info.getFromParameterId(), info.getCompareValue());
			if(isAdded = (data == null)){
				data = new Condition();
			}
			
			BeanUtils.copyProperties(info,data);
			
			if(info.getTransitionId() != null && (data.getTransition() == null || !data.getTransition().getId().equalsIgnoreCase(info.getTransitionId()))){
				Transition transition = this.transitionService.loadTransition(info.getTransitionId());
				if(transition != null){
					data.setTransition(transition);
					info.setTransitionName(transition.getName());
					if(transition.getProcess() != null){
						info.setProcessId(transition.getProcess().getId());
						info.setProcessName(transition.getProcess().getName());
					}
				}
			}
			if(info.getFromParameterId() != null && (data.getParameter() == null || !data.getParameter().getId().equalsIgnoreCase(info.getFromParameterId()))){
				Parameter parameter = this.parameterService.loadParameter(info.getFromParameterId());
				if(parameter != null){
					data.setParameter(parameter);
					info.setFromParameterName(parameter.getName());
				}
			}
			
			if(isAdded)this.conditionDao.save(data);
		}
		return info;
	}
	
	private Condition loadCondition(String transitionId,String fromParameterId,String compareValue){
		final String hql = "from Condition c where c.transition.id = :transitionId and c.parameter.id = :fromParameterId and c.compareValue = :compareValue";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("transitionId", transitionId);
		parameters.put("fromParameterId", fromParameterId);
		parameters.put("compareValue", compareValue);
		List<Condition> list = this.conditionDao.find(hql, parameters, null, null);
		if(list == null || list.size() == 0) return null;
		return list.get(0);
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0)return;
		for(int i = 0; i < ids.length; i++){
			String[] strs = ids[i].split(",");
			if(strs != null && strs.length >= 3){
				Condition data = this.loadCondition(strs[0], strs[1], strs[2]);
				if(data != null) this.conditionDao.delete(data);
			}
		}
	}
}