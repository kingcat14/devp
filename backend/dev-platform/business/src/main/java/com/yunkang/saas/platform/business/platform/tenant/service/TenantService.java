package com.yunkang.saas.platform.business.platform.tenant.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.business.platform.tenant.dao.TenantDao;
import com.yunkang.saas.platform.business.platform.tenant.dao.TenantSpecification;
import com.yunkang.saas.platform.business.platform.tenant.domain.Tenant;
import com.yunkang.saas.platform.business.platform.tenant.dto.TenantCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("tenantService")
public class TenantService  extends GenericCrudService<Tenant, Long, TenantCondition, TenantDao> {

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