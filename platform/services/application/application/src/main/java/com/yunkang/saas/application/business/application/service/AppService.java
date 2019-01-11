package com.yunkang.saas.application.business.application.service;


import com.yunkang.saas.application.business.application.dao.AppDao;
import com.yunkang.saas.application.business.application.dao.AppSpecification;
import com.yunkang.saas.application.business.application.domain.App;
import com.yunkang.saas.application.business.application.dto.AppCondition;
import com.yunkang.saas.common.jpa.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("appService")
public class AppService  extends CrudService<App, AppCondition, AppDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	@Override
	public Specification<App> getSpecification(AppCondition condition) {
		return new AppSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , App.PROPERTY_NAME);
		return sort;
	}
}