package ipower.org.service;

import java.util.List;

import ipower.org.dao.IUserDao;
import ipower.org.domain.IUserBase;
import ipower.org.pageModel.UserInfo;

/**
 * 用户服务接口。
 * @author 杨勇。
 * @since 2013-12-04。
 * */
public interface IUserService extends IDataServiceBase<UserInfo> {
	/**
	 * 设置用户数据操作接口。
	 * @param userDao
	 * 	数据操作对象。
	 * */
	void setUserDao(IUserDao userDao);
	/**
	 * 设置部门服务接口。
	 * @param departmentService
	 * 	部门服务接口。
	 * */
	void setDepartmentService(IDepartmentService departmentService);
	/**
	 * 设置组织部门服务接口。
	 * @param postService
	 * 	组织部门服务接口。
	 * */
	void setPostService(IPostService postService);
	/**
	 * 加载用户集合。
	 * @param username
	 * 	用户姓名。
	 * @return 用户集合。
	 * */
	List<IUserBase> allUsers(String username);
}