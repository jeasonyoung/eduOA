package ipower.org.domain;

import java.io.Serializable;

/**
 * 用户角色(一个用户可有多个角色)。
 * @author 杨勇。
 * @since 2013-12-09。
 * */
public class UserRole implements Serializable{
	private static final long serialVersionUID = 1L;
	private User user;
	private Role role;
	/**
	 * 获取用户。
	 * @return 用户。
	 * */
	public User getUser() {
		return user;
	}
	/**
	 * 设置用户。
	 * @param user
	 * 	用户。
	 * */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * 获取角色。
	 * @return 角色。
	 * */
	public Role getRole() {
		return role;
	}
	/**
	 * 设置角色。
	 * @param role
	 * 	角色。
	 * */
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public int hashCode(){
		int value = super.hashCode() + 32;
		if(this.user != null && this.user.getId() != null){
			value += this.user.hashCode() * this.user.getId().hashCode();
		}
		if(this.role != null && this.role.getId() != null){
			value += this.role.hashCode() * this.role.getId().hashCode();
		}
		return value;
	}
	
	@Override
	public boolean equals(Object obj){
		if(this == obj) return true;
		if(obj == null) return false;
		if(this.getClass() != obj.getClass()) return false;
		UserRole userRole = (UserRole)obj;
		if(this.getRole() == null && userRole.getRole() != null)
			return false;
		if(!this.getRole().getId().equalsIgnoreCase(userRole.getRole().getId()))
			return false;
		if(this.getUser() == null && userRole.getUser() != null)
			return false;
		return this.getUser().getId().equalsIgnoreCase(userRole.getUser().getId());
	}
}