package ipower.edu.service;

import ipower.configuration.ModuleSystem;
import ipower.pageModel.TreeNode;

import java.util.List;
/**
 * 菜单服务接口。
 * @author 杨勇.
 * @since 2013-11-27.
 * */
public interface IMenuService {
	/**
	 * 设置主菜单文件。
	 * @param moduleFile
	 * 	菜单文件。
	 * */
	void setMenuFile(String menuFile);
	/**
	 * 获取菜单数据。
	 * @param systemId
	 * 	系统ID。
	 * @return
	 * 	菜单数据。
	 * */
	List<TreeNode> tree(String systemId);
	/**
	 * 根据系统ID获取系统信息。
	 * @param systemId
	 * 	系统ID。
	 * @return 系统信息。
	 * */
	ModuleSystem loadModuleSystem(String systemId);
	/**
	 * 加载菜单名称。
	 * @param menuId
	 * 	菜单ID。
	 * @return 菜单名称。
	 * */
	String loadMenuName(String menuId);
}