package com.yunkang.saas.platform.authentication.business.platform.application.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.authentication.business.platform.application.dao.ApplicationPasswordDao;
import com.yunkang.saas.platform.authentication.business.platform.application.dao.ApplicationPasswordSpecification;
import com.yunkang.saas.platform.authentication.business.platform.application.domain.ApplicationPassword;
import com.yunkang.saas.platform.authentication.business.platform.application.dto.ApplicationPasswordCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("applicationPasswordService")
@Slf4j
public class ApplicationPasswordService  extends GenericCrudService<ApplicationPassword, Long, ApplicationPasswordCondition, ApplicationPasswordDao> {

	@Override
	public Specification<ApplicationPassword> getSpecification(ApplicationPasswordCondition condition) {
		return new ApplicationPasswordSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC);

		return sort;
	}
}