package ipower.org.domain;

import java.io.Serializable;
/**
 * 角色
 * @author 杨勇。
 * @since 2013-12-06。
 * */
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,name,description;
	/**
	 * 获取角色ID。
	 * @return 角色ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置角色ID。
	 * @param id
	 *  角色ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取角色名称。
	 * @return 角色名称。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置角色名称。
	 * @param name
	 *  角色名称。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取角色描述。
	 * @return 角色描述。
	 * */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置角色描述。
	 * @param description
	 * 	角色描述。
	 * */
	public void setDescription(String description) {
		this.description = description;
	}
}