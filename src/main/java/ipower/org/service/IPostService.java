package ipower.org.service;

import java.util.List;

import ipower.org.dao.IPostDao;
import ipower.org.domain.Post;
import ipower.org.pageModel.PostInfo;

/**
 * 职务岗位服务接口。
 * @author 杨勇。
 * @since 2013-12-01。
 * */
public interface IPostService extends IDataServiceBase<PostInfo> {
	/**
	 * 设置职务岗位数据操作接口。
	 * @param postDao
	 * 	数据操作接口。
	 * */
	void setPostDao(IPostDao postDao);
	/**
	 * 加载职务岗位数据。
	 * @param postId
	 * 	职务岗位ID。
	 * @return 职务岗位数据。
	 * */
	Post loadPost(String postId);
	/**
	 * 加载全部的职务岗位数据集合。
	 * @return 职务岗位数据集合。
	 * */
	List<Post> allPosts();
}