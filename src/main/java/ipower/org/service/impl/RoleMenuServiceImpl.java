package ipower.org.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ipower.edu.service.IMenuService;
import ipower.org.dao.IRoleDao;
import ipower.org.dao.IRoleMenuDao;
import ipower.org.domain.Role;
import ipower.org.domain.RoleMenu;
import ipower.org.pageModel.RoleMenuInfo;
import ipower.org.service.IRoleMenuService;
import ipower.pageModel.DataGrid;

/**
 * 角色菜单服务实现类。
 * @author 杨勇。
 * @since 2013-12-07。
 * */
public class RoleMenuServiceImpl implements IRoleMenuService {
	private IRoleDao roleDao;
	private IRoleMenuDao roleMenuDao;
	private IMenuService menuService;
	
	@Override
	public void setRoleMenuDao(IRoleMenuDao roleMenuDao) {
		this.roleMenuDao = roleMenuDao;
	}
	
	@Override
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	
	/**
	 * 获取列表数据。
	 * @param info
	 * 	查询条件。
	 * */
	@Override
	public DataGrid<RoleMenuInfo> datagrid(RoleMenuInfo info) {
		DataGrid<RoleMenuInfo> grid = new DataGrid<>();
		grid.setRows(this.changeModels(this.findRoles(info)));
		grid.setTotal(this.total(info));
		return grid;
	}
	
	private List<Role> findRoles(RoleMenuInfo info){
		String hql = "from Role r where (r.id in (select rm.role.id from RoleMenu rm)) ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql+=" order by r."+ info.getSort() +" "+ info.getOrder();
		}
		return this.roleDao.find(hql, parameters, info.getPage(), info.getRows());
	}
	
	private List<RoleMenuInfo> changeModels(List<Role> list){
		List<RoleMenuInfo> results = new ArrayList<>();
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				RoleMenuInfo info = this.changeModel(list.get(i));
				if(info != null)results.add(info);
			}
		}
		return results;
	}
	
	private List<RoleMenu> loadRoleMenus(String roleId){
		final String hql = "from RoleMenu rm where rm.role.id = :roleId";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("roleId", roleId);
		return this.roleMenuDao.find(hql, parameters, null, null);
	}
	
	private RoleMenuInfo changeModel(Role role){
		if(role == null) return null;
		RoleMenuInfo info = new RoleMenuInfo();
		info.setId(role.getId());
		info.setName(role.getName());
		List<RoleMenu> list = this.loadRoleMenus(role.getId());
		if(list != null && list.size() > 0){
			StringBuilder sb = new StringBuilder();
			String[] menus = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				if(list.get(i) == null) continue;
				menus[i] = list.get(i).getMenuId();
				if(sb.length() > 0)sb.append(",");
				String name = this.loadMenuName(menus[i]);
				if(name != null && !name.trim().isEmpty()) sb.append(name);
			}
			info.setMenuIds(menus);
			info.setMenuNames(sb.toString());
		}
		return info;
	}
	private String loadMenuName(String menuId){
		if(menuId == null || menuId.trim().isEmpty() || this.menuService == null) return null;
		return this.menuService.loadMenuName(menuId);
	}
	
	private Long total(RoleMenuInfo info) {
		String hql = "select count(*) from Role r where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.roleDao.count(hql, parameters);
	}

	private String addWhere(RoleMenuInfo info, String hql, Map<String, Object> parameters) {
		if(info.getId() != null && !info.getId().trim().isEmpty()){
			hql += " and r.id = :id ";
			parameters.put("id", info.getId());
		}
		if(info.getName() != null && !info.getName().trim().isEmpty()){
			hql +=" and r.name like :name";
			parameters.put("name", "%"+info.getName()+"%");
		}
		return hql;
	}

	@Override
	public RoleMenuInfo update(RoleMenuInfo info) {
		if(info == null || info.getId() == null || info.getId().trim().isEmpty()) return null;
		this.delete(new String[]{info.getId()});
		Role role = this.roleDao.load(Role.class, info.getId());
		if(role == null) return null;
		if(info.getMenuIds() != null && info.getMenuIds().length > 0){
			String[] menus = this.splitMenuIds(info.getMenuIds());
			for(int i = 0; i < menus.length;i++){
				RoleMenu rm = new RoleMenu();
				rm.setRole(role);
				rm.setMenuId(menus[i]);
				this.roleMenuDao.save(rm);
			}
		}
		return this.changeModel(role);
	}
	
	private String[] splitMenuIds(String[] input){
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
		this.roleMenuDao.delete(ids);
	}

	@Override
	public String[] loadMenus(String roleId) {
		if(roleId != null && !roleId.trim().isEmpty()){
			Role role = this.roleDao.load(Role.class, roleId);
			if(role != null){
				RoleMenuInfo info = this.changeModel(role);
				if(info != null)return info.getMenuIds();
			}
		}
		return null;
	}
}