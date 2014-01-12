package ipower.org.dao;

import ipower.org.domain.RoleMenu;

/**
 * 角色菜单数据访问接口。
 * @author 杨勇。
 * @since 2013-12-07。
 * */
public interface IRoleMenuDao extends IDaoBase<RoleMenu> {
	/**
	 * 删除角色菜单。
	 * @param roleIds
	 * 	角色ID集合。
	 * */
	boolean delete(String[] roleIds);
}