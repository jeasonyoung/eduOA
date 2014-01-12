package ipower.edu.document.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import ipower.edu.document.pageModel.DocumentInfo;
import ipower.edu.document.pageModel.DocumentSearchInfo;
import ipower.edu.document.service.IDocumentSearchService;
import ipower.edu.document.service.IDocumentService;
import ipower.pageModel.DataGrid;

/**
 * 公文检索服务实现类。
 * @author 杨勇。
 * @since 2013-12-11。
 * */
public class DocumentSearchServiceImpl implements IDocumentSearchService {
	private IDocumentService documentService;
	@Override
	public void setDocumentService(IDocumentService documentService) {
		this.documentService = documentService;
	}

	@Override
	public DataGrid<DocumentSearchInfo> datagrid(DocumentSearchInfo info) {
		DataGrid<DocumentSearchInfo> grid = new DataGrid<DocumentSearchInfo>();
		if(info != null){
			DocumentInfo filter = new DocumentInfo();
			BeanUtils.copyProperties(info, filter);
			DataGrid<DocumentInfo> sources = this.documentService.datagrid(filter);
			if(sources != null){
				List<DocumentSearchInfo> rows = new ArrayList<>();
				grid.setTotal(sources.getTotal());
				for(DocumentInfo doc : sources.getRows()){
					DocumentSearchInfo target = new DocumentSearchInfo();
					BeanUtils.copyProperties(doc, target);
					rows.add(target);
				}
				grid.setRows(rows);
			}
		}
		return grid;
	}

}