package com.yunkang.saas.bootstrap.platform.startcheck;

import com.yunkang.saas.bootstrap.platform.business.platform.tenant.domain.Tenant;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.domain.TenantType;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.service.TenantService;
import com.yunkang.saas.bootstrap.platform.business.platform.tenant.service.TenantTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 检查租户的初始化配置
 */
@Component
@Order(value=1)
public class PlatformTenantCheckeService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlatformTenantCheckeService.class);

	@Autowired
	private TenantService tenantService;

	@Autowired
	private TenantTypeService tenantTypeService;

    @Override
    public void run(String... args) throws Exception {


		checkTenantType();
		checkTenant();

    }

    private void checkTenant(){
    	Tenant tenant = new Tenant();
    	tenant.setId(1L);
    	tenant.setName("超级管理员");
		tenant.setStatus(true);
    	tenant.setTenantCode("TENANT_SUPER");
    	tenant.setTenantType(1L);
    	check(tenant);
	}

	private void checkTenantType(){
		TenantType tenantType = new TenantType();
		tenantType.setId(1L);
		tenantType.setName("超管租户");
		tenantType.setTenantTypeCode("TENANT_SUPER");
		check(tenantType);

		tenantType.setId(-1L);
		tenantType.setName("测试租户");
		tenantType.setTenantTypeCode("TENANT_TEST");
		check(tenantType);

		tenantType.setId(2L);
		tenantType.setName("普通租户");
		tenantType.setTenantTypeCode("TENANT_COMMON");
		check(tenantType);
	}



	private void check(TenantType target){
		LOGGER.info("[check TenantType]:{}", target);
		TenantType entity = tenantTypeService.find(target.getId());
		if(entity == null){
			tenantTypeService.merge(target);
		}
	}

	private void check(Tenant target){
		LOGGER.info("[check Tenant]:{}", target);
		Tenant entity = tenantService.find(target.getId());
		if(entity == null){
			tenantService.merge(target);
		}
	}


}