package ipower.edu.document.pageModel;

import ipower.pageModel.Paging;

/**
 * 流程步骤变迁。
 * @author 杨勇。
 * @since 2013-12-19。
 * */
public class TransitionInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,name,processId,processName,fromStepId,fromStepName,toStepId,toStepName;
	private Integer rule;
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
	public String getId() {
		return id;
	}
	/**
	 * 设置步骤变迁ID。
	 * @param id
	 * 	步骤变迁ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取步骤变迁名称。
	 * @return 步骤变迁名称。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置步骤变迁名称。
	 * @param name
	 * 	步骤变迁名称。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取变迁规则逻辑运算。
	 * @return 获取变迁规则逻辑运算(1-变迁条件并运算，2-变迁条件或运算)。
	 * */
	public Integer getRule() {
		return rule;
	}
	/**
	 * 设置变迁规则逻辑运算。
	 * @param rule
	 * 	获取变迁规则逻辑运算(1-变迁条件并运算，2-变迁条件或运算)。
	 * */
	public void setRule(Integer rule) {
		this.rule = rule;
	}
	/**
	 * 获取前驱步骤ID。
	 * @return 前驱步骤ID。
	 * */
	public String getFromStepId() {
		return fromStepId;
	}
	/**
	 * 设置前驱步骤ID。
	 * @param fromStepId
	 * 	前驱步骤ID。
	 * */
	public void setFromStepId(String fromStepId) {
		this.fromStepId = fromStepId;
	}
	/**
	 * 获取前驱步骤名称。
	 * @return 前驱步骤名称。
	 * */
	public String getFromStepName() {
		return fromStepName;
	}
	/**
	 * 设置前驱步骤名称。
	 * @param fromStepName
	 * 	前驱步骤名称。
	 * */
	public void setFromStepName(String fromStepName) {
		this.fromStepName = fromStepName;
	}
	/**
	 * 获取后驱步骤ID。
	 * @return 后驱步骤ID。
	 * */
	public String getToStepId() {
		return toStepId;
	}
	/**
	 * 设置后驱步骤ID。
	 * @param toStepId
	 * 	后驱步骤ID。
	 * */
	public void setToStepId(String toStepId) {
		this.toStepId = toStepId;
	}
	/**
	 * 获取后驱步骤名称。
	 * @return 后驱步骤名称。
	 * */
	public String getToStepName() {
		return toStepName;
	}
	/**
	 * 设置后驱步骤名称。
	 * @param toStepName
	 * 	后驱步骤名称。
	 * */
	public void setToStepName(String toStepName) {
		this.toStepName = toStepName;
	}
}