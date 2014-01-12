package ipower.edu.action;

import ipower.configuration.ModuleSystem;
import ipower.edu.action.BaseAction;
import ipower.edu.service.IMenuService;

/**
 * 默认页面Action.
 * @author 杨勇.
 * @since 2013-11-27.
 * */
public class IndexAction extends BaseAction {
	private String systemId,systemName;
	private IMenuService menuService;
	/**
	 * 设置菜单服务接口。
	 * @param menuService
	 * 	菜单服务接口。
	 * */
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	/**
	 * 获取系统ID。
	 * @return 系统ID。
	 * */
	public String getSystemId() {
		return systemId;
	}
	/**
	 * 设置系统ID。
	 * @param systemId
	 * 	系统ID。
	 * */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	/**
	 * 获取系统名称。
	 * @return 系统名称。
	 * */
	public String getSystemName() {
		return systemName;
	}
	/**
	 * 默认输出。
	 * */
	@Override
	public String execute() throws Exception{
		if(this.menuService != null && this.systemId != null && !this.systemId.isEmpty()){
			ModuleSystem ms = this.menuService.loadModuleSystem(this.systemId);
			if(ms != null) this.systemName = ms.getName();
		}
		return SUCCESS;
	}
	/**
	 * 顶部Banner头。
	 * */
	public String top(){
		return "top";
	}
	/**
	 * 左边菜单。
	 * */
	public String leftmenu(){
		return "leftmenu";
	}
	/**
	 * 中间工作区域。
	 * */
	public String workspace(){
		return "workspace";
	}
	/**
	 * 底部footer。
	 * */
	public String footer(){
		return "footer";
	}
}