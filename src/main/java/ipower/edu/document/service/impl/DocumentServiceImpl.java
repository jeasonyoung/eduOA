package ipower.edu.document.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.edu.document.dao.IDocumentDao;
import ipower.edu.document.domain.Document;
import ipower.edu.document.domain.DocumentAttachment;
import ipower.edu.document.pageModel.DocumentInfo;
import ipower.edu.document.service.IDocumentAttachmentService;
import ipower.edu.document.service.IDocumentService;

/**
 * 公文服务实现类。
 * @author 杨勇。
 * @since 2013-12-10。
 * */
public class DocumentServiceImpl extends DataServiceBaseImpl<Document,DocumentInfo> implements IDocumentService {
	private IDocumentDao documentDao;
	private IDocumentAttachmentService documentAttachmentService;
	/**
	 * 设置公文数据访问。
	 * @param documentDao
	 * 	公文数据访问。
	 * */
	@Override
	public void setDocumentDao(IDocumentDao documentDao) {
		this.documentDao = documentDao;
	}
	
	@Override
	public void setDocumentAttachmentService(IDocumentAttachmentService documentAttachmentService) {
		this.documentAttachmentService = documentAttachmentService;
	}

	@Override
	protected List<Document> find(DocumentInfo info) {
		String hql = "from Document d where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		hql += " order by ";
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql += " d."+ info.getSort() +" "+ info.getOrder() + ",";
		}
		hql += " d.receiveDate desc,d.createDate desc,d.lastModifyDate desc";
		return this.documentDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected DocumentInfo changeModel(Document data) {
		DocumentInfo info = new DocumentInfo();
		if(data != null){
			BeanUtils.copyProperties(data, info);
			if(data.getAttachments() != null && data.getAttachments().size() > 0){
				for(DocumentAttachment attachment : data.getAttachments()){
					info.setAttachmentId(attachment.getId());
					info.setAttachmentName(attachment.getName());
					if(this.documentAttachmentService != null){
						info.setAttachmentPath(this.documentAttachmentService.formatAttachmentPath(attachment.getPath()));
					}
					else {
						info.setAttachmentPath(attachment.getPath());
					}
					break;
				}
			}
		}
		return info;
	}

	@Override
	protected Long total(DocumentInfo info) {
		String hql = "select count(*) from Document d where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.documentDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(DocumentInfo info, String hql, Map<String, Object> parameters) {
		//公文类型。
		if(info.getType() != null && info.getType() > -1){
			hql += " and d.type = :type ";
			parameters.put("type", info.getType());
		}
		//公文状态。
		if(info.getStatus() != null && info.getStatus() > -1){
			hql += " and d.status = :status ";
			parameters.put("status", info.getStatus());
		}
		//按条件检索数据。
		if(info.getSearchName() != null && !info.getSearchName().trim().isEmpty()){
			hql += " and d."+ info.getSearchName() + " like :value";
			parameters.put("value", "%" + info.getSearchValue() + "%");
		}
		//收文登记号
		if(info.getReceiveCode() != null && !info.getReceiveCode().trim().isEmpty()){
			hql += " and d.receiveCode like :receiveCode ";
			parameters.put("receiveCode", "%" + info.getReceiveCode() + "%");
		}
		//文件字号
		if(info.getCode() != null && !info.getCode().trim().isEmpty()){
			hql += " and d.code like :code ";
			parameters.put("code", "%" + info.getCode() + "%");
		}
		//文件标题
		if(info.getTitle() != null && !info.getTitle().trim().isEmpty()){
			hql += " and d.title like :title ";
			parameters.put("title", "%" + info.getTitle() + "%");
		}
		//发文号
		if(info.getIssueCode() != null && !info.getIssueCode().trim().isEmpty()){
			hql += " and d.issueCode like :issueCode ";
			parameters.put("issueCode", "%" + info.getIssueCode() + "%");
		}
		//行文登记号
		if(info.getRegCode() != null && !info.getRegCode().trim().isEmpty()){
			hql += " and d.regCode like :regCode ";
			parameters.put("regCode", "%" + info.getRegCode() + "%");
		}
		//登记人
		if(info.getRegEmployee() != null && !info.getRegEmployee().trim().isEmpty()){
			hql += " and d.regEmployee like :regEmployee ";
			parameters.put("regEmployee", "%" + info.getRegEmployee() + "%");
		}
		//来文单位
		if(info.getFromUnit() != null && !info.getFromUnit().trim().isEmpty()){
			hql += " and d.fromUnit like :fromUnit ";
			parameters.put("fromUnit", "%" + info.getFromUnit() + "%");
		}
		//来文联系人
		if(info.getFromLinkEmployee() != null && !info.getFromLinkEmployee().trim().isEmpty()){
			hql += " and d.fromLinkEmployee like :fromLinkEmployee ";
			parameters.put("fromLinkEmployee", "%" + info.getFromLinkEmployee() + "%");
		}
		//电话
		if(info.getFromLinkTel() != null && !info.getFromLinkTel().trim().isEmpty()){
			hql += " and d.fromLinkTel like :fromLinkTel ";
			parameters.put("fromLinkTel", "%" + info.getFromLinkTel() + "%");
		}
		//公文所有者。
		if(info.getCreateUserId() != null && !info.getCreateUserId().trim().isEmpty()){
			hql += " and d.createUserId = :createUserId ";
			parameters.put("createUserId", info.getCreateUserId());
		}
		return hql;
	}

	@Override
	public DocumentInfo update(DocumentInfo info) {
		if(info != null){
			boolean isAdded = false;
			Document data = (info.getId() == null || info.getId().trim().isEmpty()) ? null : this.documentDao.load(Document.class, info.getId());
			if(isAdded = (data == null)){
				info.setId(UUID.randomUUID().toString());
				data = new Document();
			}
			BeanUtils.copyProperties(info, data);
			if(info.getAttachmentId() != null && (data.getAttachments() == null || !this.existsAttachments(data.getAttachments(), info.getAttachmentId()))){
				DocumentAttachment attachment = this.documentAttachmentService.loadAttachment(info.getAttachmentId());
				if(attachment != null){
					info.setAttachmentName(attachment.getName());
					if(this.documentAttachmentService != null){
						info.setAttachmentPath(this.documentAttachmentService.formatAttachmentPath(attachment.getPath()));
					}else{
						info.setAttachmentPath(attachment.getPath());
					}
					
					if(data.getAttachments() == null) data.setAttachments(new HashSet<DocumentAttachment>());
					data.getAttachments().add(attachment);
				}
			}	
			if(isAdded)this.documentDao.save(data);
		}
		return info;
	}
	private boolean existsAttachments(Set<DocumentAttachment> attachments, String attachmentId){
		if(attachments == null || attachments.size() == 0 || attachmentId == null || attachmentId.trim().isEmpty()) return false;
		for(DocumentAttachment data : attachments){
			if(data != null && data.getId().equalsIgnoreCase(attachmentId)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(int i = 0; i < ids.length; i++){
			Document doc = this.documentDao.load(Document.class, ids[i]);
			if(doc != null) this.documentDao.delete(doc);
		}
	}

	@Override
	public Document loadDocument(String documentId) {
		if(documentId == null || documentId.trim().isEmpty()) return null;
		return this.documentDao.load(Document.class, documentId);
	}
}