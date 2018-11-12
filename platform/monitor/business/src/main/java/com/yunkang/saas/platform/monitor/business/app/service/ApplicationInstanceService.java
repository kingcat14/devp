package com.yunkang.saas.platform.monitor.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.app.dao.ApplicationInstanceDao;
import com.yunkang.saas.platform.monitor.business.app.dao.ApplicationInstanceSpecification;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationInstance;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationInstanceCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("applicationInstanceService")
public class ApplicationInstanceService  extends GenericCrudService<ApplicationInstance, String, ApplicationInstanceCondition, ApplicationInstanceDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInstanceService.class);

	@Override
	public Specification<ApplicationInstance> getSpecification(ApplicationInstanceCondition condition) {
		return new ApplicationInstanceSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ApplicationInstance.PROPERTY_APP);
		return sort;
	}
}