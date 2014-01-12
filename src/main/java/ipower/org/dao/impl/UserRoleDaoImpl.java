package ipower.org.dao.impl;

import org.hibernate.Query;

import ipower.org.dao.IUserRoleDao;
import ipower.org.domain.UserRole;

/**
 * 用户角色数据访问对象。
 * @author 杨勇。
 * @since 2013-12-09。
 * */
public class UserRoleDaoImpl extends DaoBaseImpl<UserRole> implements IUserRoleDao{

	@Override
	public boolean delete(String[] userIds) {
		if(userIds == null || userIds.length == 0) return false;
		StringBuilder sb = new StringBuilder();
		sb.append("'");
		for(int i = 0; i < userIds.length; i++){
			if(i > 0) sb.append("','");
			sb.append(userIds[i]);
		}
		sb.append("'");
		Query query = this.getCurrentSession().createQuery("delete UserRole u where u.user.id in ("+ sb.toString() +")");
	    return	query.executeUpdate() > 0;
	}

}