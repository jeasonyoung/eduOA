package ipower.org.domain;

import java.io.Serializable;

/**
 * 角色菜单。
 * @author 杨勇。
 * @since 2013-12-07。
 * */
public class RoleMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	private String menuId;
	private Role role;
	/**
	 * 获取菜单ID。
	 * @return 菜单ID。
	 * */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * 设置菜单ID。
	 * @param menId
	 * 	菜单ID。
	 * */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
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
		return (this.menuId == null ? 0 : this.menuId.hashCode()) * 
				(this.role == null ? 0 : this.role.hashCode() * ((this.role.getId() == null || this.role.getId().isEmpty()) ? 0 : this.role.getId().hashCode()));
	}
	
	@Override
	public boolean equals(Object obj){
		if(this == obj) return true;
		if(obj == null) return false;
		if(this.getClass() != obj.getClass()) return false;
		RoleMenu roleMenu = (RoleMenu)obj;
		if(this.getMenuId() == null && roleMenu.getMenuId() != null)
			return false;
		if(!this.getMenuId().equalsIgnoreCase(roleMenu.getMenuId()))
			return false;
		if(this.getRole() == null && roleMenu.getRole() != null)
			return false;
		return this.getRole().getId().equalsIgnoreCase(roleMenu.getRole().getId());
	}
}