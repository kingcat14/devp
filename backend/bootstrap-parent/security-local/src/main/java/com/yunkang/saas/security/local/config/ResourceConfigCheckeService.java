package com.yunkang.saas.security.local.config;


import com.yunkang.saas.bootstrap.business.platform.security.domain.RoleResourceRelation;
import com.yunkang.saas.bootstrap.business.platform.security.service.RoleResourceRelationService;
import com.yunkang.saas.bootstrap.business.resource.domain.Resource;
import com.yunkang.saas.bootstrap.business.resource.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

/**
 * 系统管理功能配置页面
 */
//@Component
//@Order(value=1)
public class ResourceConfigCheckeService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceConfigCheckeService.class);

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
		resource999001002.setOrderIndex(1);
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
		resource999001003.setOrderIndex(1);
		check(resource999001003);

		Resource resource999002 = new Resource();
		resource999002.setHidden(true);
		resource999002.setAppCode(appCode);
		resource999002.setId(CODE_PTGL_CSPZ);
		resource999002.setCode(CODE_PTGL_CSPZ);
		resource999002.setParentCode(CODE_PTGL);
		resource999002.setName("参数配置");
		resource999002.setType("module");
		resource999002.setOrderIndex(1);
		check(resource999002);

		Resource resource999002001 = new Resource();
		resource999002001.setHidden(true);
		resource999002001.setAppCode(appCode);
		resource999002001.setId(CODE_PTGL_CSPZ_PTCSPZ);
		resource999002001.setCode(CODE_PTGL_CSPZ_PTCSPZ);
		resource999002001.setParentCode(CODE_PTGL_CSPZ);
		resource999002001.setName("平台参数配置");
		resource999002001.setType("function");
		resource999002001.setUrl("AM.controller.platform.config.PlatformConfigController");
		resource999002001.setOrderIndex(1);
		check(resource999002001);

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


	}

	private void check(Resource resource){
		LOGGER.info("[check resource]:{}", resource);
		Resource resource1 = resourceService.find(resource.getId());
		if(resource1 == null){
			resourceService.merge(resource);
		}
	}



	private void check(RoleResourceRelation resourceRelation){
		int count = roleResourceRelationService.countByRoleIdAndResourceId(resourceRelation.getRoleId(), resourceRelation.getResourceId());
		if(count <= 0){
			roleResourceRelationService.add(resourceRelation);
		}
	}

}