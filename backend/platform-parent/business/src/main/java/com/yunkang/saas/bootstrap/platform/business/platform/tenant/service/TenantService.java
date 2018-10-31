package com.yunkang.saas.bootstrap.platform.business.platform.tenant.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.dao.TenantDao;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.dao.TenantSpecification;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.domain.Tenant;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.domain.TenantAddEvent;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.dto.TenantCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("tenantService")
public class TenantService  extends GenericCrudService<Tenant, Long, TenantCondition, TenantDao> implements ApplicationContextAware {

	private static final Logger LOGGER = LoggerFactory.getLogger(TenantService.class);

	private static ApplicationContext applicationContext;

	@Transactional
	public void add(Tenant t){
		t.fillId();
		dao.save(t);
		TenantAddEvent tenantAddEvent = new TenantAddEvent(this, t);
		applicationContext.publishEvent(tenantAddEvent);
	}

	@Override
	public Specification<Tenant> getSpecification(TenantCondition condition) {
		return new TenantSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , Tenant.PROPERTY_TENANT_CODE);
		return sort;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}