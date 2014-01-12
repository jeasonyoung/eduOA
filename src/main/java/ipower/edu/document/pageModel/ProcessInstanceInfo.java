package ipower.edu.document.pageModel;

import java.util.Date;

import ipower.pageModel.Paging;

/**
 * 流程实例信息。
 * @author 杨勇。
 * @since 2013-12-19。
 * */
public class ProcessInstanceInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,name,remarks,processId,processName;
	private Integer status;
	private Date begin,end;
	/**
	 * 获取所属流程ID。
	 * @return 所属流程ID。
	 * */
	public String getProcessId() {
		return processId;
	}
	/**
	 * 设置所属流程ID。
	 * @param processId
	 * 	所属流程ID。
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
	 * 所属流程名称。
	 * */
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	/**
	 * 获取流程实例ID。
	 * @return 流程实例ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置流程实例ID。
	 * @param id
	 * 	流程实例ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取流程实例名称。
	 * @return 流程实例名称。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置流程实例名称。
	 * @param name
	 * 	流程实例名称。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取流程实例备注。
	 * @return 流程实例备注。
	 * */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置流程实例备注。
	 * @param remaks
	 * 	流程实例备注。
	 * */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取流程实例状态。
	 * @return 流程实例状态。
	 * */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置流程实例状态。
	 * @param status
	 * 	流程实例状态。
	 * */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取流程实例开始时间。
	 * @return 流程实例开始时间。
	 * */
	public Date getBegin() {
		return begin;
	}
	/**
	 * 设置流程实例开始时间。
	 * @param begin
	 * 	流程实例开始时间。
	 * */
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	/**
	 * 获取流程实例结束时间。
	 * @return 流程实例结束时间。
	 * */
	public Date getEnd() {
		return end;
	}
	/**
	 * 设置流程实例结束时间。
	 * @param end
	 * 	流程实例结束时间。
	 * */
	public void setEnd(Date end) {
		this.end = end;
	}
}