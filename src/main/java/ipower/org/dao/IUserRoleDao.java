package ipower.org.dao;

import ipower.org.domain.UserRole;

/**
 * 用户角色数据访问接口。
 * @author 杨勇。
 * @since 2013-12-09。
 * */
public interface IUserRoleDao extends IDaoBase<UserRole> {
	/**
	 * 删除用户的角色。
	 * @param roleIds
	 * 	用户ID集合。
	 * */
	boolean delete(String[] userIds);
}