package com.yunkang.saas.platform.authentication.business.platform.application.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.authentication.business.platform.application.dao.ConfigAppCategoryDao;
import com.yunkang.saas.platform.authentication.business.platform.application.dao.ConfigAppCategorySpecification;
import com.yunkang.saas.platform.authentication.business.platform.application.domain.ConfigAppCategory;
import com.yunkang.saas.platform.authentication.business.platform.application.dto.ConfigAppCategoryCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("configAppCategoryService")
@Slf4j
public class ConfigAppCategoryService  extends GenericCrudService<ConfigAppCategory, Long, ConfigAppCategoryCondition, ConfigAppCategoryDao> {

	@Override
	public Specification<ConfigAppCategory> getSpecification(ConfigAppCategoryCondition condition) {
		return new ConfigAppCategorySpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC);

		return sort;
	}
}