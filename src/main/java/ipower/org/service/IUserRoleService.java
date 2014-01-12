package ipower.org.service;

import ipower.org.dao.IRoleDao;
import ipower.org.dao.IUserDao;
import ipower.org.dao.IUserRoleDao;
import ipower.org.pageModel.UserRoleInfo;

/**
 * 用户角色服务接口。
 * @author 杨勇。
 * @since 2013-12-09。
 * */
public interface IUserRoleService extends IDataServiceBase<UserRoleInfo> {
	/**
	 * 设置用户角色数据接口。
	 * @param userRoleDao
	 * 	用户角色数据接口。
	 * */
	void setUserRoleDao(IUserRoleDao userRoleDao);
	/**
	 * 设置用户数据接口。
	 * @param userDao
	 * 	用户数据接口。
	 * */
	void setUserDao(IUserDao userDao);
	/**
	 * 设置角色数据接口。
	 * @param roleDao
	 * 	角色数据接口。
	 * */
	void setRoleDao(IRoleDao roleDao);
	/**
	 * 加载角色ID集合。
	 * @param userId
	 * 	用户ID。
	 * @return 角色ID集合。
	 * */
	String[] loadRoleIds(String userId);
}