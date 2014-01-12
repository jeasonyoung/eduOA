package ipower.org.domain;

import java.io.Serializable;

/**
 * 职务岗位。
 * @author 杨勇。
 * @since 2013-12-01。
 * */
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,name;
	private int level;
	/**
	 * 获取职务岗位ID。
	 * @return 职务岗位ID。
	 * */
	public String getId() {
		return id;
	}
	/**
	 * 设置职务岗位ID。
	 * @param id
	 * 	职务岗位ID。
	 * */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取职务岗位名称。
	 * @return 职务岗位名称。
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 设置职务岗位名称。
	 * @param name
	 * 	职务岗位名称。
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取职级。
	 * @return 职级。
	 * */
	public int getLevel() {
		return level;
	}
	/**
	 * 设置职级。
	 * @param 职级。
	 * */
	public void setLevel(int level) {
		this.level = level;
	}
}