package com.xy.xyblog.bean.qbean;

public class PageQbean {
	//默认页大小
	private static final int DEFAULT_PAGE_SIZE=10;
	//当前页号
	private Integer pageNum=1;
	//页大小
	private Integer pageSize=DEFAULT_PAGE_SIZE;
	//偏移量
	private Integer offset;
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getOffset(){
		this.offset=computeOffset();
		return offset;
	}
	public void setOffset(Integer offset){
		this.offset = offset;
	}
	private Integer computeOffset(){
		if(offset!= null){
			return offset;
		}
		if(pageNum==null)
			return null;
		if(pageSize==null)
			return (pageNum-1)*DEFAULT_PAGE_SIZE;
		return (pageNum-1)*pageSize;
	}
}
