package ipower.edu.action;

import java.io.IOException;

import ipower.edu.service.IDataServiceBase;
import ipower.pageModel.Json;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 数据操作Action抽象类.
 * @author 杨勇.
 * @since 2013-11-27.
 * */
public abstract class BaseDataAction<T> extends BaseAction implements ModelDriven<T> {
	/**
	 * 数据服务对象。
	 * */
	protected IDataServiceBase<T> service;
	/**
	 * 设置服务对象。
	 * @param service
	 * 	服务对象。
	 * */
	public void setService(IDataServiceBase<T> service){
		this.service = service;
	}
	/**
	 * 列表数据。 
	 * @throws IOException 
	 * */
	public void datagrid() throws IOException{
		this.writeJson(this.service.datagrid(this.getModel()));
	}
	/**
	 * 更新数据。
	 * @throws IOException 
	 * */
	public void update() throws IOException{
		Json result = new Json();
		try {
			result.setData(this.service.update(this.getModel()));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}finally{
			this.writeJson(result);
		}
	}
	/**
	 * 获取删除主键字符串。
	 * */
	protected abstract String deletePrimaryString();
	/**
	 * 删除数据。
	 * @throws IOException 
	 * */
	public void delete() throws IOException{
		Json result = new Json();
		try {
			String s = this.deletePrimaryString();
			if(s != null && !s.isEmpty()){
				this.service.delete(s.split("\\|"));
				result.setSuccess(true);
				result.setMsg("删除成功！");
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}finally{
			this.writeJson(result);
		}
	}
}