package ipower.edu.document.service;

import java.util.List;

import ipower.edu.document.dao.IStepUserDao;
import ipower.edu.document.domain.StepUser;
import ipower.edu.document.pageModel.StepUserInfo;

/**
 * 流程步骤用户服务接口。
 * @author 杨勇。
 * @since 2013-12-18。
 * */
public interface IStepUserService extends IDataServiceBase<StepUserInfo> {
	/**
	 * 设置步骤用户数据访问。
	 * @param stepUserDao
	 * 	步骤用户数据访问。
	 * */
	void setStepUserDao(IStepUserDao stepUserDao);
	/**
	 * 设置步骤服务接口。
	 * @param stepService
	 * 	步骤服务接口。
	 * */
	void setStepService(IStepService stepService);
	/**
	 * 获取步骤下用户集合。
	 * @param stepId
	 * 	步骤ID。
	 * @return 步骤用户集合。
	 * */
	List<StepUser> loadStepUsers(String stepId);
}