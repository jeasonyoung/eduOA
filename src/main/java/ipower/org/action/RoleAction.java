package ipower.org.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ipower.org.domain.Role;
import ipower.org.pageModel.RoleInfo;
import ipower.org.service.IRoleService;
import ipower.pageModel.TreeNode;

/**
 * 角色Action。
 * @author 杨勇。
 * @since 2013-12-06。
 * */
public class RoleAction extends BaseDataAction<RoleInfo> {
	private RoleInfo info = new RoleInfo();
	
	@Override
	public RoleInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getId();
	}

	public void all() throws IOException{
		if(this.service instanceof IRoleService){
			this.writeJson(((IRoleService)this.service).allRoles());
		}
	}
	
	public void tree() throws IOException{
		if(this.service instanceof IRoleService){
			List<TreeNode> nodes = new ArrayList<>();
			List<Role> list = ((IRoleService)this.service).allRoles();
			if(list != null && list.size() > 0){
				for(int i = 0; i < list.size(); i++){
					Role role = list.get(i);
					if(role != null){
						TreeNode node = new TreeNode();
						node.setId(role.getId());
						node.setText(role.getName());
						nodes.add(node);
					}
				}
			}
			this.writeJson(nodes);
		}
	}
}