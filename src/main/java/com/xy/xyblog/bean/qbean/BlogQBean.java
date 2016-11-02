package com.xy.xyblog.bean.qbean;

/**
 * 博客列表查询条件
 *
 * @author xiuyang jlss2011@163.com 2015-7-10
 */
public class BlogQBean extends PageQbean {
    /**
     * 标题
     */
    private String title;
    /**
     * 分类
     */
    private Integer classify;
    /**
     * 标签Id
     */
    private Integer tagId;
    /**
     * 排序字段
     */
    private String sort;
    /**
     * 排序方式，升序或者降序
     */
    private String order = "desc";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
    
}
