package ipower.org.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.org.dao.IRoleDao;
import ipower.org.domain.Role;
import ipower.org.pageModel.RoleInfo;
import ipower.org.service.IRoleService;

/**
 * 角色服务实现。
 * @author 杨勇。
 * @since 2013-12-06。
 * */
public class RoleServiceImpl extends DataServiceBaseImpl<Role,RoleInfo> implements IRoleService {
	private IRoleDao roleDao;
	/**
	 * 设置角色数据访问。
	 * @param roleDao
	 * 	角色数据访问。
	 * */
	@Override
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}
	/**
	 * 加载角色。
	 * @param roleId
	 * 	角色ID。
	 * @return 角色。
	 * */
	@Override
	public Role loadRole(String roleId) {
		if(roleId == null || roleId.trim().isEmpty()) return null;
		return this.roleDao.load(Role.class, roleId);
	}
	/**
	 * 加载全部角色集合。
	 * @return 角色集合。
	 * */
	@Override
	public List<Role> allRoles() {
		return this.roleDao.find("from Role r order by r.name", null, null, null);
	}
	/**
	 * 查询数据。
	 * @param info
	 * 	查询条件。
	 * @return 结果数据集。
	 * */
	@Override
	protected List<Role> find(RoleInfo info) {
		String hql = "from Role r where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql+=" order by r."+ info.getSort() +" "+ info.getOrder();
		}
		return this.roleDao.find(hql, parameters, info.getPage(), info.getRows());
	}
	/**
	 * 格式转换。
	 * @param data
	 * 	源数据格式。
	 * @return 目标格式。
	 * */
	@Override
	protected RoleInfo changeModel(Role data) { 
		if(data == null) return null;
		RoleInfo info = new RoleInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}
	/**
	 * 统计数据总数。
	 * @param info
	 * 	查询条件。
	 * */
	@Override
	protected Long total(RoleInfo info) {
		String hql = "select count(*) from Role r where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.roleDao.count(hql, parameters);
	}
	/**
	 * 生成查询条件。
	 * @param info
	 * 	条件对象。
	 * @param hql
	 * 	HQL
	 * @param parameters
	 * 	查询条件集合。
	 * @return 生成的查询HQL。
	 * */
	@Override
	protected String addWhere(RoleInfo info, String hql, Map<String, Object> parameters) {
		if(info.getName() != null && !info.getName().trim().isEmpty()){
			hql +=" and r.name like :name";
			parameters.put("name", "%"+info.getName()+"%");
		}
		return hql;
	}
	/**
	 * 更新角色。
	 * @param info
	 * 	更新数据。
	 * @return 更新后的数据。
	 * */
	@Override
	public RoleInfo update(RoleInfo info) {
		if(info != null){
			boolean isAdded = false;
			Role data = (info.getId() == null || info.getId().isEmpty()) ? null : this.roleDao.load(Role.class,info.getId());
			if(isAdded = (data == null)){
				info.setId(UUID.randomUUID().toString());
				data = new Role();
			}
			BeanUtils.copyProperties(info, data);
			if(isAdded)this.roleDao.save(data);
		}
		return info;
	}
	/**
	 * 删除主键下的数据。
	 * @param ids
	 * 	主键集合。
	 * */
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(String id: ids){
			if(id == null || id.trim().isEmpty()) continue;
			Role data = this.roleDao.load(Role.class, id);
			if(data != null) this.roleDao.delete(data);
		}
	}
}