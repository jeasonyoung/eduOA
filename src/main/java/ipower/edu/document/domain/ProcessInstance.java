package ipower.edu.document.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 流程实例。
 * @author 杨勇。
 * @since 2013-12-12。
 * */
public class ProcessInstance implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,name,remarks;
	private Integer status;
	private Process process;
	private Date begin,end;
	private Set<StepTaskInstance> stepTaskInstances;
	/**
	 * 流程实例状态-运行中。
	 * */
	public final static Integer CONST_STATUS_RUN = 1;
	/**
	 * 流程实例状态-运行错误。
	 * */
	public final static Integer CONST_STATUS_ERROR = 2;
	/**
	 * 流程实例状态-实例结束。
	 * */
	public final static Integer CONST_STATUS_END = 3;
	/**
	 * 获取所属流程。
	 * @return 所属流程。
	 * */
	public Process getProcess() {
		return process;
	}
	/**
	 * 设置所属流程。
	 * @param process
	 * 	所属流程。
	 * */
	public void setProcess(Process process) {
		this.process = process;
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
	 * 获取流程实例状态。
	 * @return 流程实例状态(1-运行,2-错误,3-结束)。
	 * */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置流程实例状态。
	 * @param status
	 * 	流程实例状态(1-运行,2-错误,3-结束)。
	 * */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取备注信息。
	 * @return 备注信息。
	 * */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置备注信息。
	 * @param remarks
	 * 	备注信息。
	 * */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取流程开始时间。
	 * @return 流程开始时间。
	 * */
	public Date getBegin() {
		return begin;
	}
	/**
	 * 设置流程开始时间。
	 * @param begin
	 * 	流程开始时间。
	 * */
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	/**
	 * 获取流程结束时间。
	 * @return 流程结束时间。
	 * */
	public Date getEnd() {
		return end;
	}
	/**
	 * 设置流程结束时间。
	 * @param end
	 * 	流程结束时间。
	 * */
	public void setEnd(Date end) {
		this.end = end;
	}
	/**
	 * 获取步骤任务集合。
	 * @return 步骤任务集合。
	 * */
	public Set<StepTaskInstance> getStepTaskInstances() {
		return stepTaskInstances;
	}
	/**
	 * 设置步骤任务集合。
	 * @param stepTaskInstances
	 * 	步骤任务集合。
	 * */
	public void setStepTaskInstances(Set<StepTaskInstance> stepTaskInstances) {
		this.stepTaskInstances = stepTaskInstances;
	}
}