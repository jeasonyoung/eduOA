package ipower.edu.document.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 公文记录。
 * @author 杨勇。
 * @since 2013-12-10。
 * */
public class DocumentRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,userId,userName,content;
	private Integer type;
	private Date time;
	private Document document;
	/**
	 * 获取公文。
	 * @return 公文。
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
	 * 获取记录ID。
	 * @return 记录ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置记录ID。
	 * @param id
	 * 	记录ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取记录类型。
	 * @return 记录类型。
	 * */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置记录类型。
	 * @param type
	 * 	记录类型。
	 * */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取操作用户ID。
	 * @return 操作用户ID。
	 * */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置操作用户ID。
	 * @param userId
	 * 	操作用户ID。
	 * */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取操作用户名称。
	 * @return 操作用户名称。
	 * */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置操作用户名称。
	 * @param userName 
	 * 	操作用户名称。
	 * */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取记录内容。
	 * @return 记录内容。
	 * */
	public String getContent() {
		return content;
	}
	/**
	 * 设置记录内容。
	 * @param content
	 * 	记录内容。
	 * */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取记录时间。
	 * @return 记录时间。
	 * */
	public Date getTime() {
		return time;
	}
	/**
	 * 设置记录时间。
	 * @param time
	 * 	记录时间。
	 * */
	public void setTime(Date time) {
		this.time = time;
	}
}