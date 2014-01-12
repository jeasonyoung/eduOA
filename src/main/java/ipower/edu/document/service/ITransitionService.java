package ipower.edu.document.service;

import java.util.List;

import ipower.edu.document.dao.ITransitionDao;
import ipower.edu.document.domain.Transition;
import ipower.edu.document.pageModel.TransitionInfo;

/**
 * 步骤变迁服务接口。
 * @author 杨勇。
 * @since 2013-12-19。
 * */
public interface ITransitionService extends IDataServiceBase<TransitionInfo> {
	/**
	 * 设置步骤变迁数据接口。
	 * @param transitionDao
	 * 	步骤变迁数据接口。
	 * */
	void setTransitionDao(ITransitionDao transitionDao);
	/**
	 * 设置流程服务。
	 * @param processService
	 * 	流程服务。
	 * */
	void setProcessService(IProcessService processService);
	/**
	 * 设置步骤服务接口。
	 * @param stepService
	 * 	步骤服务接口。
	 * */
	void setStepService(IStepService stepService);
	/**
	 * 获取变迁对象。
	 * @param transitionId
	 * 	变迁ID。
	 * @return 变迁对象。
	 * */
	Transition loadTransition(String transitionId);
	/**
	 * 流程下的步骤变迁集合。
	 * @param processId
	 * 	步骤变迁集合。
	 * */
	List<Transition> loadTransitions(String processId);
}