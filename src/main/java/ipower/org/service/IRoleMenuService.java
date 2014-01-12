package ipower.org.service;

import ipower.edu.service.IMenuService;
import ipower.org.dao.IRoleDao;
import ipower.org.dao.IRoleMenuDao;
import ipower.org.pageModel.RoleMenuInfo;

/**
 * 角色菜单服务接口。
 * @author 杨勇。
 * @since 2013-12-07。
 * */
public interface IRoleMenuService  extends IDataServiceBase<RoleMenuInfo> {
	/**
	 * 设置角色菜单数据接口。
	 * @param roleMenuDao
	 * 	角色菜单数据接口。
	 * */
	void setRoleMenuDao(IRoleMenuDao roleMenuDao);
	/**
	 * 设置角色数据接口。
	 * @param roleDao
	 * 	角色数据接口。
	 * */
	void setRoleDao(IRoleDao roleDao);
	/**
	 * 设置菜单服务接口。
	 * @param menuService
	 * 	菜单服务接口。
	 * */
	void setMenuService(IMenuService menuService);
	/**
	 * 加载菜单ID集合。
	 * @param roleId
	 * 	角色ID。
	 * @return 菜单ID集合。
	 * */
	String[] loadMenus(String roleId);
}