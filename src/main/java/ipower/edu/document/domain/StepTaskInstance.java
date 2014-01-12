package ipower.edu.document.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 步骤任务实例。
 * @author 杨勇。
 * @since 2013-12-12。
 * */
public class StepTaskInstance implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,name,url,data,userId,userName;
	private Integer status,type;
	private Date time;
	private ProcessInstance processInstance;
	private Step step;
	/**
	 * 步骤任务实例状态-未处理。
	 * */
	public final static Integer CONST_STATUS_NO = 1;
	/**
	 * 步骤任务实例状态-处理完成。
	 * */
	public final static Integer CONST_STATUS_COMPLETE = 2;
	/**
	 * 步骤任务实例状态-忽略跳过。
	 * */
	public final static Integer CONST_STATUS_IGNORE = 3;
	/**
	 * 步骤任务实例类型-待办。
	 * */
	public final static Integer CONST_TYPE_PENDING = 1;
	/**
	 * 步骤任务实例类型-待阅。
	 * */
	public final static Integer CONST_TYPE_BEREAD = 2;
	
	/**
	 * 获取步骤任务实例ID。
	 * @return 步骤任务实例ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置步骤任务实例ID。
	 * @param id
	 * 	步骤任务实例ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取步骤任务实例名称。
	 * @return 步骤任务实例名称。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置步骤任务实例名称。
	 * @param name 
	 * 	步骤任务实例名称。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取任务用户ID。
	 * @return 任务用户ID。
	 * */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置任务用户ID。
	 * @param userId
	 * 	任务用户ID。
	 * */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取任务用户名。
	 * @return 任务用户名。
	 * */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置任务用户名。
	 * @param userName
	 * 	任务用户名。
	 * */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取步骤任务实例url。
	 * @return 步骤任务实例url。
	 * */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置步骤任务实例url。
	 * @param url
	 * 	步骤任务实例url。
	 * */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取步骤处理数据。
	 * @return 步骤处理数据。
	 * */
	public String getData() {
		return data;
	}
	/**
	 * 设置步骤处理数据。
	 * @param data
	 * 	步骤处理数据。
	 * */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * 获取步骤状态。
	 * @return 步骤状态(0-未处理，1-处理完成，2-忽略跳过)。
	 * */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置步骤状态。
	 * @param status
	 * 	步骤状态(0-未处理，1-处理完成，2-忽略跳过)。
	 * */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取步骤类型。
	 * @return 步骤类型(1-待办，2-待阅)。
	 * */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置步骤类型。
	 * @param type
	 * 	步骤类型(1-待办，2-待阅)。
	 * */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取实例生成时间。
	 * @return 实例生成时间。
	 * */
	public Date getTime() {
		return time;
	}
	/**
	 * 设置实例生成时间。
	 * @param time
	 * 实例生成时间。
	 * */
	public void setTime(Date time) {
		this.time = time;
	}
	/**
	 * 获取所属流程实例。
	 * @return 所属流程实例。
	 * */
	public ProcessInstance getProcessInstance() {
		return processInstance;
	}
	/**
	 * 设置所属流程实例。
	 * @param processInstance 
	 * 	所属流程实例。
	 * */
	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}
	/**
	 * 获取所属步骤。
	 * @return 所属步骤。
	 * */
	public Step getStep() {
		return step;
	}
	/**
	 * 设置所属步骤。
	 * @param step
	 * 	所属步骤。
	 * */
	public void setStep(Step step) {
		this.step = step;
	}
}