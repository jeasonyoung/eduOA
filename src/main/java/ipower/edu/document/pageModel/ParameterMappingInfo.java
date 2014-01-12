package ipower.edu.document.pageModel;

import ipower.pageModel.Paging;
/**
 * 流程步骤变迁参数映射。
 * @author 杨勇。
 * @since 2013-12-19。
 * */
public class ParameterMappingInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id, processId,processName,transitionId,transitionName, 
				fromParameterId,fromParameterName,toParameterId,toParameterName;
	/**
	 * 获取主键组合值。
	 * @return 主键组合值。
	 * */
	public String getId() {
		id = this.transitionId + "," + this.fromParameterId;
		return id;
	}
	/**
	 * 设置主键组合值。
	 * @param id
	 * 	主键组合值。
	 * */
	public void setId(String id) {
		this.id = id;
	}
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
	 * 	所属流程名称。
	 * */
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	/**
	 * 获取步骤变迁ID。
	 * @return 步骤变迁ID。
	 * */
	public String getTransitionId() {
		return transitionId;
	}
	/**
	 * 设置步骤变迁ID。
	 * @param transitionId
	 * 	步骤变迁ID。
	 * */
	public void setTransitionId(String transitionId) {
		this.transitionId = transitionId;
	}
	/**
	 * 获取步骤变迁名称。
	 * @return 步骤变迁名称。
	 * */
	public String getTransitionName() {
		return transitionName;
	}
	/**
	 * 设置步骤变迁名称。
	 * @param transitionName
	 * 	步骤变迁名称。
	 * */
	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}
	/**
	 * 获取前驱参数ID。
	 * @return 前驱参数ID。
	 * */
	public String getFromParameterId() {
		return fromParameterId;
	}
	/**
	 * 设置前驱参数ID。
	 * @param fromParameterId
	 * 	前驱参数ID。
	 * */
	public void setFromParameterId(String fromParameterId) {
		this.fromParameterId = fromParameterId;
	}
	/**
	 * 获取前驱参数名称。
	 * @return 前驱参数名称。
	 * */
	public String getFromParameterName() {
		return fromParameterName;
	}
	/**
	 * 设置前驱参数名称。
	 * @param fromParameterName
	 * 	前驱参数名称。
	 * */
	public void setFromParameterName(String fromParameterName) {
		this.fromParameterName = fromParameterName;
	}
	/**
	 * 获取后驱参数ID。
	 * @return 后驱参数ID。
	 * */
	public String getToParameterId() {
		return toParameterId;
	}
	/**
	 * 设置后驱参数ID。
	 * @param toParameterId
	 * 	后驱参数ID。
	 * */
	public void setToParameterId(String toParameterId) {
		this.toParameterId = toParameterId;
	}
	/**
	 * 获取后驱参数名称。
	 * @return 后驱参数名称。
	 * */
	public String getToParameterName() {
		return toParameterName;
	}
	/**
	 * 设置后驱参数名称。
	 * @param toParameterName
	 * 	后驱参数名称。
	 * */
	public void setToParameterName(String toParameterName) {
		this.toParameterName = toParameterName;
	}
}