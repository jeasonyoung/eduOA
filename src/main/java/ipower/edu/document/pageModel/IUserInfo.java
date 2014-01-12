package ipower.edu.document.pageModel;

import java.io.Serializable;
/**
 * 用户信息接口。
 * @author 杨勇。
 * @since 2013-12-23。
 * */
public interface IUserInfo extends Serializable {
	/**
	 * 获取用户ID。
	 * @return 用户ID。
	 * */
	String getUserId();
	/**
	 * 获取用户名称。
	 * @return 用户名称。
	 * */
	String getUserName();
}