package com.xy.xyblog.bean.ext;


import com.xy.xyblog.bean.Blog;
import com.xy.xyblog.bean.Tag;

import java.util.List;

/**
 *
 * @author xiuyang jlss2011@163.com
 * 2015-7-23
 */
public class BlogExt extends Blog {
    private List<Tag> tagList;

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    
}
