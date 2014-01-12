package ipower.edu.document.service;

import ipower.edu.document.dao.IDocumentDao;
import ipower.edu.document.domain.Document;
import ipower.edu.document.pageModel.DocumentInfo;

/**
 * 公文服务接口。
 * @author 杨勇。
 * @since 2013-12-10。
 * */
public interface IDocumentService extends IDataServiceBase<DocumentInfo> {
	/**
	 * 设置公文数据访问接口。
	 * @param documentDao
	 * 	公文数据访问接口。
	 * */
	void setDocumentDao(IDocumentDao documentDao);
	/**
	 * 设置公文附件服务。
	 * @param documentAttachmentService
	 * 	公文附件服务。
	 * */
	void setDocumentAttachmentService(IDocumentAttachmentService documentAttachmentService);
	/**
	 * 根据公文ID加载公文数据。
	 * @param documentId
	 * 	公文ID。
	 * @return 公文数据。
	 * */
	Document loadDocument(String documentId);
}