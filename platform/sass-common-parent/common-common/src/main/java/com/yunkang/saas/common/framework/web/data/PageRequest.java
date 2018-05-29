/*
 * Copyright 2008-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yunkang.saas.common.framework.web.data;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * 
 * @author Oliver Gierke
 * @author Thomas Darimont
 */
public class PageRequest extends AbstractPageRequest {

	private static final long serialVersionUID = -4541509938956089562L;

	private Sort sort;

	public PageRequest() {
		this(0, 10, (Sort)null);
	}

	public PageRequest(int page, int size) {
		this(page, size, (Sort)null);
	}

	public PageRequest(int page, int size, Direction direction, String... properties) {
		this(page, size, new Sort(direction, properties));
	}

	public PageRequest(int page, int size, Sort sort) {
		super(page, size);
		this.sort = sort;
	}

	@Override
	public Sort getSort() {
		return this.sort;
	}

	public void setSort(Sort sort){
		this.sort = sort;
	}

	@Override
	public Pageable next() {
		return new PageRequest(this.getPageNumber() + 1, this.getPageSize(), this.getSort());
	}

	@Override
	public PageRequest previous() {
		return this.getPageNumber() == 0?this:new PageRequest(this.getPageNumber() - 1, this.getPageSize(), this.getSort());
	}

	@Override
	public Pageable first() {
		return new PageRequest(0, this.getPageSize(), this.getSort());
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		} else if(!(obj instanceof PageRequest)) {
			return false;
		} else {
			PageRequest that = (PageRequest)obj;
			boolean sortEqual = this.sort == null?that.sort == null:this.sort.equals(that.sort);
			return super.equals(that) && sortEqual;
		}
	}

	@Override
	public int hashCode() {
		return 31 * super.hashCode() + (null == this.sort?0:this.sort.hashCode());
	}

	@Override
	public String toString() {
		return String.format("Page request, number: %d, size %d, sort: %s", Integer.valueOf(this.getPageNumber()), Integer.valueOf(this.getPageSize()), this.sort == null?"":this.sort.toString());
	}


}
