package ipower.org.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ipower.org.dao.IRoleDao;
import ipower.org.dao.IUserDao;
import ipower.org.dao.IUserRoleDao;
import ipower.org.domain.Role;
import ipower.org.domain.User;
import ipower.org.domain.UserRole;
import ipower.org.pageModel.UserRoleInfo;
import ipower.org.service.IUserRoleService;
import ipower.pageModel.DataGrid;

/**
 * 用户角色服务实现类。
 * @author 杨勇。
 * @since 2013-12-09。
 * */
public class UserRoleServiceImpl implements IUserRoleService {
	private IUserRoleDao userRoleDao;
	private IUserDao userDao;
	private IRoleDao roleDao;
	private static Map<String, Role> mapRoleCache = Collections.synchronizedMap(new HashMap<String,Role>());
	/**
	 * 设置用户角色数据接口。
	 * @param userRoleDao
	 * 	用户角色数据接口。
	 * */
	@Override
	public void setUserRoleDao(IUserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	/**
	 * 设置用户数据接口。
	 * @param userDao
	 * 	用户数据接口。
	 * */
	@Override
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * 设置角色数据接口。
	 * @param roleDao
	 * 	角色数据接口。
	 * */
	@Override
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}
	/**
	 * 获取列表数据。
	 * @param info
	 * 	查询条件。
	 * @return 列表数据。
	 * */
	@Override
	public DataGrid<UserRoleInfo> datagrid(UserRoleInfo info) {
		DataGrid<UserRoleInfo> grid = new DataGrid<UserRoleInfo>();
		grid.setRows(this.changeModel(this.findUsers(info)));
		grid.setTotal(this.total(info));
		return grid;
	}
	
	private Long total(UserRoleInfo info) {
		String hql = "select count(*) from User u where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.userDao.count(hql, parameters);
	}
	
	private List<UserRoleInfo> changeModel(List<User> users){
		List<UserRoleInfo> list = new ArrayList<>();
		if(users != null && users.size() > 0){
			for(int i = 0; i < users.size(); i++){
				UserRoleInfo info = this.changeModel(users.get(i));
				if(info != null) list.add(info);
			}
		}
		return list;
	}
	
	private UserRoleInfo changeModel(User user){
		if(user == null) return null;
		UserRoleInfo info = new UserRoleInfo();
		info.setId(user.getId());
		info.setName(user.getName());
		List<UserRole> list = this.loadRoles(user.getId());
		if(list!= null && list.size() > 0){
			StringBuilder sb = new StringBuilder();
			String[] roles = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				if(list.get(i) == null || list.get(i).getRole() == null) continue;
				roles[i] = list.get(i).getRole().getId();
				if(sb.length() > 0)sb.append(",");
				sb.append(list.get(i).getRole().getName());
			}
			info.setRoleIds(roles);
			info.setRoleNames(sb.toString());
		}
		return info;
	}
	
	private List<UserRole> loadRoles(String userId){
		final String hql = "from UserRole ur where ur.user.id = :userId";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userId", userId);
		return this.userRoleDao.find(hql, parameters, null, null);
	}
	
	private List<User> findUsers(UserRoleInfo info){
		String hql = "from User u where (u.id in (select ur.user.id from UserRole ur)) ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql+=" order by u."+ info.getSort() +" "+ info.getOrder();
		}
		return this.userDao.find(hql, parameters, info.getPage(), info.getRows());
	}
	
	private String addWhere(UserRoleInfo info, String hql, Map<String, Object> parameters) {
		if(info.getId() != null && !info.getId().trim().isEmpty()){
			hql += " and u.id = :id ";
			parameters.put("id", info.getId());
		}
		if(info.getName() != null && !info.getName().trim().isEmpty()){
			hql +=" and (u.name like :name or u.account like :name)";
			parameters.put("name", "%"+info.getName()+"%");
		}
		return hql;
	}
	
	private synchronized Role loadRole(String roleId){
		if(roleId == null || roleId.trim().isEmpty()) return null;
		Role role = mapRoleCache.get(roleId);
		if(role == null){
		  role = this.roleDao.load(Role.class, roleId);
		  if(role != null) mapRoleCache.put(roleId, role);
		}
		return role;
	}

	@Override
	public UserRoleInfo update(UserRoleInfo info) {
		if(info == null || info.getId() == null || info.getId().trim().isEmpty()) return null;
		this.delete(new String[]{info.getId()});
		User user = this.userDao.load(User.class, info.getId());
		if(user == null) return null;
		if(info.getRoleIds() != null && info.getRoleIds().length > 0){
			String[] roles = this.splitRoleIds(info.getRoleIds());
			for(int i = 0; i < roles.length; i++){
				UserRole userRole = new UserRole();
				userRole.setUser(user);
				Role role = this.loadRole(roles[i]);
				if(role == null) continue;
				userRole.setRole(role);
				this.userRoleDao.save(userRole);
			}
		}
		return this.changeModel(user);
	}

	private String[] splitRoleIds(String[] input){
		List<String> list = new ArrayList<>();
		if(input != null && input.length > 0){
			for(int i = 0; i < input.length; i++){
				String[] data = input[i].split(",");
				if(data != null && data.length > 0){
					for(int j = 0; j < data.length; j++){
						if(data[j] != null && !data[j].trim().isEmpty()){
							list.add(data[j].trim());
						}
					}
				}
			}
		}
		return list.toArray(new String[0]);
	}
	
	@Override
	public void delete(String[] ids) {
		this.userRoleDao.delete(ids);
	}
	
	@Override
	public String[] loadRoleIds(String userId){
		if(userId == null || userId.trim().isEmpty()) return null;
		UserRoleInfo info = this.changeModel(this.userDao.load(User.class, userId));
		if(info != null) return info.getRoleIds();
		return null;
	}
}