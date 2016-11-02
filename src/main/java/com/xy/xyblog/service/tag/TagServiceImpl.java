package com.xy.xyblog.service.tag;

import com.xy.xyblog.bean.Tag;
import com.xy.xyblog.bean.TagRelation;
import com.xy.xyblog.dao.tag.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author xiuyang jlss2011@163.com
 * 2015-7-22
 */
@Service(value="tagService")
public class TagServiceImpl implements TagService{

    @Autowired
    private TagDao tagDao;
    @Override
    public List<Tag> getTagByType(Integer type) {
        return tagDao.getTagByType(type);
    }

    @Override
    public int insertTagRelation(List<TagRelation> relations) {
        return tagDao.insertTagRelationBatch(relations);
    }

    @Override
    public int countRelationByType(Integer tagId, Integer relationType) {
        return tagDao.countRelationByType(tagId, relationType);
    }

}
