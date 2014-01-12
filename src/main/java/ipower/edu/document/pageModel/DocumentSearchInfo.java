package ipower.edu.document.pageModel;

import java.util.Date;

import ipower.pageModel.Paging;

/**
 * 公文检索信息。
 * @author 杨勇。
 * @since 2013-12-11。
 * */
public class DocumentSearchInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String id,receiveCode,fromUnit,title,searchName,searchValue;
	private Date receiveDate;
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
	 * 获取查询条件字段名称。
	 * @return 查询条件字段名称。
	 * */
	public String getSearchName() {
		return searchName;
	}
	/**
	 * 设置查询条件字段名称。
	 * @param searchName
	 * 	设置查询条件字段名称。
	 * */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	/**
	 * 获取查询条件字段值。
	 * @return 查询条件字段值。
	 * */
	public String getSearchValue() {
		return searchValue;
	}
	/**
	 * 设置查询条件字段值。
	 * @param searchValue
	 * 	查询条件字段值。
	 * */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
}