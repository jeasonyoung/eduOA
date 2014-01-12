package ipower.org.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import ipower.org.dao.IPostDao;
import ipower.org.domain.Post;
import ipower.org.pageModel.PostInfo;
import ipower.org.service.IPostService;

/**
 * 职务岗位服务实现类。
 * @author 杨勇。
 * @since 2013-12-01。
 * */
public class PostServiceImpl extends DataServiceBaseImpl<Post, PostInfo> implements IPostService {
	private IPostDao postDao;
	/**
	 * 设置数据操作接口。
	 * @param postDao
	 * 	数据操作接口。
	 * */
	@Override
	public void setPostDao(IPostDao postDao) {
		this.postDao = postDao;
	}
	/**
	 * 查询数据。
	 * @param info
	 * 	查询条件。
	 * @return 结果数据集。
	 * */
	@Override
	protected List<Post> find(PostInfo info) {
		String hql = "from Post p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		if(info.getSort() != null && !info.getSort().trim().isEmpty()){
			hql+=" order by p."+ info.getSort() +" "+ info.getOrder();
		}
		return this.postDao.find(hql, parameters, info.getPage(), info.getRows());
	}
	/**
	 * 格式转换。
	 * @param data
	 * 	源数据格式。
	 * @return 目标格式。
	 * */
	@Override
	protected PostInfo changeModel(Post data) {
		if(data == null) return null;
		PostInfo info = new PostInfo();
		BeanUtils.copyProperties(data, info);
		return info;
	}
	/**
	 * 统计数据总数。
	 * @param info
	 * 	查询条件。
	 * */
	@Override
	protected Long total(PostInfo info) {
		String hql = "select count(*) from Post p where 1 = 1 ";
		Map<String, Object> parameters = new HashMap<>();
		hql = this.addWhere(info, hql, parameters);
		return this.postDao.count(hql, parameters);
	}
	/**
	 * 生成查询条件。
	 * @param info
	 * 	条件对象。
	 * @param hql
	 * 	HQL
	 * @param parameters
	 * 	查询条件集合。
	 * @return 生成的查询HQL。
	 * */
	@Override
	protected String addWhere(PostInfo info, String hql, Map<String, Object> parameters) {
		if(info.getName() != null && !info.getName().trim().isEmpty()){
			hql +=" and p.name like :name";
			parameters.put("name", "%"+info.getName()+"%");
		}
		return hql;
	}
	/**
	 * 更新职务岗位。
	 * @param info
	 * 	更新数据。
	 * @return 更新后的数据。
	 * */
	@Override
	public PostInfo update(PostInfo info) {
		if(info != null){
			boolean isAdded = false;
			Post data = (info.getId() == null || info.getId().isEmpty()) ? null : this.postDao.load(Post.class,info.getId());
			if(isAdded = (data == null)){
				info.setId(UUID.randomUUID().toString());
				data = new Post();
			}
			BeanUtils.copyProperties(info, data);
			if(isAdded)this.postDao.save(data);
		}
		return info;
	}
	/**
	 * 删除主键下的数据。
	 * @param ids
	 * 	主键集合。
	 * */
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0) return;
		for(String id: ids){
			if(id == null || id.trim().isEmpty()) continue;
			Post data = this.postDao.load(Post.class, id);
			if(data != null) this.postDao.delete(data);
		}
	}
	
	@Override
	public Post loadPost(String postId) {
		if(postId == null || postId.trim().isEmpty()) return null;
		return this.postDao.load(Post.class, postId);
	}
	
	@Override
	public List<Post> allPosts() {
		return this.postDao.find("from Post p order by p.level", null, null, null);
	}
}