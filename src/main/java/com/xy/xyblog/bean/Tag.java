package com.xy.xyblog.bean;

/**
 * 标签
 * @author xiuyang jlss2011@163.com
 * 2015-7-21
 */
public class Tag {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 标签所属分类
     */
    private Integer tagType;
    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 删除标志
     */
    private Integer isValid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTagType() {
        return tagType;
    }

    public void setTagType(Integer tagType) {
        this.tagType = tagType;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
}
