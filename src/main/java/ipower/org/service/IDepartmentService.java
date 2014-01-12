package ipower.org.service;

import java.util.List;

import ipower.org.dao.IDepartmentDao;
import ipower.org.domain.Department;
import ipower.org.pageModel.DepartmentInfo;
import ipower.pageModel.TreeNode;

/**
 * 组织部门服务接口。
 * @author 杨勇。
 * @since 2013-12-02。
 * */
public interface IDepartmentService extends IDataServiceBase<DepartmentInfo> {
	/**
	 * 设置部门数据访问。
	 * @param departmentDao
	 * 	部门数据访问。
	 * */
	void setDepartmentDao(IDepartmentDao departmentDao);
	/**
	 * 加载部门数据。
	 * @param departmentId
	 * 	部门ID
	 * @return 部门数据。
	 * */
	Department loadDepartment(String departmentId);
	/**
	 * 获取组织部门树结构。
	 * @param departmentId
	 * 	组织部门ID。
	 * @return 组织部门树结构。
	 * */
	List<TreeNode> tree(String departmentId);
	/**
	 * 不包含指定部门ID的子部门的组织部门树形结构。
	 * @param departmentId
	 * 	组织部门ID。
	 * @return 组织部门树结构。
	 * */
	List<TreeNode> treeOfNoChilds(String departmentId);
}