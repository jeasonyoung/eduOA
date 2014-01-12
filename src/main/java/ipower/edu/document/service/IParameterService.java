package ipower.edu.document.service;

import java.util.List;

import ipower.edu.document.dao.IParameterDao;
import ipower.edu.document.domain.Parameter;
import ipower.edu.document.pageModel.ParameterInfo;

/**
 * 步骤参数服务。
 * @author 杨勇。
 * @since 2013-12-17。
 * */
public interface IParameterService extends IDataServiceBase<ParameterInfo> {
	/**
	 * 设置步骤参数数据访问。
	 * @param parameterDao
	 * 	步骤参数数据访问。
	 * */
	void setParameterDao(IParameterDao parameterDao);
	/**
	 * 设置步骤服务。
	 * @param stepService
	 * 	步骤服务。
	 * */
	void setStepService(IStepService stepService);
	/**
	 * 加载参数对象。
	 * @param parameterId
	 * 	参数对象。
	 * @return 参数对象。
	 * */
	Parameter loadParameter(String parameterId);
	/**
	 * 加载步骤下的全部参数。
	 * @param stepId
	 * 	步骤ID。
	 * @return 参数集合。
	 * */
	List<Parameter> allParameters(String stepId);
}