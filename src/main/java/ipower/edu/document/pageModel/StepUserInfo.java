package ipower.edu.document.pageModel;

import ipower.pageModel.Paging;

/**
 * 流程步骤用户。
 * @author 杨勇。
 * @since 2013-12-18。
 * */
public class StepUserInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,processId,processName, stepId,stepName,userId,userName;
	private Integer type;
	/**
	 * 获取所属流程ID。
	 * @return 流程ID。
	 * */
	public String getProcessId() {
		return processId;
	}
	/**
	 * 设置所属流程ID。
	 * @param processId
	 * 	流程ID。
	 * */
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	/**
	 * 获取所属流程名称。
	 * @return 所属流程名称。
	 * */
	public String getProcessName() {
		return processName;
	}
	/**
	 * 设置所属流程名称。
	 * @param processName
	 * 	所属流程名称。
	 * */
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	/**
	 * 获取步骤ID。
	 * @return 步骤ID。
	 * */
	public String getStepId() {
		return stepId;
	}
	/**
	 * 设置步骤ID。
	 * @param stepId
	 * 	步骤ID。
	 * */
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	/**
	 * 获取步骤名称。
	 * @return 步骤名称。
	 * */
	public String getStepName() {
		return stepName;
	}
	/**
	 * 设置步骤名称。
	 * @param stepName
	 * 	步骤名称。
	 * */
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	/**
	 * 获取用户ID。
	 * @return 用户ID。
	 * */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置用户ID。
	 * @param userId
	 * 	用户ID。
	 * */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取用户名称。
	 * @return 用户名称。
	 * */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置用户名称。
	 * @param userName
	 * 	用户名称。
	 * */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取步骤用户联合主键。
	 * @return 步骤用户联合主键。
	 * */
	public String getId() {
		this.id = this.stepId + "," + this.userId;
		return this.id;
	}
	/**
	 * 设置步骤用户联合主键。
	 * @param id
	 * 	步骤用户联合主键。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取类型。
	 * @return 类型(1-待办，2-待阅)。
	 * */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置类型。
	 * @param type
	 * 	类型。
	 * */
	public void setType(Integer type) {
		this.type = type;
	}
}