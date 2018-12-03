package com.yunkang.saas.platform.authentication.business.platform.tenant.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dao.TenantTypeDao;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dao.TenantTypeSpecification;
import com.yunkang.saas.platform.authentication.business.platform.tenant.domain.TenantType;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantTypeCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("tenantTypeService")
@Slf4j
public class TenantTypeService  extends GenericCrudService<TenantType, Long, TenantTypeCondition, TenantTypeDao> {

	@Override
	public Specification<TenantType> getSpecification(TenantTypeCondition condition) {
		return new TenantTypeSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC);

		return sort;
	}
}