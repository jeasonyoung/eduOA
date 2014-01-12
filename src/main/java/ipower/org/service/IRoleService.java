package ipower.org.service;

import java.util.List;

import ipower.org.dao.IRoleDao;
import ipower.org.domain.Role;
import ipower.org.pageModel.RoleInfo;

/**
 * 角色服务接口。
 * @author 杨勇。
 * @since 2013-12-06。
 * */
public interface IRoleService extends IDataServiceBase<RoleInfo> {
	/**
	 * 设置角色数据访问。
	 * @param roleDao
	 * 	角色数据访问。
	 * */
	void setRoleDao(IRoleDao roleDao);
	/**
	 * 加载角色。
	 * @param roleId
	 * 	角色ID。
	 * @return 角色。
	 * */
	Role loadRole(String roleId);
	/**
	 * 加载全部角色集合。
	 * @return 角色集合。
	 * */
	List<Role> allRoles();
}