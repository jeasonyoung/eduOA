package ipower.org.action;

import java.io.IOException;

import ipower.org.pageModel.RoleMenuInfo;
import ipower.org.service.IRoleMenuService;

/**
 * 角色菜单Action。
 * @author 杨勇。
 * @since 2013-12-07。
 * */
public class RoleMenuAction extends BaseDataAction<RoleMenuInfo> {
	private RoleMenuInfo info = new RoleMenuInfo();
	@Override
	public RoleMenuInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}
	
	public void menus() throws IOException{
		if(this.service instanceof IRoleMenuService){
			this.writeJson(((IRoleMenuService)this.service).loadMenus(this.getModel().getId()));
		}
	}
}