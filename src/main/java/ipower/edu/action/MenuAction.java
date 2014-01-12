package ipower.edu.action;

import java.io.IOException;

import ipower.edu.service.IMenuService;

/**
 * 主菜单Action.
 * @author 杨勇.
 * @since 2013-11-27.
 * */
public class MenuAction extends BaseAction {
	private IMenuService menuService;
	private String systemId;
	/**
	 * 设置菜单服务。
	 * @param menuService
	 * 	菜单服务。
	 * */
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	/**
	 * 设置系统ID。
	 * @param systemId
	 * 	系统菜单。
	 * */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	/**
	 * 菜单格式数据。
	 * @throws IOException 
	 * */
	public void tree() throws IOException{
		if(this.menuService != null){
			this.writeJson(this.menuService.tree(this.systemId));
		}
	}
}