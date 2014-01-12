package ipower.edu.document.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import ipower.edu.document.dao.IStepUserDao;
import ipower.edu.document.domain.Step;
import ipower.edu.document.domain.StepUser;
import ipower.edu.document.pageModel.StepUserInfo;
import ipower.edu.document.service.IStepService;
import ipower.edu.document.service.IStepUserService;

/**
 * 流程步骤用户服务实现类。
 * @author 杨勇。
 * @since 2013-12-18。
 * */
public class StepUserServiceImpl extends DataServiceBaseImpl<StepUser,StepUserInfo> implements IStepUserService {
	private IStepUserDao stepUserDao;
	private IStepService stepService;
	@Override
	public void setStepUserDao(IStepUserDao stepUserDao) {
		this.stepUserDao = stepUserDao;
	}

	@Override
	public void setStepService(IStepService stepService) {
		this.stepService = stepService;
	}

	@Override
	protected List<StepUser> find(StepUserInfo info) {
		String hql = "from StepUser s where 1 = 1";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		hql += " order by s.step.process.name,s.step.orderNo,";
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql += " s."+ info.getSort() +" "+ info.getOrder();
		}
		return this.stepUserDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected StepUserInfo changeModel(StepUser data) {
		StepUserInfo info = new StepUserInfo();
		if(data != null){
			BeanUtils.copyProperties(data, info);
			if(data.getStep() != null){
				info.setStepId(data.getStep().getId());
				info.setStepName(data.getStep().getName());
				if(data.getStep().getProcess() != null){
					info.setProcessId(data.getStep().getProcess().getId());
					info.setProcessName(data.getStep().getProcess().getName());
				}
			}
		}
		return info;
	}

	@Override
	protected Long total(StepUserInfo info) {
		String hql = "select count(*) from StepUser s where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.stepUserDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(StepUserInfo info, String hql, Map<String, Object> parameters) {
		if(info.getProcessId() != null && !info.getProcessId().trim().isEmpty()){
			hql += " and s.step.process.id = :processId ";
			parameters.put("processId", info.getProcessId());
		}
		if(info.getStepId() != null && !info.getStepId().trim().isEmpty()){
			hql += " and s.step.id = :stepId ";
			parameters.put("stepId", info.getStepId());
		}
		if(info.getUserName() != null && !info.getUserName().trim().isEmpty()){
			hql +=" and s.userName like :userName ";
			parameters.put("userName", "%"+info.getUserName()+"%");
		}
		return hql;
	}

	@Override
	public StepUserInfo update(StepUserInfo info) {
		if(info != null){
			boolean isAdded = false;
			StepUser data =  this.loadStepUser(info.getStepId(), info.getUserId());
			if(isAdded = (data == null)){
				data = new StepUser();
			}
			BeanUtils.copyProperties(info,data);
			if(info.getStepId() != null && (data.getStep() == null || !data.getStep().getId().equalsIgnoreCase(info.getStepId()))){
				Step step = this.stepService.loadStep(info.getStepId());
				if(step != null){
					data.setStep(step);
					info.setStepName(step.getName());
					if(step.getProcess() != null){
						info.setProcessId(step.getProcess().getId());
						info.setProcessName(step.getProcess().getName());
					}
				}
			}
			if(isAdded)this.stepUserDao.save(data);
		}
		return info;
	}
	
	private synchronized StepUser loadStepUser(String stepId, String userId){
		final String hql = "from StepUser s where s.step.id = :stepId and s.userId = :userId";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("stepId", stepId);
		parameters.put("userId", userId);
		List<StepUser> list = this.stepUserDao.find(hql, parameters, null, null);	
		if(list == null || list.size() == 0) return null;
		return list.get(0);
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length;  i++){
			String[] strs = ids[i].split(",");
			if(strs != null && strs.length >= 2){
				StepUser data = this.loadStepUser(strs[0], strs[1]);
				if(data != null) this.stepUserDao.delete(data);
			}
		}
	}

	@Override
	public List<StepUser> loadStepUsers(String stepId) {
		final String hql = "from StepUser s where s.step.id = :stepId";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("stepId", stepId);
		return this.stepUserDao.find(hql, parameters, null, null);
	}

}