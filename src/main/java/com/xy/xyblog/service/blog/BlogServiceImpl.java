package com.xy.xyblog.service.blog;

import com.xy.xyblog.bean.Blog;
import com.xy.xyblog.bean.qbean.BlogQBean;
import com.xy.xyblog.dao.blog.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author xiuyang jlss2011@163.com
 * 2015-7-10
 */
@Service(value="blogService")
public class BlogServiceImpl implements BlogService{
    
    @Autowired
    private BlogDao blogDao;
    @Override
    public long insert(Blog blog) {
        blogDao.insert(blog);
        return blog.getId();
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public List<Blog> getBlogListBySearchIn(BlogQBean qbean) {
        return blogDao.getBlogListBySearchIn(qbean);
    }

    @Override
    public int countBlogListBySearchIn(BlogQBean qbean) {
        return blogDao.countBlogListBySearchIn(qbean);
    }

    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }

}
