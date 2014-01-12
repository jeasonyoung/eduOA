package ipower.org.action;

import java.io.IOException;

import ipower.org.pageModel.DepartmentInfo;
import ipower.org.service.IDepartmentService;

/**
 * 组织部门Action。
 * @author 杨勇。
 * @since 2013-12-02。
 * */
public class DepartmentAction extends BaseDataAction<DepartmentInfo>{
	private DepartmentInfo info = new DepartmentInfo();
	
	@Override
	public DepartmentInfo getModel() {
		return this.info;
	}

	@Override
	protected String deletePrimaryString() {
		return this.getModel().getDepartmentId();
	}

	/**
	 * 组织部门树行结构。
	 * @throws IOException 
	 * */
	public void tree() throws IOException{
		if(this.service instanceof IDepartmentService){
			this.writeJson(((IDepartmentService)this.service).tree(this.getModel().getDepartmentId()));
		}
	}
	/**
	 * 不包含指定部门ID的子部门的组织部门树形结构。
	 * @throws IOException
	 * */
	public void treeOfNoChilds() throws IOException{
		if(this.service instanceof IDepartmentService){
			this.writeJson(((IDepartmentService)this.service).treeOfNoChilds(this.getModel().getDepartmentId()));
		}
	}
}