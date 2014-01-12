package ipower.edu.document.pageModel;

import ipower.pageModel.Paging;

import java.util.Date;
/**
 * 公文附件信息。
 * @author 杨勇。
 * @since 2013-12-10。
 * */
public class DocumentAttachmentInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,name,path,documentId,documentTitle;
	private Date create;
	/**
	 * 构造函数。
	 * */
	public DocumentAttachmentInfo(){
		this.create = new Date();
	}
	/**
	 * 构造函数。
	 * @param id
	 * 	公文附件ID。
	 * @param name
	 * 	公文附件名。
	 * @param path
	 * 	附件路径。
	 * */
	public DocumentAttachmentInfo(String id,String name, String path){
		this();
		this.id = id;
		this.name = name;
		this.path = path;
	}
	
	/**
	 * 获取所属公文ID。
	 * @return 所属公文ID。
	 * */
	public String getDocumentId() {
		return documentId;
	}
	/**
	 * 设置所属公文ID。
	 * @param documentId
	 * 	所属公文ID。
	 * */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	/**
	 * 获取公文标题。
	 * @return 获取公文标题。
	 * */
	public String getDocumentTitle() {
		return documentTitle;
	}
	/**
	 * 设置公文标题。
	 * @param documentTitle
	 * 	公文标题。
	 * */
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	/**
	 * 获取附件ID。
	 * @return 附件ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置附件ID。
	 * @param id
	 * 	附件ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取附件文件名。
	 * @return 附件文件名。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置附件文件名。
	 * @param name 附件文件名。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取附件存储路径。
	 * @return 附件存储路径。
	 * */
	public String getPath() {
		return path;
	}
	/**
	 * 设置附件存储路径。
	 * @param path
	 * 	附件存储路径。
	 * */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 获取附件上传时间。
	 * @return 附件上传时间。
	 * */
	public Date getCreate() {
		return create;
	}
	/**
	 * 设置附件上传时间。
	 * @param create
	 * 	附件上传时间。
	 * */
	public void setCreate(Date create) {
		this.create = create;
	}
}