package com.yunkang.saas.platform.monitor.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.app.dao.ApplicationDao;
import com.yunkang.saas.platform.monitor.business.app.dao.ApplicationSpecification;
import com.yunkang.saas.platform.monitor.business.app.domain.Application;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("applicationService")
public class ApplicationService  extends GenericCrudService<Application, String, ApplicationCondition, ApplicationDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);

	@Override
	public Specification<Application> getSpecification(ApplicationCondition condition) {
		return new ApplicationSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Application.PROPERTY_NAME);
		return sort;
	}
}