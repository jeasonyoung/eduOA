package ipower.org.domain;
/**
 * 用户基础接口。
 * @author 杨勇。
 * @since 2013-12-09。 
 * */
public interface IUserBase {
	/**
	 * 获取用户ID。
	 * @return 用户ID。
	 * */
	String getId();
	/**
	 * 获取用户名。
	 * @return 用户名。
	 * */
	String getName();
	/**
	 * 获取用户账号。
	 * @return 用户账号。
	 * */
	String getAccount();
}