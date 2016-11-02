package com.xy.xyblog.dao.tag;

import com.xy.xyblog.bean.Tag;
import com.xy.xyblog.bean.TagRelation;

import java.util.List;

/**
 *
 * @author alex
 * 2015-7-22
 */
public interface TagDao {
    public int insertTagRelationBatch(List<TagRelation> tagRelations);
    public List<Tag> getTagByType(Integer tagType);
    public int countRelationByType(Integer tagId, Integer relationType);
}
