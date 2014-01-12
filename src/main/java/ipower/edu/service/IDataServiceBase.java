package ipower.edu.service;

import ipower.pageModel.DataGrid;

/**
 * 数据服务接口.
 * @author 杨勇.
 * @since 2013-11-27.
 * */
public interface IDataServiceBase<T> {
	/**
	 * 加载列表数据。
	 * @param info
	 * 	查询条件
	 * @return 列表数据。
	 * */
	DataGrid<T> datagrid(T info);
	/**
	 * 更新数据。
	 * @param info
	 * 	源数据。
	 * */
	T update(T info);
	/**
	 * 删除数据。
	 * @param ids
	 * 	需删除的主键数组。
	 * */
	void delete(String[] ids);
}