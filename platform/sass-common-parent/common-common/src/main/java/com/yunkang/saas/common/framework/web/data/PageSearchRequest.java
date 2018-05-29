package com.yunkang.saas.common.framework.web.data;

/**
 * Created by gonghongrui on 16/10/25.
 */
public class PageSearchRequest<T>  {

	private Integer page;
	private Integer limit;
	T searchCondition;
	private SortCondition sortCondition;

	public T getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(T searchCondition) {
		this.searchCondition = searchCondition;
	}

	public Integer getPage() {
		return page==null?0:this.page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit==null?10:limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public SortCondition getSortCondition() {
		return sortCondition;
	}

	public void setSortCondition(SortCondition sortCondition) {
		this.sortCondition = sortCondition;
	}
}

