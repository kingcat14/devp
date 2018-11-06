package com.yunkang.saas.bootstrap.platform.business.application.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.bootstrap.platform.business.application.dao.AppDao;
import com.yunkang.saas.bootstrap.platform.business.application.dao.AppSpecification;
import com.yunkang.saas.bootstrap.platform.business.application.domain.App;
import com.yunkang.saas.bootstrap.platform.business.application.dto.AppCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("appService")
public class AppService  extends GenericCrudService<App, Long, AppCondition, AppDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppService.class);

	public App findByCode(String code){
		return dao.findByCode(code);
	}

	@Override
	public Specification<App> getSpecification(AppCondition condition) {
		return new AppSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , App.PROPERTY_NAME);
		return sort;
	}
}