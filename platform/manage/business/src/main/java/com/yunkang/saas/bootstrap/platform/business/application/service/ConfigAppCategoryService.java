package com.yunkang.saas.bootstrap.platform.business.application.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.bootstrap.platform.business.application.dao.ConfigAppCategoryDao;
import com.yunkang.saas.bootstrap.platform.business.application.dao.ConfigAppCategorySpecification;
import com.yunkang.saas.bootstrap.platform.business.application.domain.ConfigAppCategory;
import com.yunkang.saas.bootstrap.platform.business.application.dto.ConfigAppCategoryCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("configAppCategoryService")
public class ConfigAppCategoryService  extends GenericCrudService<ConfigAppCategory, Long, ConfigAppCategoryCondition, ConfigAppCategoryDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigAppCategoryService.class);

	@Override
	public Specification<ConfigAppCategory> getSpecification(ConfigAppCategoryCondition condition) {
		return new ConfigAppCategorySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , ConfigAppCategory.PROPERTY_NAME);
		return sort;
	}
}