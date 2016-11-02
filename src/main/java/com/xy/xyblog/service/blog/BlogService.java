package com.xy.xyblog.service.blog;

import com.xy.xyblog.bean.Blog;
import com.xy.xyblog.bean.qbean.BlogQBean;

import java.util.List;

/**
 * 博客文章Service
 * @author xiuyang jlss2011@163.com
 * 2015-7-10
 */
public interface  BlogService {
    /**
     * 插入文章
     * @param blog
     * @return 
     */
    public long insert(Blog blog);
    /**
     * 根据id查询文章
     * @param id
     * @return 
     */
    public Blog getBlogById(Long id);
    /**
     * 根据查询条件查询文章列表
     * @param qbean
     * @return 
     */
    public List<Blog> getBlogListBySearchIn(BlogQBean qbean);
    /**
     * 根据条件统计文章数量
     * @param qbean
     * @return 
     */
    public int countBlogListBySearchIn(BlogQBean qbean);
    /**
     * 根据id删除
     * @param id
     * @return 
     */
    public int deleteBlog(Long id);
}
