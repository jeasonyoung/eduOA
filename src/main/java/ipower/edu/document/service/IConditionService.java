package ipower.edu.document.service;

import ipower.edu.document.dao.IConditionDao;
import ipower.edu.document.pageModel.ConditionInfo;

/**
 * 变迁条件服务接口。
 * @author 杨勇。
 * @since 2013-12-11。
 * */
public interface IConditionService extends IDataServiceBase<ConditionInfo> {
	/**
	 * 设置变迁条件数据接口。
	 * @param conditionDao
	 * 	变迁条件数据接口。
	 * */
	void setConditionDao(IConditionDao conditionDao);
	/**
	 * 设置流程步骤变迁服务。
	 * @param transitionService
	 * 	流程步骤变迁服务。
	 * */
	void setTransitionService(ITransitionService transitionService);
	/**
	 * 设置流程步骤参数服务。
	 * @param parameterService
	 * 	流程步骤参数服务。
	 * */
	void setParameterService(IParameterService parameterService);
}