package ipower.test;

import ipower.org.pageModel.PostInfo;
import ipower.org.service.IPostService;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TestDao {
	private Logger logger = Logger.getLogger(TestDao.class);
	private IPostService postService;
	
	@Autowired
	public void setPostService(IPostService postService) {
		this.postService = postService;
	}

	@Test
	public void testPostDao(){
		PostInfo info = new PostInfo();
		info.setName("校长");
		info.setLevel(1);
		
		logger.info("插入前："+JSON.toJSONString(info));
		int v = 0;
		if(v == 2){
			info  = this.postService.update(info);
		}
		logger.info("插入后："+JSON.toJSONString(info));
	}
}