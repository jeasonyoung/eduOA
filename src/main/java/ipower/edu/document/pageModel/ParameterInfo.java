package ipower.edu.document.pageModel;

import ipower.pageModel.Paging;
/**
 * 步骤参数信息。
 * @author 杨勇。
 * @since 2013-12-17。
 * */
public class ParameterInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,name,value,description,stepId,stepName,processId,processName;
	/**
	 * 获取流程ID。
	 * @return 流程ID。
	 * */
	public String getProcessId() {
		return processId;
	}
	/**
	 * 设置流程ID。
	 * @param processId
	 * 	流程ID。
	 * */
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	/**
	 * 获取流程名称。
	 * @return 流程名称。
	 * */
	public String getProcessName() {
		return processName;
	}
	/**
	 * 设置流程名称。
	 * @param processName 
	 * 	流程名称。
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
	 * 获取参数ID。
	 * @return 参数ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置参数ID。
	 * @param id
	 * 	参数ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 设置参数名。
	 * @return 参数名。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置参数名。
	 * @param name
	 * 	参数名。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取默认值。
	 * @return 默认值。
	 * */
	public String getValue() {
		return value;
	}
	/**
	 * 设置默认值。
	 * @param value
	 * 	默认值。
	 * */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取参数描述。
	 * @return 参数描述。
	 * */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置参数描述。
	 * @param description
	 * 	参数描述。
	 * */
	public void setDescription(String description) {
		this.description = description;
	}
}