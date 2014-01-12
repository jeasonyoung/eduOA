package ipower.edu.document.service;

import java.util.List;

import ipower.edu.document.dao.IStepDao;
import ipower.edu.document.domain.Step;
import ipower.edu.document.pageModel.StepInfo;

/**
 * 步骤服务接口。
 * @author 杨勇。
 * @since 2013-12-15。
 * */
public interface IStepService extends IDataServiceBase<StepInfo> {
	/**
	 * 设置流程步骤数据访问接口。
	 * @param stepDao
	 * 	流程步骤数据访问接口。
	 * */
	void setStepDao(IStepDao stepDao);
	/**
	 * 设置流程服务接口。
	 * @param processService
	 * 	流程服务接口。
	 * */
	void setProcessService(IProcessService processService);
	/**
	 * 加载步骤数据。
	 * @param stepId
	 * 	步骤ID。
	 * @return 步骤数据。
	 * */
	Step loadStep(String stepId);
	/**
	 * 加载步骤数据。
	 * @param stepSign
	 * 步骤标示。
	 * @return 步骤数据。
	 * */
	Step loadStepSign(String stepSign);
	/**
	 * 步骤数据集合。
	 * @param processId
	 * 	流程ID。
	 * @param ignoreStepId
	 * 	忽略的步骤ID。
	 * @return 步骤集合。
	 * */
	List<Step> allSteps(String processId,String ignoreStepId);
}