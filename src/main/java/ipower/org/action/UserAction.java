package ipower.org.action;

import java.io.IOException;

import ipower.org.pageModel.UserInfo;
import ipower.org.service.IUserService;

/**
 * 用户Action。
 * @author 杨勇。
 * @since 2013-12-04。
 * */
public class UserAction extends BaseDataAction<UserInfo> {
	private UserInfo info = new UserInfo();
	
	@Override
	public UserInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}
	
	public void all() throws IOException{
		if(this.service instanceof IUserService){
			this.writeJson(((IUserService)this.service).allUsers(this.getModel().getName()));
		}
	}
}