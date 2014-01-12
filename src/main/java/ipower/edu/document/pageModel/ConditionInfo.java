package ipower.edu.document.pageModel;

import ipower.pageModel.Paging;

/**
 * 变迁规则。
 * @author 杨勇。
 * @since 2013-12-20。
 * */
public class ConditionInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id, processId,processName,transitionId,transitionName,
		fromParameterId,fromParameterName,compareValue;
	/**
	 * 获取变迁规则主键。
	 * @return 变迁规则主键。
	 * */
	public String getId() {
		id = this.transitionId + "," + this.fromParameterId + "," + this.compareValue;
		return id;
	}
	/**
	 * 设置变迁规则主键。
	 * @param id
	 * 	变迁规则主键。
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
	 *  @param processId
	 *  所属流程ID。
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
	 * 获取流程步骤变迁ID。
	 * @return 流程步骤变迁ID。
	 * */
	public String getTransitionId() {
		return transitionId;
	}
	/**
	 * 设置流程步骤变迁ID。
	 * @param transitionId
	 * 	流程步骤变迁ID。
	 * */
	public void setTransitionId(String transitionId) {
		this.transitionId = transitionId;
	}
	/**
	 * 获取流程步骤变迁名称。
	 * @return 流程步骤变迁名称。
	 * */
	public String getTransitionName() {
		return transitionName;
	}
	/**
	 * 设置流程步骤变迁名称。
	 * @param transitionName
	 * 	流程步骤变迁名称。
	 * */
	public void setTransitionName(String transitionName) {
		this.transitionName = transitionName;
	}
	/**
	 * 获取前驱步骤参数ID。
	 * @return 前驱步骤参数ID。
	 * */
	public String getFromParameterId() {
		return fromParameterId;
	}
	/**
	 * 设置前驱步骤参数ID。
	 * @param fromParameterID
	 * 	前驱步骤参数ID。
	 * */
	public void setFromParameterId(String fromParameterId) {
		this.fromParameterId = fromParameterId;
	}
	/**
	 * 获取前驱步骤参数名称。
	 * @return 前驱步骤参数名称。
	 * */
	public String getFromParameterName() {
		return fromParameterName;
	}
	/**
	 * 设置前驱步骤参数名称。
	 * @param fromParameterName
	 * 	前驱步骤参数名称。
	 * */
	public void setFromParameterName(String fromParameterName) {
		this.fromParameterName = fromParameterName;
	}
	/**
	 * 获取变迁条件。
	 * @return 变迁条件。
	 * */
	public String getCompareValue() {
		return compareValue;
	}
	/**
	 * 设置变迁条件。
	 * @param compareValue
	 * 	变迁条件。
	 * */
	public void setCompareValue(String compareValue) {
		this.compareValue = compareValue;
	}
}