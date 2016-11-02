package com.xy.xyblog.dao.blog;

import com.xy.xyblog.bean.Blog;
import com.xy.xyblog.bean.qbean.BlogQBean;
import com.xy.xyblog.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author xiuyang jlss2011@163.com
 * 2015-7-10
 */
@Repository(value="blogDao")
public class BlogDaoImpl implements BlogDao{
    
    @Autowired
    private BlogMapper blogMapper;
    
    @Override
    public long insert(Blog blog) {
        return blogMapper.insert(blog);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public List<Blog> getBlogListBySearchIn(BlogQBean qbean) {
        return blogMapper.getBlogListBySearchIn(qbean);
    }

    @Override
    public int countBlogListBySearchIn(BlogQBean qbean) {
        return blogMapper.countBlogListBySearchIn(qbean);
    }

    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }
    
}
