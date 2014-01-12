package ipower.org.pageModel;

import ipower.pageModel.Paging;

/**
 * 用户角色信息。
 * @author 杨勇。
 * @since 2013-12-09。
 * */
public class UserRoleInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,name,roleNames;
	private String[] roleIds;
	/**
	 * 获取用户ID。
	 * @return 用户ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置用户ID。
	 * @param id
	 * 	用户ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取用户名。
	 * @return 用户名。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置用户名。
	 * @param name 
	 * 	用户名。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取角色名。
	 * @return 角色名。
	 * */
	public String getRoleNames() {
		return roleNames;
	}
	/**
	 * 设置角色名。
	 * @param roleNames
	 * 角色名。
	 * */
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	/**
	 * 获取角色ID集合。
	 * @return 角色ID集合。
	 * */
	public String[] getRoleIds() {
		return roleIds;
	}
	/**
	 * 设置角色ID集合。
	 * @param roleIds
	 * 	角色ID集合。
	 * */
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
}