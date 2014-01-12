package ipower.edu.document.service;

import ipower.edu.document.dao.IProcessInstanceDao;
import ipower.edu.document.dao.IStepTaskInstanceDao;
import ipower.edu.document.pageModel.ProcessInstanceInfo;

/**
 * 流程实例服务接口。
 * @author 杨勇。
 * @since 2013-12-19。
 * */
public interface IProcessInstanceService extends IDataServiceBase<ProcessInstanceInfo>,IFlowService {
	/**
	 * 设置流程实例数据访问。
	 * @param processInstanceDao
	 * 	流程实例数据访问。
	 * */
	void setProcessInstanceDao(IProcessInstanceDao processInstanceDao);
	/**
	 * 设置流程步骤实例数据访问。
	 * @param stepTaskInstanceDao
	 * 	流程步骤实例数据访问。
	 * */
	void setStepTaskInstanceDao(IStepTaskInstanceDao stepTaskInstanceDao);
	/**
	 * 设置流程服务。
	 * @param processService
	 * 	流程服务。
	 * */
	void setProcessService(IProcessService processService);
	/**
	 * 设置流程步骤服务。
	 * @param stepService
	 * 	流程步骤服务。
	 * */
	void setStepService(IStepService stepService);
}