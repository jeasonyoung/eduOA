package ipower.org.action;

import java.io.IOException;

import ipower.org.pageModel.PostInfo;
import ipower.org.service.IPostService;

public class PostAction extends BaseDataAction<PostInfo> {
	private PostInfo data = new PostInfo();
	
	@Override
	public PostInfo getModel() {
		return this.data;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}
	
	public void all() throws IOException{
		if(this.service instanceof IPostService){
			this.writeJson(((IPostService)this.service).allPosts());
		}
	}
}