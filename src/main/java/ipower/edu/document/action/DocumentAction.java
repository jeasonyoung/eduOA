package ipower.edu.document.action;

import ipower.edu.document.pageModel.DocumentInfo;

/**
 * 公文Action。
 * @author 杨勇。
 * @since 2013-12-11。
 * */
public class DocumentAction extends BaseDataAction<DocumentInfo>  {
	private DocumentInfo info = new DocumentInfo();
	
	@Override
	public DocumentInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.info.getId();
	}
}