package ipower.org.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户。
 * @author 杨勇。
 * @since 2013-12-03。
 * */
public class User implements IUserBase,Serializable{
	private static final long serialVersionUID = 1L;
	private String id,name,account,password,idCard,email,mobile;
	private Integer gender,status;
	private Date birthday;
	private Department department;
	private Post post;
	/**
	 * 获取用户ID。
	 * @return 用户ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置用户ID。
	 * @param id
	 * 用户ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取用户名。
	 * @return 用户名。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置用户名。
	 * @param name
	 * 用户名。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取用户账号。
	 * @return 用户账号。
	 * */
	public String getAccount() {
		return account;
	}
	/**
	 * 设置用户账号。
	 * @param account
	 * 	用户账号。
	 * */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取用户密码。
	 * @return 用户密码。
	 * */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置用户密码。
	 * @param password
	 *  用户密码。
	 * */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取身份证号码。
	 * @return 身份证号码。
	 * */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * 设置身份证号码。
	 * @param idCard
	 * 	身份证号码。
	 * */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * 获取电子信箱。
	 * @return 电子信箱。
	 * */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置电子信箱。
	 * @param email
	 * 	电子信箱。
	 * */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取手机号码。
	 * @return 手机号码。
	 * */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置手机号码。
	 * @param mobile
	 * 	手机号码。
	 * */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取用户性别。
	 * @return 用户性别。
	 * */
	public Integer getGender() {
		return gender;
	}
	/**
	 * 设置用户性别。
	 * @param gender
	 *  用户性别。
	 * */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 获取用户状态。
	 * @return 用户状态。
	 * */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置用户状态。
	 * @param status
	 * 	用户状态。
	 * */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取用户生日。
	 * @return 用户生日。
	 * */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 设置用户生日。
	 * @param birthday
	 * 用户生日。
	 * */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取用户所属组织部门。
	 * @return 组织部门。
	 * */
	public Department getDepartment() {
		return department;
	}
	/**
	 * 设置用户所属组织部门。
	 * @param department
	 * 	组织部门。
	 * */
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * 获取用户所属职务岗位。
	 * @return 职务岗位。
	 * */
	public Post getPost() {
		return post;
	}
	/**
	 * 设置用户所属职务岗位。
	 * @param post
	 *  职务岗位。
	 * */
	public void setPost(Post post) {
		this.post = post;
	}
}