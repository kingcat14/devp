package com.yunkang.saas.bootstrap.platform.business.platform.tenant.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.dao.TenantAdminAccountDao;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.dao.TenantAdminAccountSpecification;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.domain.TenantAdminAccount;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.dto.TenantAdminAccountCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("tenantAdminAccountService")
public class TenantAdminAccountService  extends GenericCrudService<TenantAdminAccount, Long, TenantAdminAccountCondition, TenantAdminAccountDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TenantAdminAccountService.class);

	public TenantAdminAccount findByTid(Long tenantId){
		return dao.findByTid(tenantId);
	}

	@Override
	public Specification<TenantAdminAccount> getSpecification(TenantAdminAccountCondition condition) {
		return new TenantAdminAccountSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, TenantAdminAccount.PROPERTY_ACCOUNT_NAME);
		return sort;
	}
}