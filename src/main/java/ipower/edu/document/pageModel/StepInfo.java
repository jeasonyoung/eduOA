package ipower.edu.document.pageModel;

import ipower.pageModel.Paging;

/**
 * 流程步骤信息。
 * @author 杨勇。
 * @since 2013-12-15。
 * */
public class StepInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,name,sign,url,description,processId, processName;
	private Integer type,orderNo;
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
	 * 设置流程ID。
	 * @param processName
	 * 	流程ID。
	 * */
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	/**
	 * 获取步骤ID。
	 * @return 步骤ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置步骤ID。
	 * @param id
	 * 	步骤ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取步骤名称。
	 * @return 步骤名称。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置步骤名称。
	 * @param name
	 *  步骤名称。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取步骤标示。
	 * @return 步骤标示。
	 * */
	public String getSign() {
		return sign;
	}
	/**
	 * 设置步骤标示。
	 * @param sign
	 * 	步骤标示。
	 * */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * 获取步骤Url。
	 * @return 步骤Url。
	 * */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置步骤Url。
	 * @param url
	 * 	步骤url。
	 * */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取步骤类型。
	 * @return 步骤类型。
	 * */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置步骤类型。
	 * @param type
	 * 	步骤类型。
	 * */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取步骤描述。
	 * @return 步骤描述。
	 * */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置步骤描述。
	 * @param description
	 * 	步骤描述。
	 * */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取步骤排序号。
	 * @return 步骤排序号。
	 * */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置步骤排序号。
	 * @param orderNo
	 * 	步骤排序号。
	 * */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
}