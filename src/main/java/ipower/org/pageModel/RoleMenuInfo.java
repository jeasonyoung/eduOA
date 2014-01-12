package ipower.org.pageModel;

import ipower.pageModel.Paging;

/**
 * 角色菜单信息。
 * @author 杨勇。
 * @since 2013-12-07。
 * */
public class RoleMenuInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,name,menuNames;
	private String[] menuIds;
	/**
	 * 获取角色ID。
	 * @return 角色ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置角色ID。
	 * @param roleId
	 * 	角色ID。
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
	 * @param roleName
	 * 	角色名称。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取菜单名称。
	 * @return 菜单名称。
	 * */
	public String getMenuNames() {
		return menuNames;
	}
	/**
	 * 设置菜单名称。
	 * @param menuNames
	 * 	菜单名称。
	 * */
	public void setMenuNames(String menuNames) {
		this.menuNames = menuNames;
	}
	/**
	 * 获取菜单ID数组。
	 * @return 菜单ID数组。
	 * */
	public String[] getMenuIds() {
		return menuIds;
	}
	/**
	 * 设置菜单ID组数
	 * @param menuIds
	 * 	菜单数组。
	 * */
	public void setMenuIds(String[] menuIds) {
		this.menuIds = menuIds;
	}
	
}