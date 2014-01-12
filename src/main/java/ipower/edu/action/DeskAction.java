package ipower.edu.action;
/**
 * 办公桌面ACTION。
 * @author 杨勇.
 * @since 2011-11-27.
 * */
public class DeskAction extends BaseAction {
	/**
	 * 布局主页面。
	 * */
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}
	/**
	 * 左边菜单。
	 * */
	public String left(){
		return "left";
	}
	/**
	 * 中间工作区域。
	 * */
	public String workspace(){
		return "workspace";
	}
}