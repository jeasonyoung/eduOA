package ipower.edu.document.pageModel;

import ipower.pageModel.Paging;

/**
 * 流程信息
 * @author 杨勇。
 * @since 2013-12-14。
 * */
public class ProcessInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,name,description;
	private Integer status;
	/**
	 * 获取流程ID。
	 * @return 流程ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置流程ID。
	 * @param id
	 * 	流程ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取流程名称。
	 * @return 流程名称。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置流程名称。
	 * @param name
	 *  流程名称。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取流程状态。
	 * @return 流程状态。
	 * */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置流程状态。
	 * @param status
	 * 	流程状态。
	 * */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取流程描述。
	 * @return 流程描述。
	 * */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置流程描述。
	 * @param description
	 * 	流程描述。
	 * */
	public void setDescription(String description) {
		this.description = description;
	}
}