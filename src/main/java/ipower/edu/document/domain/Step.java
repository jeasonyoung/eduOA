package ipower.edu.document.domain;

import java.io.Serializable;
import java.util.Set;

/**
 * 流程步骤。
 * @author 杨勇。
 * @since 2012-12-11。
 * */
public class Step implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,sign,name,url,description;
	private Integer type,orderNo;
	private Process process;
	private Set<Parameter> parameters;
	private Set<StepUser> stepUsers;
	/**
	 * 开始步骤类型值。
	 * */
	public final static Integer StartStepTypeValue = 1;
	/**
	 * 中间步骤类型值。
	 * */
	public final static Integer MiddleStepTypeValue = 2;
	/**
	 * 终结步骤类型值。
	 * */
	public final static Integer EndStepTypeValue = 3;
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
	 * 获取步骤标示。
	 * @return 步骤标示。
	 * */
	public String getSign() {
		return sign;
	}
	/**
	 * 设置步骤标示。
	 * @param sign
	 * 步骤标示。
	 * */
	public void setSign(String sign) {
		this.sign = sign;
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
	 * 	步骤名称。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取步骤类型
	 * @return 步骤类型(1-开始步骤 2-中间步骤 3-终结步骤)。
	 * */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置步骤类型。
	 * @param type
	 * 	步骤类型(1-开始步骤 2-中间步骤 3-终结步骤)。
	 * */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取步骤操作URL。
	 * @return 步骤操作URL。
	 * */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置步骤操作URL。
	 * @param url
	 * 	步骤操作URL。
	 * */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取步骤排序。
	 * @return 步骤排序。
	 * */
	public Integer getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置步骤排序。
	 * @param order
	 * 	步骤排序。
	 * */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
	 * 获取步骤参数集合。
	 * @return 步骤参数集合。
	 * */
	public Set<Parameter> getParameters() {
		return parameters;
	}
	/**
	 * 设置步骤参数集合。
	 * @param parameters
	 * 	步骤参数集合。
	 * */
	public void setParameters(Set<Parameter> parameters) {
		this.parameters = parameters;
	}
	/**
	 * 获取步骤用户集合。
	 * @return 步骤用户集合。
	 * */
	public Set<StepUser> getStepUsers() {
		return stepUsers;
	}
	/**
	 * 设置步骤用户集合。
	 * @param stepUsers
	 * 	步骤用户集合。
	 * */
	public void setStepUsers(Set<StepUser> stepUsers) {
		this.stepUsers = stepUsers;
	}
}