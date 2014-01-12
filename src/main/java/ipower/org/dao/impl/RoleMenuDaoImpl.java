package ipower.org.dao.impl;

import org.hibernate.Query;

import ipower.org.dao.IRoleMenuDao;
import ipower.org.domain.RoleMenu;

/**
 * 角色菜单数据访问实现类。
 * @author 杨勇。
 * @since 2013-12-07。
 * */
public class RoleMenuDaoImpl extends DaoBaseImpl<RoleMenu> implements IRoleMenuDao {

	@Override
	public boolean delete(String[] roleIds) {
		if(roleIds == null || roleIds.length == 0) return false;
		StringBuilder sb = new StringBuilder();
		sb.append("'");
		for(int i = 0; i < roleIds.length; i++){
			if(i > 0) sb.append("','");
			sb.append(roleIds[i]);
		}
		sb.append("'");
		Query query = this.getCurrentSession().createQuery("delete RoleMenu r where r.role.id in ("+ sb.toString() +")");
	    return	query.executeUpdate() > 0;
	}

}