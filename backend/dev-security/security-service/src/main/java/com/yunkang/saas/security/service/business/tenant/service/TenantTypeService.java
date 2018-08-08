package com.yunkang.saas.security.service.business.tenant.service;


import com.yunkang.saas.common.jpa.CrudService;
import com.yunkang.saas.security.service.business.tenant.dao.TenantTypeDao;
import com.yunkang.saas.security.service.business.tenant.dao.TenantTypeSpecification;
import com.yunkang.saas.security.service.business.tenant.domain.TenantType;
import com.yunkang.saas.tenant.business.tenant.dto.TenantTypeCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("tenantTypeService")
public class TenantTypeService  extends CrudService<TenantType, TenantTypeCondition, TenantTypeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TenantTypeService.class);

	@Override
	public Specification<TenantType> getSpecification(TenantTypeCondition condition) {
		return new TenantTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , TenantType.PROPERTY_TENANT_TYPE_CODE);
		return sort;
	}
}