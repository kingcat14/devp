package com.yunkang.saas.platform.authentication.business.platform.tenant.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dao.TenantAdminAccountDao;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dao.TenantAdminAccountSpecification;
import com.yunkang.saas.platform.authentication.business.platform.tenant.domain.TenantAdminAccount;
import com.yunkang.saas.platform.authentication.business.platform.tenant.dto.TenantAdminAccountCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("tenantAdminAccountService")
@Slf4j
public class TenantAdminAccountService  extends GenericCrudService<TenantAdminAccount, Long, TenantAdminAccountCondition, TenantAdminAccountDao> {

	@Override
	public Specification<TenantAdminAccount> getSpecification(TenantAdminAccountCondition condition) {
		return new TenantAdminAccountSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC);

		return sort;
	}
}