package com.xy.xyblog.bean;

/**
 *
 * @author xiuyang jlss2011@163.com
 * 2015-7-21
 */
public class TagRelation {
    private Long id;
    private Integer relType;
    private Long relId;
    private Integer tagId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRelType() {
        return relType;
    }

    public void setRelType(Integer relType) {
        this.relType = relType;
    }

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public TagRelation(Integer relType, Long relId, Integer tagId) {
        this.relType = relType;
        this.relId = relId;
        this.tagId = tagId;
    }

    public TagRelation() {
    }
    
    
}
