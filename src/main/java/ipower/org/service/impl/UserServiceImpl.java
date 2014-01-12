package ipower.org.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.org.dao.IUserDao;
import ipower.org.domain.Department;
import ipower.org.domain.IUserBase;
import ipower.org.domain.Post;
import ipower.org.domain.User;
import ipower.org.pageModel.UserInfo;
import ipower.org.service.IDepartmentService;
import ipower.org.service.IPostService;
import ipower.org.service.IUserService;

/**
 * 用户服务实现类。
 * @author 杨勇。
 * @since 2013-12-04。
 * */
public class UserServiceImpl extends DataServiceBaseImpl<User,UserInfo> implements IUserService {
	private IUserDao userDao;
	private IDepartmentService departmentService;
	private IPostService postService;
	/**
	 * 设置数据操作接口。
	 * @param userDao
	 * 	数据操作接口。
	 * */
	@Override
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * 设置部门服务接口。
	 * @param departmentService
	 * 	部门服务接口。
	 * */
	@Override
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	/**
	 * 设置组织部门服务接口。
	 * @param postService
	 * 	组织部门服务接口。
	 * */
	@Override
	public void setPostService(IPostService postService) {
		this.postService = postService;
	}
	/**
	 * 查询数据。
	 * @param info
	 * 	查询条件。
	 * @return 结果数据集。
	 * */
	@Override
	protected List<User> find(UserInfo info) {
		String hql = "from User u where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		hql += " order by";
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql+="  u."+ info.getSort() +" "+ info.getOrder() + ",";
		}
		hql +=" u.department.departmentName, u.post.name";
		return this.userDao.find(hql, parameters, info.getPage(), info.getRows());
	}

	@Override
	protected UserInfo changeModel(User data) {
		UserInfo info = new UserInfo();
		BeanUtils.copyProperties(data, info); 
		if(data.getDepartment() != null){
			info.setDepartmentId(data.getDepartment().getDepartmentId());
			info.setDepartmentName(data.getDepartment().getDepartmentName());
		}
		if(data.getPost() != null){
			info.setPostId(data.getPost().getId());
			info.setPostName(data.getPost().getName());
		}
		return info;
	}

	@Override
	protected Long total(UserInfo info) {
		String hql = "select count(*) from User u where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.userDao.count(hql, parameters);
	}

	@Override
	protected String addWhere(UserInfo info, String hql, Map<String, Object> parameters) {
		if(info.getDepartmentId() != null && !info.getDepartmentId().trim().isEmpty()){
			hql += " and u.department.departmentId = :departmentId";
			parameters.put("departmentId", info.getDepartmentId());
		}
		if(info.getPostName() != null && !info.getPostName().trim().isEmpty()){
			hql +=" and u.post.name like :postName ";
			parameters.put("postName", "%"+info.getPostName()+"%");
		}
		if(info.getName() != null && !info.getName().trim().isEmpty()){
			hql += " and (u.name like :name or u.account like :name)";
			parameters.put("name", info.getName());
		}
		return hql;
	}

	@Override
	public UserInfo update(UserInfo info) {
		if(info != null){
			boolean isAdded = false;
			User data = (info.getId() == null || info.getId().trim().isEmpty()) ? null : this.userDao.load(User.class,info.getId());
			if(isAdded = (data == null)){
				info.setId(UUID.randomUUID().toString());
				data = new User();
			}
			if(isAdded)
				BeanUtils.copyProperties(info, data);
			else{
				BeanUtils.copyProperties(info, data, new String[]{"password"});
				if(info.getPassword() != null && !info.getPassword().trim().isEmpty()){
					data.setPassword(info.getPassword());
				}
			}
			if(info.getDepartmentId() != null && !info.getDepartmentId().trim().isEmpty()){
				Department dept = this.departmentService.loadDepartment(info.getDepartmentId());
				if(dept != null){
					info.setDepartmentName(dept.getDepartmentName());
					data.setDepartment(dept);
				}
			}
			if(info.getPostId() != null && !info.getPostId().trim().isEmpty()){
				Post post = this.postService.loadPost(info.getPostId());
				if(post != null){
					info.setPostName(post.getName());
					data.setPost(post);
				}
			}
			if(isAdded)this.userDao.save(data);
		}
		return info;
	}
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(String id: ids){
			if(id == null || id.trim().isEmpty()) continue;
			User data = this.userDao.load(User.class, id);
			if(data != null) this.userDao.delete(data);
		}
	}
	@Override
	public List<IUserBase> allUsers(String username) {
		String hql = "from User u where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		if(username != null && !username.trim().isEmpty()){
			hql += " and (u.name like :name or u.account like :name) ";
			parameters.put("name", "%"+ username +"%");
		}
		hql += " order by u.name,u.account ";
		
		List<IUserBase> results = new ArrayList<>();
		List<User> list = this.userDao.find(hql, parameters, null, null);
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				if(list.get(i) != null) results.add(new InnerUser(list.get(i)));
			}
		}
		return results;
	}
	
	class InnerUser implements IUserBase{
		private String id,name,account;
		
		public InnerUser(User user){
			this.id = user.getId();
			this.name = user.getName();
			this.account = user.getAccount();
		}
		
		@Override
		public String getId() {
			return this.id;
		}

		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public String getAccount() {
			return this.account;
		}
		
	}
}