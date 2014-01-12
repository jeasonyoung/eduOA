package ipower.edu.document.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 公文。
 * @author 杨勇。
 * @since 2013-12-10。
 * */
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,receiveCode,code,title,fromUnit,fromLinkEmployee,
			fromLinkTel,issueCode,regCode,regEmployee,content,createUserId,createUserName;
	private Date receiveDate,createDate,lastModifyDate;
	private Integer receiveCount,flag,status,type;
	private Set<DocumentAttachment> attachments;
	private Set<DocumentRecord> records;
	/**
	 * 获取公文ID。
	 * @return 公文ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置公文ID。
	 * @return id
	 * 	公文ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取收文登记号。
	 * @return 收文登记号。
	 * */
	public String getReceiveCode() {
		return receiveCode;
	}
	/**
	 * 设置收文登记号。
	 * @param receiveCode
	 * 	收文登记号。
	 * */
	public void setReceiveCode(String receiveCode) {
		this.receiveCode = receiveCode;
	}
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
	 * 获取文件标题。
	 * @return 文件标题。
	 * */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置文件标题。
	 * @param title
	 * 	文件标题。
	 * */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取来文单位。
	 * @return 来文单位。
	 * */
	public String getFromUnit() {
		return fromUnit;
	}
	/**
	 * 设置来文单位。
	 * @param fromUnit
	 * 	来文单位。
	 * */
	public void setFromUnit(String fromUnit) {
		this.fromUnit = fromUnit;
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
	 * 获取收文日期。
	 * @return 收文日期。
	 * */
	public Date getReceiveDate() {
		return receiveDate;
	}
	/**
	 * 设置收文日期。
	 * @param receiveDate
	 * 	收文日期。
	 * */
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
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
	 * 获取公文附件。
	 * @return 公文附件。
	 * */
	public Set<DocumentAttachment> getAttachments() {
		return attachments;
	}
	/**
	 * 设置公文附件集合。
	 * @param attachments
	 * 	公文附件集合。
	 * */
	public void setAttachments(Set<DocumentAttachment> attachments) {
		this.attachments = attachments;
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
	 * 获取办理记录。
	 * @return 办理记录。
	 * */
	public Set<DocumentRecord> getRecords() {
		return records;
	}
	/**
	 * 设置办理记录。
	 * @param records
	 * 	办理记录。
	 * */
	public void setRecords(Set<DocumentRecord> records) {
		this.records = records;
	}
}