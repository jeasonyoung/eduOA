package ipower.edu.document.domain;

import java.io.Serializable;
import java.util.Set;

/**
 * 流程定义。
 * @author 杨勇。
 * @since 2013-12-11。
 * */
public class Process implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,name,description;
	private Integer status;
	private Set<Step> steps;
	private Set<Transition> transitions;
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
	 * @return 流程ID。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置流程名称。
	 * @param name
	 * 	流程名称。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取流程状态。
	 * @return 流程状态(1-启用,0-禁用)。
	 * */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置流程状态。
	 * @param status
	 * 	流程状态(1-启用,0-禁用)。
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
	/**
	 * 获取流程步骤集合。
	 * @return 流程步骤集合。
	 * */
	public Set<Step> getSteps() {
		return steps;
	}
	/**
	 * 设置流程步骤集合。
	 * @param steps
	 * 	流程步骤集合。
	 * */
	public void setSteps(Set<Step> steps) {
		this.steps = steps;
	}
	/**
	 * 获取流程变迁集合。
	 * @return 流程变迁集合。
	 * */
	public Set<Transition> getTransitions() {
		return transitions;
	}
	/**
	 * 设置流程变迁集合。
	 * @param transitions
	 * 	流程变迁集合。
	 * */
	public void setTransitions(Set<Transition> transitions) {
		this.transitions = transitions;
	}
}