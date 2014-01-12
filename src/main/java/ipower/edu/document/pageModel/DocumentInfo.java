package ipower.edu.document.pageModel;

import java.util.Date;
/**
 * 公文信息。
 * @author 杨勇。
 * @since 2013-12-10。
 * */
public class DocumentInfo extends DocumentSearchInfo {
	private static final long serialVersionUID = 1L;
	private String code,fromLinkEmployee, fromLinkTel,issueCode,regCode,regEmployee,content,createUserId,createUserName,
	attachmentId,attachmentName,attachmentPath;
	private Date createDate,lastModifyDate;
	private Integer receiveCount,flag,type,status;
	
	/**
	 * 获取文件字号。
	 * @return 文件字号。
	 * */
	public String getCode() {
		return code;
	}
	/**
	 * 设置文件字号。
	 * @param code
	 * 	文件字号。
	 * */
	public void setCode(String code) {
		this.code = code;
	}
	
	
	/**
	 * 获取文件标志。
	 * @return 文件标志。
	 * */
	public Integer getFlag() {
		return flag;
	}
	/**
	 * 设置文件标志。
	 * @param flag
	 * 	文件标志。
	 * */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	/**
	 * 获取发文号。
	 * @return 发文号。
	 * */
	public String getIssueCode() {
		return issueCode;
	}
	/**
	 * 设置发文号。
	 * @param issueCode
	 * 	发文号。
	 * */
	public void setIssueCode(String issueCode) {
		this.issueCode = issueCode;
	}
	/**
	 * 获取行文登记号。
	 * @return 行文登记号。
	 * */
	public String getRegCode() {
		return regCode;
	}
	/**
	 * 设置行文登记号。
	 * @param regCode
	 * 	行文登记号。
	 * */
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	
	/**
	 * 获取收文份数。
	 * @return 收文份数。
	 * */
	public Integer getReceiveCount() {
		return receiveCount;
	}
	/**
	 * 设置收文份数。
	 * @param receiveCount
	 * 	收文份数。
	 * */
	public void setReceiveCount(Integer receiveCount) {
		this.receiveCount = receiveCount;
	}
	/**
	 * 获取登记人。
	 * @return 登记人。
	 * */
	public String getRegEmployee() {
		return regEmployee;
	}
	/**
	 * 设置登记人。
	 * @param regEmployee
	 * 	登记人。
	 * */
	public void setRegEmployee(String regEmployee) {
		this.regEmployee = regEmployee;
	}
	/**
	 * 获取来文联系人。
	 * @return 来文联系人。
	 * */
	public String getFromLinkEmployee() {
		return fromLinkEmployee;
	}
	/**
	 * 设置来文联系人。
	 * @param fromLinkEmployee
	 * 	来文联系人。
	 * */
	public void setFromLinkEmployee(String fromLinkEmployee) {
		this.fromLinkEmployee = fromLinkEmployee;
	}
	/**
	 * 获取电话。
	 * @return 电话。
	 * */
	public String getFromLinkTel() {
		return fromLinkTel;
	}
	/**
	 * 设置电话。
	 * @param fromLinkTel
	 * 	电话。
	 * */
	public void setFromLinkTel(String fromLinkTel) {
		this.fromLinkTel = fromLinkTel;
	}
	/**
	 * 获取文件内容。
	 * @return 文件内容。
	 * */
	public String getContent() {
		return content;
	}
	/**
	 * 设置文件内容。
	 * @param content
	 * 	文件内容。
	 * */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取公文类型。
	 * @return 公文类型(1-公文收发，2-公文起草)。
	 * */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置公文类型。
	 * @param type
	 * 	公文类型(1-公文收发，2-公文起草)。
	 * */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取公文状态。
	 * @return 公文状态(0-草稿,1-执行,2-归档)。
	 * */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置公文状态。
	 * @param status
	 * 	公文状态(0-草稿,1-执行,2-归档)。
	 * */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取公文创建用户ID。
	 * @return 公文创建用户ID。
	 * */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置公文创建用户ID。
	 * @param createUserId
	 * 	公文创建用户ID。
	 * */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取公文创建用户名称。
	 * @return 公文创建用户名称。
	 * */
	public String getCreateUserName() {
		return createUserName;
	}
	/**
	 * 设置公文创建用户名称。
	 * @param createUserName
	 * 	公文创建用户名称。
	 * */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	/**
	 * 获取公文创建日期。
	 * @return 公文创建日期。
	 * */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置公文创建日期。
	 * @param createDate 公文创建日期。
	 * */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取公文最后修改日期。
	 * @return 公文最后修改日期。
	 * */
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	/**
	 * 设置公文最后修改日期。
	 * @param lastModifyDate
	 * 	公文最后修改日期。
	 * */
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	/**
	 * 获取公文附件ID。
	 * @return 公文附件ID。
	 * */
	public String getAttachmentId() {
		return attachmentId;
	}
	/**
	 * 设置公文附件ID。
	 * @param attachmentId
	 * 	公文附件ID。
	 * */
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	/**
	 * 获取公文附件名。
	 * @return 公文附件名。
	 * */
	public String getAttachmentName() {
		return attachmentName;
	}
	/**
	 * 设置公文附件名。
	 * @param attachmentName
	 * 	公文附件名。
	 * */
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	/**
	 * 获取公文附件地址。
	 * @return 公文附件地址。
	 * */
	public String getAttachmentPath() {
		return attachmentPath;
	}
	/**
	 * 设置公文附件地址。
	 * @param attachmentPath
	 * 	公文附件地址。
	 * */
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
}