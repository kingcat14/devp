package com.yunkang.saas.security.service.business.tenant.service;


import com.yunkang.saas.common.jpa.CrudService;
import com.yunkang.saas.security.service.business.tenant.dao.TenantDao;
import com.yunkang.saas.security.service.business.tenant.dao.TenantSpecification;
import com.yunkang.saas.security.service.business.tenant.domain.Tenant;
import com.yunkang.saas.tenant.business.tenant.dto.TenantCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("tenantService")
public class TenantService  extends CrudService<Tenant, TenantCondition, TenantDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TenantService.class);

	@Override
	public Specification<Tenant> getSpecification(TenantCondition condition) {
		return new TenantSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , Tenant.PROPERTY_TENANT_CODE);
		return sort;
	}
}