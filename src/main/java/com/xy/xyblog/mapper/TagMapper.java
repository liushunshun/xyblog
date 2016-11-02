package com.xy.xyblog.mapper;


import com.xy.xyblog.bean.Tag;
import com.xy.xyblog.bean.TagRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author alex
 */
@Mapper
public interface TagMapper {
    public int insertTagRelationBatch(List<TagRelation> tagRelations);
    public List<Tag> getTagByType(Integer tagType);
    public int countRelationByType(@Param("tagId") Integer tagId, @Param("relationType") Integer relationType);
}
