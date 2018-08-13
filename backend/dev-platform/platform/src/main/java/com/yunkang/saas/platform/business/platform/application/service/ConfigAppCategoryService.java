package com.yunkang.saas.platform.business.platform.application.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.business.platform.application.dao.ConfigAppCategoryDao;
import com.yunkang.saas.platform.business.platform.application.dao.ConfigAppCategorySpecification;
import com.yunkang.saas.platform.business.platform.application.domain.ConfigAppCategory;
import com.yunkang.saas.platform.business.platform.application.dto.ConfigAppCategoryCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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