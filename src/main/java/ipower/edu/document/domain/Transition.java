package ipower.edu.document.domain;

import java.io.Serializable;
import java.util.Set;

/**
 * 步骤变迁。
 * @author 杨勇。
 * @since 2013-12-11。
 * */
public class Transition implements Serializable {
	private static final long serialVersionUID = 1L;
	private Process process;
	private String id,name;
	private Step from,to;
	private Set<ParameterMapping> mappings;
	private Integer rule;
	private Set<Condition> conditions;
	/**
	 * 变迁条件并运算。
	 * */
	public final static Integer RULE_Union_RUN = 1;
	/**
	 * 变迁条件或运算。
	 * */
	public final static Integer RULE_OR_RUN = 2;
	/**
	 * 获取流程。
	 * @return 流程。
	 * */
	public Process getProcess() {
		return process;
	}
	/**
	 * 设置流程。
	 * @param process 
	 * 	流程。
	 * */
	public void setProcess(Process process) {
		this.process = process;
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
	 * 获取前驱步骤。
	 * @return 前驱步骤。
	 * */
	public Step getFrom() {
		return from;
	}
	/**
	 * 设置前驱步骤。
	 * @param from
	 * 	前驱步骤。
	 * */
	public void setFrom(Step from) {
		this.from = from;
	}
	/**
	 * 获取后驱步骤。
	 * @return 后驱步骤。
	 * */
	public Step getTo() {
		return to;
	}
	/**
	 * 设置后驱步骤。
	 * @param to
	 * 	后驱步骤。
	 * */
	public void setTo(Step to) {
		this.to = to;
	}
	/**
	 * 获取参数映射集合。
	 * @return 参数映射集合。
	 * */
	public Set<ParameterMapping> getMappings() {
		return mappings;
	}
	/**
	 * 设置参数映射集合。
	 * @param mappings
	 * 	参数映射集合。
	 * */
	public void setMappings(Set<ParameterMapping> mappings) {
		this.mappings = mappings;
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
	 * 获取变迁规则集合。
	 * @return 变迁规则集合。
	 * */
	public Set<Condition> getConditions() {
		return conditions;
	}
	/**
	 * 设置变迁规则集合。
	 * @param conditions
	 * 	变迁规则集合。 
	 * */
	public void setConditions(Set<Condition> conditions) {
		this.conditions = conditions;
	}
}