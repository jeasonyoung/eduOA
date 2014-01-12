package ipower.edu.document.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 公文附件。
 * @author 杨勇。
 * @since 2013-12-10。
 * */
public class DocumentAttachment implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,name,path;
	private Date create;
	private Document document;
	/**
	 * 构造函数。
	 * */
	public DocumentAttachment(){
		this.setCreate(new Date());
	}
	/**
	 * 获取公文。
	 * @return 获取公文。
	 * */
	public Document getDocument() {
		return document;
	}
	/**
	 * 设置公文。
	 * @param document
	 * 	公文。
	 * */
	public void setDocument(Document document) {
		this.document = document;
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