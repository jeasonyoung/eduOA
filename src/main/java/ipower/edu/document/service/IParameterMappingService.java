package ipower.edu.document.service;

import ipower.edu.document.dao.IParameterMappingDao;
import ipower.edu.document.pageModel.ParameterMappingInfo;

/**
 * 流程步骤变迁参数映射服务接口。
 * @author 杨勇。
 * @since 2013-12-19。
 * */
public interface IParameterMappingService extends IDataServiceBase<ParameterMappingInfo> {
	/**
	 * 设置参数映射数据访问。
	 * @param parameterMappingDao
	 * 	参数映射数据访问。
	 * */
	void setParameterMappingDao(IParameterMappingDao parameterMappingDao);
	/**
	 * 设置变迁服务。
	 * @param transitionService
	 * 	变迁服务。
	 * */
	void setTransitionService(ITransitionService transitionService);
	/**
	 * 设置参数服务。
	 * @param parameterService
	 *  参数服务。
	 * */
	void setParameterService(IParameterService parameterService);
}