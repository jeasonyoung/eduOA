package ipower.edu.document.service;

import java.util.List;

import ipower.edu.document.dao.IDocumentAttachmentDao;
import ipower.edu.document.domain.DocumentAttachment;
import ipower.edu.document.pageModel.DocumentAttachmentInfo;

/**
 * 公文附件服务接口。
 * @author 杨勇。
 * @since 2013-12-26。
 * */
public interface IDocumentAttachmentService extends IDataServiceBase<DocumentAttachmentInfo> {
	/**
	 * 设置公文附件数据访问接口。
	 * @param documentAttachmentDao
	 * 	公文附件数据访问接口。
	 * */
	void setDocumentAttachmentDao(IDocumentAttachmentDao documentAttachmentDao);
	/**
	 * 设置公文服务。
	 * @param documentService
	 * 	公文服务。
	 * */
	void setDocumentService(IDocumentService documentService);
	/**
	 * 设置附件存储根目录。
	 * @param storeFolder
	 *  附件存储根目录。
	 * */
	void setStoreFolder(String storeFolder);
	/**
	 * 格式化附件路径。
	 * @param path
	 * 	格式化前附件路径。
	 * @return 格式化后的附件路径。
	 * */
	String formatAttachmentPath(String path);
	/**
	 * 根据公文ID加载附件集合。
	 * @param documentId
	 * 	公文ID。
	 * @return 附件集合。
	 * */
	List<DocumentAttachment> loaDocumentAttachments(String documentId);
	/**
	 * 根据附件ID加载附件数据。
	 * @param attachmentId
	 * 	附件ID。
	 * @return 附件数据。
	 * */
	DocumentAttachment loadAttachment(String attachmentId);
}