package ipower.edu.document.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.edu.document.dao.IDocumentAttachmentDao;
import ipower.edu.document.domain.Document;
import ipower.edu.document.domain.DocumentAttachment;
import ipower.edu.document.pageModel.DocumentAttachmentInfo;
import ipower.edu.document.service.IDocumentAttachmentService;
import ipower.edu.document.service.IDocumentService;

/**
 * 公文附件服务实现类。
 * @author 杨勇。
 * @since 2013-12-26。
 * */
public class DocumentAttachmentServiceImpl extends DataServiceBaseImpl<DocumentAttachment,DocumentAttachmentInfo> implements IDocumentAttachmentService {
	private IDocumentAttachmentDao documentAttachmentDao;
	private IDocumentService documentService;
	private String storeFolder;
	
	@Override
	public void setDocumentAttachmentDao(IDocumentAttachmentDao documentAttachmentDao) {
		this.documentAttachmentDao = documentAttachmentDao;
	}

	@Override
	public void setDocumentService(IDocumentService documentService) {
		this.documentService = documentService;
	}

	@Override
	public void setStoreFolder(String storeFolder) {
		this.storeFolder = storeFolder;
	}
	
	@Override
	protected List<DocumentAttachment> find(DocumentAttachmentInfo info) {
		String hql = "from DocumentAttachment d where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql =  this.addWhere(info, hql, parameters);
		return this.documentAttachmentDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected DocumentAttachmentInfo changeModel(DocumentAttachment data) {
		DocumentAttachmentInfo info = new DocumentAttachmentInfo();
		if(data != null){
			BeanUtils.copyProperties(data, info);
			if(data.getDocument() != null){
				info.setDocumentId(data.getDocument().getId());
				info.setDocumentTitle(data.getDocument().getTitle());
			}
		}
		return info;
	}

	@Override
	protected Long total(DocumentAttachmentInfo info) {
		String hql = "select count(*) from DocumentAttachment d where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql =  this.addWhere(info, hql, parameters);
		return this.documentAttachmentDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(DocumentAttachmentInfo info, String hql,Map<String, Object> parameters) {
		if(info.getDocumentId() != null && !info.getDocumentId().trim().isEmpty()){
			hql += " and d.document.id = :documentId ";
			parameters.put("documentId", info.getDocumentId());
		}
		if(info.getId() != null && !info.getId().trim().isEmpty()){
			hql += " and d.id = :id ";
			parameters.put("id", info.getId());
		}
		if(info.getName() != null && !info.getName().trim().isEmpty()){
			hql += " and d.name like :name ";
			parameters.put("name", "%" + info.getName() + "%");
		}
		return hql;
	}

	@Override
	public DocumentAttachmentInfo update(DocumentAttachmentInfo info) {
		if(info != null){
			boolean isAdded = false;
			DocumentAttachment data = (info.getId() == null || info.getId().trim().isEmpty()) ? null : this.documentAttachmentDao.load(DocumentAttachment.class, info.getId());
			if(isAdded = (data == null)){
				data = new DocumentAttachment();
				if(info.getId() == null || info.getId().trim().isEmpty()) info.setId(UUID.randomUUID().toString());
			}
			BeanUtils.copyProperties(info, data);
			if(this.storeFolder != null && !this.storeFolder.trim().isEmpty() && info.getPath() != null){
				if(!info.getPath().startsWith(this.storeFolder)){
					info.setPath(this.formatAttachmentPath(info.getPath()));
				}
				if(data.getPath() != null && data.getPath().startsWith(this.storeFolder)){
					data.setPath(this.removeFormatAttachmentPath(data.getPath()));
				}
			}
			if(info.getDocumentId() != null && (data.getDocument() == null || !data.getDocument().getId().equalsIgnoreCase(info.getDocumentId()))){
				Document document = this.documentService.loadDocument(info.getDocumentId());
				if(document != null){
					data.setDocument(document);
					info.setDocumentTitle(document.getTitle());
				}
			}
			if(isAdded)this.documentAttachmentDao.save(data);
		}
		return info;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			DocumentAttachment data = this.documentAttachmentDao.load(DocumentAttachment.class, ids[i]);
			if(data != null)this.documentAttachmentDao.delete(data);
		}
	}

	@Override
	public List<DocumentAttachment> loaDocumentAttachments(String documentId) {
		final String hql = "from DocumentAttachment d where d.document.id = :documentId";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("documentId", documentId);
		return this.documentAttachmentDao.find(hql, parameters, null, null);
	}

	@Override
	public DocumentAttachment loadAttachment(String attachmentId) {
		if(attachmentId == null || attachmentId.trim().isEmpty()) return null;
		return this.documentAttachmentDao.load(DocumentAttachment.class, attachmentId);
	}

	@Override
	public String formatAttachmentPath(String path) {
		if(this.storeFolder != null && !this.storeFolder.trim().isEmpty() && path != null && !path.startsWith(this.storeFolder)){
			return this.storeFolder + "/" + path;
		}
		return path;
	}
	private String removeFormatAttachmentPath(String path){
		if(this.storeFolder != null && !this.storeFolder.trim().isEmpty() && path != null && path.startsWith(this.storeFolder)){
			return path.replaceFirst(this.storeFolder, "").trim();
		}
		return path;
	}
}