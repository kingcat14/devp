package com.yunkang.saas.platform.business.platform.application.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.business.platform.application.dao.AppDao;
import com.yunkang.saas.platform.business.platform.application.dao.AppSpecification;
import com.yunkang.saas.platform.business.platform.application.domain.App;
import com.yunkang.saas.platform.business.platform.application.dto.AppCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("appService")
public class AppService  extends GenericCrudService<App, Long, AppCondition, AppDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppService.class);

	@Override
	public Specification<App> getSpecification(AppCondition condition) {
		return new AppSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , App.PROPERTY_NAME);
		return sort;
	}
}