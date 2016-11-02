package com.xy.xyblog.bean.ext;


import com.xy.xyblog.bean.Tag;

/**
 *
 * @author xiuyang jlss2011@163.com
 * 2015-7-22
 */
public class TagExt extends Tag {
    public int blogCount;

    public int getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(int blogCount) {
        this.blogCount = blogCount;
    }
    
}
