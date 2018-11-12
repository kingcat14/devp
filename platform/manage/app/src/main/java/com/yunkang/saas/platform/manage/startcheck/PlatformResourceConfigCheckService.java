package com.yunkang.saas.platform.manage.startcheck;

import com.yunkang.saas.bootstrap.application.business.resource.domain.Resource;
import com.yunkang.saas.bootstrap.application.business.resource.service.ResourceService;
import com.yunkang.saas.bootstrap.application.business.security.domain.RoleResourceRelation;
import com.yunkang.saas.bootstrap.application.business.security.service.RoleResourceRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 系统管理功能配置页面
 */
@Component
@Order(value=1)
public class PlatformResourceConfigCheckService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlatformResourceConfigCheckService.class);
	public static final long CODE_PTGL = 999L;
	public static final long CODE_PTGL_AQPZ = 999001L;
	public static final long CODE_PTGL_AQPZ_ZYPZ = 999001001L;
	public static final long CODE_PTGL_AQPZ_JSGL = 999001002L;
	public static final long CODE_PTGL_AQPZ_ZHGL = 999001003L;
	public static final long CODE_PTGL_CSPZ = 999002L;
	public static final long CODE_PTGL_CSPZ_PTCSPZ = 999002001L;



	@Autowired
    private ResourceService resourceService;

	@Autowired
	private RoleResourceRelationService roleResourceRelationService;

	@Value("${application.code:-1}")
	private String appCode;

    @Override
    public void run(String... args) throws Exception {

	    Resource resource999 = new Resource();
		resource999.setHidden(true);
	    resource999.setId(999L);
	    resource999.setAppCode(appCode);
		resource999.setCode(CODE_PTGL);
	    resource999.setParentCode(Resource.TOP_NODE_ID);
	    resource999.setName("平台管理");
	    resource999.setType("module");
	    resource999.setOrderIndex(999);
	    check(resource999);

	    Resource resource999001 = new Resource();
		resource999001.setHidden(true);
		resource999001.setAppCode(appCode);
	    resource999001.setId(CODE_PTGL_AQPZ);
		resource999001.setCode(999001L);
	    resource999001.setParentCode(999L);
	    resource999001.setName("安全配置");
	    resource999001.setType("module");
	    resource999001.setOrderIndex(1);
	    check(resource999001);

	    Resource resource999001001 = new Resource();
		resource999001001.setHidden(true);
		resource999001001.setAppCode(appCode);
	    resource999001001.setId(CODE_PTGL_AQPZ_ZYPZ);
		resource999001001.setCode(CODE_PTGL_AQPZ_ZYPZ);
	    resource999001001.setParentCode(999001L);
	    resource999001001.setName("资源配置");
	    resource999001001.setType("function");
	    resource999001001.setUrl("AM.controller.platform.security.ResourceController");
	    resource999001001.setOrderIndex(1);
	    check(resource999001001);

	    Resource resource999001002 = new Resource();
		resource999001002.setHidden(true);
		resource999001002.setAppCode(appCode);
	    resource999001002.setId(CODE_PTGL_AQPZ_JSGL);
		resource999001002.setCode(CODE_PTGL_AQPZ_JSGL);
	    resource999001002.setParentCode(CODE_PTGL_AQPZ);
	    resource999001002.setName("角色管理");
	    resource999001002.setType("function");
	    resource999001002.setUrl("AM.controller.platform.security.RoleController");
	    resource999001002.setOrderIndex(2);
	    check(resource999001002);

	    Resource resource999001003 = new Resource();
		resource999001003.setHidden(true);
		resource999001003.setAppCode(appCode);
	    resource999001003.setId(CODE_PTGL_AQPZ_ZHGL);
		resource999001003.setCode(CODE_PTGL_AQPZ_ZHGL);
	    resource999001003.setParentCode(999001L);
	    resource999001003.setName("账号管理");
	    resource999001003.setType("function");
	    resource999001003.setUrl("AM.controller.platform.security.AccountController");
	    resource999001003.setOrderIndex(3);
	    check(resource999001003);

		Resource resource999003 = new Resource();
		resource999003.setHidden(true);
		resource999003.setAppCode(appCode);
		resource999003.setId(999003L);
		resource999003.setCode(999003L);
		resource999003.setParentCode(999L);
		resource999003.setName("租户管理");
		resource999003.setType("module");
		resource999003.setOrderIndex(3);
		check(resource999003);

		Resource resource999003001 = new Resource();
		resource999003001.setHidden(true);
		resource999003001.setAppCode(appCode);
		resource999003001.setId(999003001L);
		resource999003001.setCode(999003001L);
		resource999003001.setParentCode(999003L);
		resource999003001.setName("租户管理");
		resource999003001.setType("function");
		resource999003001.setUrl("AM.controller.platform.platform.tenant.TenantController");
		resource999003001.setOrderIndex(1);
		check(resource999003001);

		Resource resource999003002 = new Resource();
		resource999003002.setHidden(true);
		resource999003002.setAppCode(appCode);
		resource999003002.setId(999003002L);
		resource999003002.setCode(999003002L);
		resource999003002.setParentCode(999003L);
		resource999003002.setName("租户类型管理");
		resource999003002.setType("function");
		resource999003002.setUrl("AM.controller.platform.platform.tenant.TenantTypeController");
		resource999003002.setOrderIndex(2);
		check(resource999003002);

		Resource resource999004 = new Resource();
		resource999004.setHidden(true);
		resource999004.setAppCode(appCode);
		resource999004.setId(999004L);
		resource999004.setCode(999004L);
		resource999004.setParentCode(999L);
		resource999004.setName("应用管理");
		resource999004.setType("module");
		resource999004.setOrderIndex(4);
		check(resource999004);

		Resource resource999004001 = new Resource();
		resource999004001.setHidden(true);
		resource999004001.setAppCode(appCode);
		resource999004001.setId(999004001L);
		resource999004001.setCode(999004001L);
		resource999004001.setParentCode(999004L);
		resource999004001.setName("应用信息");
		resource999004001.setType("function");
		resource999004001.setUrl("AM.controller.platform.platform.application.AppController");
		resource999004001.setOrderIndex(1);
		check(resource999004001);

		Resource resource999004002 = new Resource();
		resource999004002.setHidden(true);
		resource999004002.setAppCode(appCode);
		resource999004002.setId(999004002L);
		resource999004002.setCode(999004002L);
		resource999004002.setParentCode(999004L);
		resource999004002.setName("应用认证管理");
		resource999004002.setType("function");
		resource999004002.setUrl("AM.controller.platform.platform.application.ApplicationPasswordController");
		resource999004002.setOrderIndex(2);
		check(resource999004002);

		Resource resource999004003 = new Resource();
		resource999004003.setHidden(true);
		resource999004003.setAppCode(appCode);
		resource999004003.setId(999004003L);
		resource999004003.setCode(999004003L);
		resource999004003.setParentCode(999004L);
		resource999004003.setName("应用种类管理");
		resource999004003.setType("function");
		resource999004003.setUrl("AM.controller.platform.platform.application.ConfigAppCategoryController");
		resource999004003.setOrderIndex(3);
		check(resource999004003);

		checkRelation();
    }


    public void checkRelation(){
		RoleResourceRelation relation = new RoleResourceRelation();
		relation.setRoleId(1L);
		relation.setResourceId(CODE_PTGL);
		check(relation);

		relation = new RoleResourceRelation();
		relation.setRoleId(1L);
		relation.setResourceId(CODE_PTGL_AQPZ);
		check(relation);

		relation = new RoleResourceRelation();
		relation.setRoleId(1L);
		relation.setResourceId(CODE_PTGL_AQPZ_ZYPZ);
		check(relation);

		relation = new RoleResourceRelation();
		relation.setRoleId(1L);
		relation.setResourceId(CODE_PTGL_AQPZ_JSGL);
		check(relation);

		relation = new RoleResourceRelation();
		relation.setRoleId(1L);
		relation.setResourceId(CODE_PTGL_AQPZ_ZHGL);
		check(relation);

		relation = new RoleResourceRelation();
		relation.setRoleId(1L);
		relation.setResourceId(999003L);
		check(relation);

		relation = new RoleResourceRelation();
		relation.setRoleId(1L);
		relation.setResourceId(999003001L);
		check(relation);

		relation = new RoleResourceRelation();
		relation.setRoleId(1L);
		relation.setResourceId(999003002L);
		check(relation);

		relation = new RoleResourceRelation();
		relation.setRoleId(1L);
		relation.setResourceId(999004L);
		check(relation);

		relation = new RoleResourceRelation();
		relation.setRoleId(1L);
		relation.setResourceId(999004001L);
		check(relation);

		relation = new RoleResourceRelation();
		relation.setRoleId(1L);
		relation.setResourceId(999004002L);
		check(relation);

		relation = new RoleResourceRelation();
		relation.setRoleId(1L);
		relation.setResourceId(999004003L);
		check(relation);



	}

    private void check(Resource resource){
	    LOGGER.info("[check resource]:{}", resource);
    	Resource resource1 = resourceService.findByCodeAndAppCode(resource);
    	if(resource1 == null){
    		resourceService.add(resource);
	    }
    }



    private void check(RoleResourceRelation resourceRelation){
    	int count = roleResourceRelationService.countByRoleIdAndResourceId(resourceRelation.getRoleId(), resourceRelation.getResourceId());
    	if(count <= 0){
			roleResourceRelationService.add(resourceRelation);
		}
	}


}