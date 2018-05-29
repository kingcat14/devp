package com.yunkang.saas.common.framework.web.controller;



import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonghongrui on 16/7/22.
 */
public class PageContent<T>  {

	private static final long serialVersionUID = 867755909294344406L;

	private ArrayList<T> content = new ArrayList<>();

	private long total;

	public PageContent(){}

	public PageContent(List<T> content, long total){

		Assert.notNull(content, "Content must not be null!");

		this.content.addAll(content);

		this.total = total;

	}

	public List<T> getContent(){
		return content;
	}

	/**
	 * Returns the total amount of elements.
	 *
	 * @return the total amount of elements
	 */
	public long getTotal(){
		return total;
	}

	public long getTotalElements() {
		return total;
	}


	public void setContent(List<T> content) {
		this.content.clear();
		this.content.addAll(content);
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
