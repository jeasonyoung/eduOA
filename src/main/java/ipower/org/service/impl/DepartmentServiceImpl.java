package ipower.org.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.org.dao.IDepartmentDao;
import ipower.org.domain.Department;
import ipower.org.pageModel.DepartmentInfo;
import ipower.org.service.IDepartmentService;
import ipower.pageModel.TreeNode;

/**
 * 组织部门服务实现类。
 * @author 杨勇。
 * @since 2013-12-02。
 * */
public class DepartmentServiceImpl extends DataServiceBaseImpl<Department, DepartmentInfo> implements IDepartmentService {
	private IDepartmentDao departmentDao;
	/**
	 * 设置组织部门数据访问接口。
	 * @param departmentDao
	 * 	组织部门数据访问接口。
	 * */
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	/**
	 * 创建组织部门树结构。
	 * @param departmentId
	 * 	部门ID。
	 * @return 树形结构。
	 * */
	@Override
	public synchronized List<TreeNode> tree(String departmentId) {
		List<TreeNode> results = new ArrayList<>();
		if(departmentId != null && !departmentId.trim().isEmpty()){
			TreeNode node = this.createDepartmentNode(this.departmentDao.load(Department.class, departmentId), null);
			if(node != null) results.add(node);
		}else{
			String hql = "from Department d where d.parent = null order by d.departmentOrder asc";
			List<Department> list = this.departmentDao.find(hql, null, null, null);
			this.createDepartmentNodes(results, list, null);
		}
		return results;
	}
	/**
	 * 不包含指定组织部门ID的子部门的组织部门树形结构。
	 * @param departmentId
	 * 	指定的组织部门ID。
	 * @return 树形结构。
	 * */
	@Override
	public synchronized List<TreeNode> treeOfNoChilds(String departmentId) {
		if(departmentId == null || departmentId.trim().isEmpty()){
			return this.tree(departmentId);
		}
		List<TreeNode> results = new ArrayList<>();
		if(departmentId != null && !departmentId.trim().isEmpty()){
			String hql = "from Department d " +
					"where d.parent = null  " +
					"and (d.departmentId <> :deptId)"+
					"and (d.departmentId not in (select tmp.departmentId from Department tmp where tmp.parent.departmentId = :deptId)) " +
					"order by d.departmentOrder asc";
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("deptId", departmentId);
			List<Department> list = this.departmentDao.find(hql, parameters, null, null);
			this.createDepartmentNodes(results, list, departmentId);
		}
		return results;
	}
	
	private void createDepartmentNodes(List<TreeNode> results, List<Department> list, String ignoreDepartmentId){
		if(results != null && list != null && list.size() > 0){
			if(list != null && list.size() > 0){
				for(int i = 0; i < list.size(); i++){	 
					TreeNode node = this.createDepartmentNode(list.get(i), ignoreDepartmentId);
					if(node != null)results.add(node);
				}
			}
		}
	}
	
	private TreeNode createDepartmentNode(Department department, String ignoreDepartmentId){
		if(department == null) return null;
		
		if(ignoreDepartmentId != null && 
				!ignoreDepartmentId.trim().isEmpty() &&
				department.getDepartmentId().equalsIgnoreCase(ignoreDepartmentId)){
			 return null;
		}
		
		TreeNode node = new TreeNode();
		
		node.setId(department.getDepartmentId());
		node.setText(department.getDepartmentName());
		
		Map<String, Object> attr = new HashMap<>();
		attr.put("order", department.getDepartmentOrder());
		node.setAttributes(attr);
		
		Set<Department> childrens = department.getChildrens();
		if(childrens != null && childrens.size() > 0){
			List<TreeNode> list = new ArrayList<>();
			for (Department dept : childrens) {
				TreeNode n  = this.createDepartmentNode(dept, ignoreDepartmentId);
				if(n != null) list.add(n);
			}
			if(list.size() > 0) node.setChildren(list);
		}
		
		return node;
	}
	/**
	 * 按条件查询数据。
	 * @param info
	 * 	查询条件。
	 * @return 结果数据集合。
	 * */
	@Override
	protected List<Department> find(DepartmentInfo info) {
		String hql = "from Department d where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		hql += " order by ";
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql+=" d."+ info.getSort() +" "+ info.getOrder() + ",";
		}
		hql += " d.departmentOrder asc ";
		return this.departmentDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected DepartmentInfo changeModel(Department data) {
		if(data == null) return null;
		DepartmentInfo info = new DepartmentInfo();
		BeanUtils.copyProperties(data, info);
		Department p = null;
		if((p = data.getParent()) != null) {
			info.setParentDepartmentId(p.getDepartmentId());
			info.setFullDepartmentName(this.createFullDepartmentName(data));
		}
		return info;
	}
	
	private String createFullDepartmentName(Department data){
		if(data != null && data.getParent() != null) {
			Stack<String> stack = new Stack<>();
			stack.add(data.getDepartmentName());
			this.pushStack(stack, data.getParent());
			if(stack.size() > 0){
				return this.popStack(stack);
			}
		}
		return null;
	}
	
	private void pushStack(Stack<String> stack, Department dept){
		if(dept != null){
			stack.push(dept.getDepartmentName());
			this.pushStack(stack, dept.getParent());
		}
	}
	
	private String popStack(Stack<String> stack){
		String result = null, output = null;
		while(stack != null && stack.size() > 0){
			 output = stack.pop();
			 if(output != null && !output.trim().isEmpty()){
				 if(result == null){
					 result = output;
				 }else {
					result +="=>"+ output;
				}
			 }
		}
		return result;
	}

	@Override
	protected Long total(DepartmentInfo info) {
		String hql = "select count(*) from Department d where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.departmentDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(DepartmentInfo info, String hql, Map<String, Object> parameters) {
		if(info.getDepartmentName() != null && !info.getDepartmentName().trim().isEmpty()){
			hql +=" and d.departmentName like :name";
			parameters.put("name", "%"+info.getDepartmentName()+"%");
		}
		return hql;
	}

	@Override
	public DepartmentInfo update(DepartmentInfo info) {
		if(info != null){
			boolean isAdded = false;
			Department data = (info.getDepartmentId() == null || info.getDepartmentId().isEmpty()) ? null : this.departmentDao.load(Department.class,info.getDepartmentId());
			if(isAdded = (data == null)){
				info.setDepartmentId(UUID.randomUUID().toString());
				data = new Department();
			}
			BeanUtils.copyProperties(info, data);
			if(info.getParentDepartmentId() != null && !info.getParentDepartmentId().trim().isEmpty()){
				Department parent = this.departmentDao.load(Department.class, info.getParentDepartmentId());
				if(parent != null){
					data.setParent(parent);
					info.setFullDepartmentName(this.createFullDepartmentName(data));
				}
			}
			
			if(isAdded)this.departmentDao.save(data);
		}
		return info;
	}

	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(String id: ids){
			if(id == null || id.trim().isEmpty()) continue;
			Department data = this.departmentDao.load(Department.class, id);
			if(data != null){
				if(data.getChildrens() != null && data.getChildrens().size() > 0){
					continue;
				}
				this.departmentDao.delete(data);
			}
		}
	}
	
	@Override
	public Department loadDepartment(String departmentId) {
		 if(departmentId == null || departmentId.trim().isEmpty()) return null;
		 return this.departmentDao.load(Department.class, departmentId);
	}
}