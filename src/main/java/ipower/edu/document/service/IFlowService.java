package ipower.edu.document.service;

import java.util.Map;

/**
 * 流程服务接口。
 * @author 杨勇。
 * @since 2013-12-30。
 * */
public interface IFlowService {

	/**
	 * 流程实例初始化。
	 * @param processId
	 * 	流程ID。
	 * @param instanceName
	 * 	流程实例名。
	 * @param remarks
	 * 	流程实例备注。
	 * @return 流程实例ID。
	 * */
	String initFlow(String processId,String instanceName,String remarks) throws Exception;
	/**
	 * 提交任务。
	 * @param stepSign
	 * 当前流程步骤标示。
	 * @param instanceId
	 * 	流程实例ID。
	 * @param taskId
	 * 当前流程步骤任务ID。
	 * 为null或者为空时为开始步骤。
	 * @param userId
	 * 	当前审批用户ID。
	 * @param userName
	 * 	当前审批用户名。
	 * @param approvalViews
	 * 	审批意见。
	 * @param parameters
	 * 	参数集合。
	 * @param toPendingUsers
	 * 	待办用户集合。
	 * @param toBereadUsers
	 *  待阅用户集合。
	 *  @return
	 *  反馈结果，true-提交成功，false-提交失败。
	 * */
	boolean CommitFlow(String stepSign,String instanceId,String taskId,String userId,String userName,String approvalViews, 
		Map<String, Object> parameters,Map<String, String> toPendingUsers,Map<String, String> toBereadUsers) throws Exception;
}