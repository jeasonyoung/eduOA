package ipower.org.action;

import java.io.IOException;

import ipower.org.pageModel.UserRoleInfo;
import ipower.org.service.IUserRoleService;

/**
 * 用户角色Action。
 * @author 杨勇。
 * @since 2013-12-09。
 * */
public class UserRoleAction extends BaseDataAction<UserRoleInfo> {
	private UserRoleInfo info = new UserRoleInfo();
	@Override
	public UserRoleInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}
	
	public void roles() throws IOException{
		if (this.service instanceof IUserRoleService) {
			this.writeJson(((IUserRoleService)this.service).loadRoleIds(this.getModel().getId()));
		}
	}
}