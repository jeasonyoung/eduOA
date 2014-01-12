package ipower.edu.document.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.edu.document.dao.ITransitionDao;
import ipower.edu.document.domain.Process;
import ipower.edu.document.domain.Step;
import ipower.edu.document.domain.Transition;
import ipower.edu.document.pageModel.TransitionInfo;
import ipower.edu.document.service.IProcessService;
import ipower.edu.document.service.IStepService;
import ipower.edu.document.service.ITransitionService;

/**
 * 步骤变迁服务实现类。
 * @author 杨勇。
 * @since 2013-12-19。
 * */
public class TransitionServiceImpl extends DataServiceBaseImpl<Transition, TransitionInfo> implements ITransitionService {
	private ITransitionDao transitionDao;
	private IProcessService processService;
	private IStepService stepService;
	@Override
	public void setTransitionDao(ITransitionDao transitionDao) {
		 this.transitionDao = transitionDao;
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
	protected List<Transition> find(TransitionInfo info) {
		String hql = "from Transition t where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		hql += " order by t.process.name,t.from.type,t.from.orderNo";
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql += " ,t."+ info.getSort() +" "+ info.getOrder();
		}
		return this.transitionDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected TransitionInfo changeModel(Transition data) {
		TransitionInfo info = new TransitionInfo();
		if(data != null){
			BeanUtils.copyProperties(data, info);
			if(data.getProcess() != null){
				info.setProcessId(data.getProcess().getId());
				info.setProcessName(data.getProcess().getName());
			}
			if(data.getFrom() != null){
				info.setFromStepId(data.getFrom().getId());
				info.setFromStepName(data.getFrom().getName());
			}
			if(data.getTo() != null){
				info.setToStepId(data.getTo().getId());
				info.setToStepName(data.getTo().getName());
			}
		}
		return info;
	}

	@Override
	protected Long total(TransitionInfo info) {
		String hql = "select count(*) from Transition t where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.transitionDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(TransitionInfo info, String hql, Map<String, Object> parameters) {
		if(info.getProcessId() != null && !info.getProcessId().trim().isEmpty()){
			hql += " and t.process.id = :processId";
			parameters.put("processId", info.getProcessId());
		}
		if(info.getName() != null && !info.getName().trim().isEmpty()){
			hql += " and t.name like :name ";
			parameters.put("name", "%" + info.getName() + "%");
		}
		if(info.getFromStepName() != null && !info.getFromStepName().trim().isEmpty()){
			hql += " and t.from.name like :fromStepName";
			parameters.put("fromStepName", "%" + info.getFromStepName() + "%");
		}
		if(info.getToStepName() != null && !info.getToStepName().trim().isEmpty()){
			hql += " and t.to.name like :toStepName";
			parameters.put("toStepName", "%" + info.getToStepName() + "%");
		}
		return hql;
	}

	@Override
	public TransitionInfo update(TransitionInfo info) {
		if(info != null){
			boolean isAdded = false;
			Transition data = (info.getId() == null || info.getId().trim().isEmpty()) ?  null : this.transitionDao.load(Transition.class, info.getId());
			if(isAdded = (data == null)){
				data = new Transition();
				info.setId(UUID.randomUUID().toString());
			}
			if(info.getProcessId() != null && (data.getProcess() == null || !data.getProcess().getId().equalsIgnoreCase(info.getProcessId()))){
				Process process = this.processService.loadProcess(info.getProcessId());
				if(process != null){
					info.setProcessName(process.getName());
					data.setProcess(process);
				}
			}
			if(info.getFromStepId() != null && (data.getFrom() == null || !data.getFrom().getId().equalsIgnoreCase(info.getFromStepId()))){
				Step step = this.stepService.loadStep(info.getFromStepId());
				if(step != null){
					info.setFromStepName(step.getName());
					data.setFrom(step);
				}
			}
			if(info.getToStepId() != null && (data.getTo() == null || !data.getTo().getId().equalsIgnoreCase(info.getToStepId()))){
				Step step = this.stepService.loadStep(info.getToStepId());
				if(step != null){
					info.setToStepName(step.getName());
					data.setTo(step);
				}
			}
			BeanUtils.copyProperties(info, data);
			if(isAdded)this.transitionDao.save(data);
		}
		return info;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length;i++){
			Transition data = this.transitionDao.load(Transition.class, ids[i]);
			if(data != null)this.transitionDao.delete(data);
		}
	}

	@Override
	public List<Transition> loadTransitions(String processId) {
		final String hql = "from Transition t where t.process.id = :processId order by t.process.name,t.from.type,t.from.orderNo";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("processId", processId);
		return this.transitionDao.find(hql, parameters, null, null);
	}

	@Override
	public Transition loadTransition(String transitionId) {
		return this.transitionDao.load(Transition.class, transitionId);
	}
}