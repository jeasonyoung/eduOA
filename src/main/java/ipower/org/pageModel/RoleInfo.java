package ipower.org.pageModel;

import ipower.org.domain.Role;
import ipower.pageModel.IPaging;

/**
 * 角色信息。
 * @author 杨勇。
 * @since 2013-12-06。
 * */
public class RoleInfo extends Role implements IPaging {
	private static final long serialVersionUID = 1L;
	private Integer page, rows;
	private String sort,order;
	/**
	 * 获取排序字段名称。
	 * @return 排序字段名称。
	 * */
	@Override
	public String getOrder() {
		return this.order;
	}
	/**
	 * 设置排序字段名称。
	 * @param order
	 * 	排序字段名称。
	 * */
	@Override
	public void setOrder(String order) {
		this.order = order;
	}
	/**
	 * 获取当前页码。
	 * @return 当前页码。
	 * */
	@Override
	public Integer getPage() {
		return this.page;
	}
	/**
	 * 设置当前页码。
	 * @param page
	 * 	当前页码。
	 * */
	@Override
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * 获取每页数据数。
	 * @return 每页数据数。
	 * */
	@Override
	public Integer getRows() {
		return this.rows;
	}
	/**
	 * 设置每页数据数。
	 * @param rows
	 * 	每页数据数。
	 * */
	@Override
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	/**
	 * 获取排序方式。
	 * @return 排序方式。
	 * */
	@Override
	public String getSort() {
		return this.sort;
	}
	/**
	 * 设置排序方式。
	 * @param sort
	 * 	排序方式。
	 * */
	@Override
	public void setSort(String sort) {
		 this.sort = sort;
	}
}