package com.yunkang.saas.platform.authentication.business.platform.tenant.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dao.TenantDao;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dao.TenantSpecification;
import com.yunkang.saas.platform.authentication.business.platform.tenant.domain.Tenant;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("tenantService")
@Slf4j
public class TenantService  extends GenericCrudService<Tenant, Long, TenantCondition, TenantDao> {

	@Override
	public Specification<Tenant> getSpecification(TenantCondition condition) {
		return new TenantSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC);

		return sort;
	}
}