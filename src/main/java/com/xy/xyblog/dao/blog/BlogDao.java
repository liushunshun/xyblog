package com.xy.xyblog.dao.blog;


import com.xy.xyblog.bean.Blog;
import com.xy.xyblog.bean.qbean.BlogQBean;

import java.util.List;

/**
 *博客文章dao
 * @author xiuyang jlss2011@163.com
 * 2015-7-10
 */
public interface BlogDao {
    public long insert(Blog blog);
    public Blog getBlogById(Long id);
    public List<Blog> getBlogListBySearchIn(BlogQBean qbean);
    public int countBlogListBySearchIn(BlogQBean qbean);
    public int deleteBlog(Long id);
}
