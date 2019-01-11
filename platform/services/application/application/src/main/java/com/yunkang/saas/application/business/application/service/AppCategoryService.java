package com.yunkang.saas.application.business.application.service;


import com.yunkang.saas.application.business.application.dao.AppCategoryDao;
import com.yunkang.saas.application.business.application.dao.AppCategorySpecification;
import com.yunkang.saas.application.business.application.domain.AppCategory;
import com.yunkang.saas.application.business.application.dto.AppCategoryCondition;
import com.yunkang.saas.common.jpa.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("appCategoryService")
public class AppCategoryService  extends CrudService<AppCategory, AppCategoryCondition, AppCategoryDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppCategory.class);

	@Override
	public Specification<AppCategory> getSpecification(AppCategoryCondition condition) {
		return new AppCategorySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , AppCategory.PROPERTY_NAME);
		return sort;
	}
}