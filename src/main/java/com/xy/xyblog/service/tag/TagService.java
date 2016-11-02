package com.xy.xyblog.service.tag;

import com.xy.xyblog.bean.Tag;
import com.xy.xyblog.bean.TagRelation;

import java.util.List;

/**
 * 标签
 * @author alex
 * 2015-7-21
 */
public interface TagService {
    public List<Tag> getTagByType(Integer type);
    
    public int insertTagRelation(List<TagRelation> relations);
    
    public int countRelationByType(Integer tagId, Integer relationType);
}
