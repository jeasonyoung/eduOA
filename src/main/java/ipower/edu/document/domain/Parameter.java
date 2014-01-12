package ipower.edu.document.domain;

import java.io.Serializable;

/**
 * 步骤参数。
 * @author 杨勇。
 * @since 2012-12-11。
 * */
public class Parameter implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,name,value,description;
	private Step step;
	/**
	 * 获取步骤。
	 * @return 步骤。
	 * */
	public Step getStep() {
		return step;
	}
	/**
	 * 设置步骤。
	 * @param step
	 * 	步骤。
	 * */
	public void setStep(Step step) {
		this.step = step;
	}
	/**
	 * 获取参数ID。
	 * @return 参数ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置参数ID。
	 * @param id
	 * 	参数ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 设置参数名。
	 * @return 参数名。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置参数名。
	 * @param name
	 * 	参数名。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取默认值。
	 * @return 默认值。
	 * */
	public String getValue() {
		return value;
	}
	/**
	 * 设置默认值。
	 * @param value
	 * 	默认值。
	 * */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取参数描述。
	 * @return 参数描述。
	 * */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置参数描述。
	 * @param description
	 * 	参数描述。
	 * */
	public void setDescription(String description) {
		this.description = description;
	}
}