package com.xy.xyblog.dao.tag;

import com.xy.xyblog.bean.Tag;
import com.xy.xyblog.bean.TagRelation;
import com.xy.xyblog.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author alex
 * 2015-7-22
 */
@Repository(value="tagDao")
public class TagDaoImpl implements TagDao{
    @Autowired
    private TagMapper tagMapper;

    @Override
    public int insertTagRelationBatch(List<TagRelation> tagRelations) {
        return tagMapper.insertTagRelationBatch(tagRelations);
    }

    @Override
    public List<Tag> getTagByType(Integer tagType) {
        return tagMapper.getTagByType(tagType);
    }

    @Override
    public int countRelationByType(Integer tagId, Integer relationType) {
        return tagMapper.countRelationByType(tagId, relationType);
    }
    
}
