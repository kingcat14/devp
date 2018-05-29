package com.yunkang.saas.common.jpa;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author gonghongrui on 2018/4/17.
 */
public interface BaseSpecification<T, C> extends Specification<T> {

	void setCondition(C c);
}
