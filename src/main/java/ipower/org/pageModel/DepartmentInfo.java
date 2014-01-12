package ipower.org.pageModel;

import ipower.pageModel.Paging;
/**
 * 组织部门信息。
 * @author 杨勇。
 * @since 2013-12-02。
 * */
public class DepartmentInfo extends Paging {
	private static final long serialVersionUID = 1L;
	private String  parentDepartmentId, departmentId,departmentName,fullDepartmentName;
	private Integer departmentOrder;
	/**
	 * 获取上级组织部门ID。
	 * @return 上级组织部门ID。
	 * */
	public String getParentDepartmentId() {
		return parentDepartmentId;
	}
	/**
	 * 设置上级组织部门ID。
	 * @param parentDepartmentId
	 * 	上级组织部门ID。
	 * */
	public void setParentDepartmentId(String parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}
	/**
	 * 获取组织部门ID。
	 * @return 组织部门ID。
	 * */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * 设置组织部门ID。
	 * @param departmentId
	 * 	组织部门ID。
	 * */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * 获取组织部门名称。
	 * @return 组织部门名称。
	 * */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * 设置组织部门名称。
	 * @param departmentName
	 * 	组织部门名称。
	 * */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * 获取组织部门全名称。
	 * @return 组织部门全名称。
	 * */
	public String getFullDepartmentName() {
		return fullDepartmentName;
	}
	/**
	 * 设置组织部门全名称。
	 * @param fullDepartmentName
	 * 	组织部门全名称。
	 * */
	public void setFullDepartmentName(String fullDepartmentName) {
		this.fullDepartmentName = fullDepartmentName;
	}
	/**
	 * 获取组织部门排序。
	 * @return 组织部门排序。
	 * */
	public Integer getDepartmentOrder() {
		return departmentOrder;
	}
	/**
	 * 设置组织部门排序。
	 * @param departmentOrder
	 * 	组织部门排序。
	 * */
	public void setDepartmentOrder(Integer departmentOrder) {
		this.departmentOrder = departmentOrder;
	}
}