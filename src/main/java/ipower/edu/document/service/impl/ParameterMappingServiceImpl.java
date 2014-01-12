package ipower.edu.document.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ipower.edu.document.dao.IParameterMappingDao;
import ipower.edu.document.domain.Parameter;
import ipower.edu.document.domain.ParameterMapping;
import ipower.edu.document.domain.Transition;
import ipower.edu.document.pageModel.ParameterMappingInfo;
import ipower.edu.document.service.IParameterMappingService;
import ipower.edu.document.service.IParameterService;
import ipower.edu.document.service.ITransitionService;

/**
 * 流程步骤变迁参数映射服务实现。
 * @author 杨勇。
 * @since 2013-12-19。
 * */
public class ParameterMappingServiceImpl extends DataServiceBaseImpl<ParameterMapping,ParameterMappingInfo> implements IParameterMappingService {
	private IParameterMappingDao parameterMappingDao;
	private ITransitionService transitionService;
	private IParameterService parameterService;
	
	@Override
	public void setParameterMappingDao(IParameterMappingDao parameterMappingDao) {
		this.parameterMappingDao = parameterMappingDao;
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
	protected List<ParameterMapping> find(ParameterMappingInfo info) {
		String hql = "from ParameterMapping p where 1 = 1";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			if(info.getSort().equalsIgnoreCase("processName")){
				info.setSort("transition.process.name");
			}else if(info.getSort().equalsIgnoreCase("transitionName")){
				info.setSort("transition.name");
			}else if(info.getSort().equalsIgnoreCase("fromParameterName")){
				info.setSort("from.name");
			}else if(info.getSort().equalsIgnoreCase("toParameterName")){
				info.setSort("to.name");
			}
			hql += " order by p."+ info.getSort() +" "+ info.getOrder();
		}
		return this.parameterMappingDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected ParameterMappingInfo changeModel(ParameterMapping data) {
		ParameterMappingInfo info = new ParameterMappingInfo();
		if(data != null){
			if(data.getTransition() != null){
				info.setTransitionId(data.getTransition().getId());
				info.setTransitionName(data.getTransition().getName());
				if(data.getTransition().getProcess() != null){
					info.setProcessId(data.getTransition().getProcess().getId());
					info.setProcessName(data.getTransition().getProcess().getName());
				}
			}
			if(data.getFrom() != null){
				info.setFromParameterId(data.getFrom().getId());
				info.setFromParameterName(data.getFrom().getName());
			}
			if(data.getTo() != null){
				info.setToParameterId(data.getTo().getId());
				info.setToParameterName(data.getTo().getName());
			}
		}
		return info;
	}

	@Override
	protected Long total(ParameterMappingInfo info) {
		String hql = "select count(*) from ParameterMapping p where 1 = 1";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.parameterMappingDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(ParameterMappingInfo info, String hql, Map<String, Object> parameters) {
		if(info.getProcessId() != null && !info.getProcessId().trim().isEmpty()){
			hql += " and p.transition.process.id = :processId ";
			parameters.put("processId", info.getProcessId());
		}
		if(info.getTransitionId() != null && !info.getTransitionId().trim().isEmpty()){
			hql += " and p.transition.id = :transitionId ";
			parameters.put("transitionId", info.getTransitionId());
		}
		if(info.getTransitionName() != null && !info.getTransitionName().trim().isEmpty()){
			hql += " and p.transition.name like :transitionName ";
			parameters.put("transitionName", "%" + info.getTransitionName() + "%");
		}
		if(info.getFromParameterName() != null && !info.getFromParameterName().trim().isEmpty()){
			hql += " and p.from.name like :fromParameterName ";
			parameters.put("fromParameterName", "%" + info.getFromParameterName() + "%");
		}
		if(info.getToParameterName() != null && !info.getToParameterName().trim().isEmpty()){
			hql += " and p.to.name like :toParameterName ";
			parameters.put("toParameterName", "%" + info.getToParameterName() + "%");
		}
		return hql;
	}

	@Override
	public ParameterMappingInfo update(ParameterMappingInfo info) {
		if(info != null){
			ParameterMapping data = this.loadMapping(info.getTransitionId(), info.getFromParameterId());
			boolean isAdded = false;
			if(isAdded = (data == null)) data = new ParameterMapping();
			if(info.getTransitionId() != null && (data.getTransition() == null || !data.getTransition().getId().equalsIgnoreCase(info.getTransitionId())))
			{
				Transition trans = this.transitionService.loadTransition(info.getTransitionId());
				if(trans != null){
					data.setTransition(trans);
					info.setTransitionName(trans.getName());
				}
			}
			
			if(info.getFromParameterId() != null && (data.getFrom() == null || !data.getFrom().getId().equalsIgnoreCase(info.getFromParameterId()))){
				Parameter parameter = this.parameterService.loadParameter(info.getFromParameterId());
				if(parameter != null){
					data.setFrom(parameter);
					info.setFromParameterName(parameter.getName());
				}
			}
			
			if(info.getToParameterId() != null && (data.getTo() == null || !data.getTo().getId().equalsIgnoreCase(info.getToParameterId()))){
				Parameter parameter = this.parameterService.loadParameter(info.getToParameterId());
				if(parameter != null){
					data.setTo(parameter);
					info.setToParameterName(parameter.getName());
				}
			}
			if(isAdded)this.parameterMappingDao.save(data);
		}
		return info;
	}
	
	private ParameterMapping loadMapping(String transitionId,String fromParameterId){
		final String hql = "from ParameterMapping p where p.transition.id = :transitionId and p.from.id = :fromParameterId ";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("transitionId", transitionId);
		parameters.put("fromParameterId", fromParameterId);
		List<ParameterMapping> list = this.parameterMappingDao.find(hql, parameters, null, null);
		if(list == null || list.size() == 0) return null;
		return list.get(0);
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0)return;
		for(int i = 0; i < ids.length; i++){
			String[] strs = ids[i].split(",");
			if(strs != null && strs.length >= 2){
				ParameterMapping data = this.loadMapping(strs[0], strs[1]);
				if(data != null) this.parameterMappingDao.delete(data);
			}
		}
	}
}