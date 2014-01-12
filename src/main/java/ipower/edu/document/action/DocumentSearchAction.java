package ipower.edu.document.action;

import java.io.IOException;

import ipower.edu.document.pageModel.DocumentSearchInfo;
import ipower.edu.document.service.IDocumentSearchService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 公文检索Action。
 * @author 杨勇。
 * @since 2013-12-11。
 * */
public class DocumentSearchAction extends BaseAction implements ModelDriven<DocumentSearchInfo> {
	private DocumentSearchInfo info = new DocumentSearchInfo();
	private IDocumentSearchService searchService;
	/**
	 * 设置公文检索服务接口。
	 * @param searchService
	 * 	公文检索服务接口。
	 * */
	public void setSearchService(IDocumentSearchService searchService) {
		this.searchService = searchService;
	}

	@Override
	public DocumentSearchInfo getModel() {
		return this.info;
	}
	
	/**
	 * 列表数据。 
	 * @throws IOException 
	 * */
	public void datagrid() throws IOException{
		this.writeJson(this.searchService.datagrid(this.getModel()));
	}

}