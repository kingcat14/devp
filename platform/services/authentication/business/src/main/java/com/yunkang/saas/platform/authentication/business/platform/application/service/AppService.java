package com.yunkang.saas.platform.authentication.business.platform.application.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.authentication.business.platform.application.dao.AppDao;
import com.yunkang.saas.platform.authentication.business.platform.application.dao.AppSpecification;
import com.yunkang.saas.platform.authentication.business.platform.application.domain.App;
import com.yunkang.saas.platform.authentication.business.platform.application.dto.AppCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("appService")
@Slf4j
public class AppService  extends GenericCrudService<App, Long, AppCondition, AppDao> {

	@Override
	public Specification<App> getSpecification(AppCondition condition) {
		return new AppSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC);

		return sort;
	}
}