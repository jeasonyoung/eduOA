package ipower.edu.document.service;

import ipower.edu.document.pageModel.DocumentSearchInfo;
import ipower.pageModel.DataGrid;

/**
 * 公文检索服务接口。
 * @author 杨勇。
 * @since 2013-12-11。
 * */
public interface IDocumentSearchService {
	/**
	 * 设置公文服务接口。
	 * @param documentDao
	 * 	公文服务接口。
	 * */
	void setDocumentService(IDocumentService documentService);
	/**
	 * 加载列表数据。
	 * @param info
	 * 	查询条件
	 * @return 列表数据。
	 * */
	DataGrid<DocumentSearchInfo> datagrid(DocumentSearchInfo info);
}