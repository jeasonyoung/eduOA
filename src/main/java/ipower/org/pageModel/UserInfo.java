package ipower.org.pageModel;

import java.util.Date;
import ipower.pageModel.Paging;

/**
 * 用户信息。
 * @author 杨勇。
 * @since 2013-12-04。
 * */
public class UserInfo extends Paging{
	private static final long serialVersionUID = 1L;
	private String id,name,account,password,idCard,email,mobile,departmentId,departmentName,postId,postName;
	private Integer gender,status;
	private Date birthday;
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
	 * 	用户ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取用户名称。
	 * @return 用户名称。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置用户名称。
	 * @param name
	 *  用户名称。
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
	 *  用户账号。
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
	 * 	用户密码。
	 * */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取用户身份证号码。
	 * @return 用户身份证号码。
	 * */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * 设置用户身份证号码。
	 * */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * 获取用户电子信箱。
	 * @return 用户电子信箱。
	 * */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置用户电子信箱。
	 * @param email
	 * 	用户电子信箱。
	 * */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取用户手机号码。
	 * @return 用户手机号码。
	 * */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置用户手机号码。
	 * @param mobile
	 * 用户手机号码。
	 * */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取所属组织部门ID。
	 * @return 所属组织部门ID。
	 * */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * 设置所属组织部门ID。
	 * @param departmentId
	 * 组织部门ID。
	 * */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * 获取所属组织部门名称。
	 * @return 组织部门名称。
	 * */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * 设置所属组织部门名称。
	 * @param departmentName
	 * 组织部门名称。
	 * */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * 获取职务岗位ID。
	 * @return 职务岗位ID。
	 * */
	public String getPostId() {
		return postId;
	}
	/**
	 * 设置职务岗位ID。
	 * @param postId
	 * 	职务岗位ID。
	 * */
	public void setPostId(String postId) {
		this.postId = postId;
	}
	/**
	 * 获取职务岗位名称。
	 * @return 职务岗位名称。
	 * */
	public String getPostName() {
		return postName;
	}
	/**
	 * 设置职务岗位名称
	 * @param postName
	 * 	职务岗位名称
	 * */
	public void setPostName(String postName) {
		this.postName = postName;
	}
	/**
	 * 获取用户性别。
	 * @return 用户性别(1-男,2-女)。
	 * */
	public Integer getGender() {
		return gender;
	}
	/**
	 * 设置用户性别。
	 * @param gender 
	 * 用户性别(1-男,2-女)。
	 * */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 获取用户状态。
	 * @return 用户状态(0-停用，1-启用)
	 * */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置用户状态。
	 * @param status 
	 * 用户状态(0-停用，1-启用)
	 * */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取用户出生日期。
	 * @return 用户出生日期。
	 * */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 设置用户出生日期。
	 * @param birthday 
	 * 	用户出生日期。
	 * */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}