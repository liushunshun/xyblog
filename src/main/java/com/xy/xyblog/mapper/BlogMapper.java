package com.xy.xyblog.mapper;



import com.sun.org.glassfish.gmbal.ManagedData;
import com.xy.xyblog.bean.Blog;
import com.xy.xyblog.bean.qbean.BlogQBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @author alex
 */
@Mapper
public interface BlogMapper {
    public long insert(Blog blog);
    public Blog getBlogById(Long id);
    public List<Blog> getBlogListBySearchIn(BlogQBean qbean);
    public int countBlogListBySearchIn(BlogQBean qbean);
    public int deleteBlog(Long id);
}
