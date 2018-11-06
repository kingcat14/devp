package com.yunkang.saas.bootstrap.platform.business.application.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.bootstrap.platform.business.application.dao.ApplicationPasswordDao;
import com.yunkang.saas.bootstrap.platform.business.application.dao.ApplicationPasswordSpecification;
import com.yunkang.saas.bootstrap.platform.business.application.domain.ApplicationPassword;
import com.yunkang.saas.bootstrap.platform.business.application.dto.ApplicationPasswordCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("applicationPasswordService")
public class ApplicationPasswordService  extends GenericCrudService<ApplicationPassword, Long, ApplicationPasswordCondition, ApplicationPasswordDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationPasswordService.class);

	@Override
	public Specification<ApplicationPassword> getSpecification(ApplicationPasswordCondition condition) {
		return new ApplicationPasswordSpecification(condition);
	}

	public Sort getDefaultSort(){

		return null;
	}
}