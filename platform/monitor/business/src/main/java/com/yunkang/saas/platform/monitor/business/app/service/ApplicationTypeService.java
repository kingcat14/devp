package com.yunkang.saas.platform.monitor.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.app.dao.ApplicationTypeDao;
import com.yunkang.saas.platform.monitor.business.app.dao.ApplicationTypeSpecification;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationType;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationTypeCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("applicationTypeService")
@Slf4j
public class ApplicationTypeService  extends GenericCrudService<ApplicationType, String, ApplicationTypeCondition, ApplicationTypeDao> {

	@Override
	public Specification<ApplicationType> getSpecification(ApplicationTypeCondition condition) {
		return new ApplicationTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ApplicationType.PROPERTY_CODE);
		return sort;
	}
}