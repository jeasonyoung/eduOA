package ipower.org.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 组织部门。
 * @author 杨勇。
 * @since 2013-12-02。
 * */
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	private String departmentId,departmentName;
	private Integer departmentOrder;
	private Department parent;
	private Set<Department> childrens;
	/**
	 * 构造函数。
	 * */
	public Department(){
		this.setChildrens(new HashSet<Department>());
	}
	/**
	 * 获取部门ID。
	 * @return 部门ID。
	 * */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * 设置部门ID。
	 * @param departmentId;
	 * 	部门ID。
	 * */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * 获取部门名称。
	 * @return 部门名称。
	 * */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * 设置部门名称。
	 * @param departmentName
	 * 	部门名称。
	 * */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * 获取部门排序。
	 * @return 部门排序。
	 * */
	public Integer getDepartmentOrder() {
		return departmentOrder;
	}
	/**
	 * 设置部门排序。
	 * @param departmentOrder
	 * 	部门排序。
	 * */
	public void setDepartmentOrder(Integer departmentOrder) {
		this.departmentOrder = departmentOrder;
	}
	/**
	 * 获取上级部门。
	 * @return 上级部门。
	 * */
	public Department getParent() {
		return parent;
	}
	/**
	 * 设置上级部门。
	 * @param parent
	 * 	上级部门。
	 * */
	public void setParent(Department parent) {
		this.parent = parent;
	}
	/**
	 * 获取下级部门集合。
	 * @return 下级部门集合。
	 * */
	public Set<Department> getChildrens() {
		return childrens;
	}
	/**
	 * 设置下级部门集合。
	 * @param childs
	 * 	下级部门集合。
	 * */
	public void setChildrens(Set<Department> childrens) {
		this.childrens = childrens;
	}
}