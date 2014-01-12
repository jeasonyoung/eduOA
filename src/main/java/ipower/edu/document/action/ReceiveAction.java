package ipower.edu.document.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import ipower.edu.document.pageModel.DocumentInfo;
import ipower.edu.document.pageModel.IUserInfo;

/**
 * 公文收发ACTION。
 * @author 杨勇。
 * @since 2013-12-19。 
 * */
public class ReceiveAction  extends BaseDataAction<DocumentInfo> implements SessionAware {
	private DocumentInfo info = new DocumentInfo();
	private Map<String, Object> session = new HashMap<>();
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@Override
	public DocumentInfo getModel() {
		IUserInfo user = (IUserInfo)this.session.get("user");
		if(user != null) this.info.setCreateUserId(user.getUserId());
		this.info.setType(1);
		return this.info;
	}
	
	@Override
	public void update() throws IOException{
		super.update();
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}

}