package com.yunkang.saas.security.local.config;


import com.yunkang.saas.platform.business.resource.domain.Resource;
import com.yunkang.saas.platform.business.resource.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 系统管理功能配置页面
 */
@Component
@Order(value=1)
public class ResourceConfigCheckeService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceConfigCheckeService.class);


	@Autowired
    private ResourceService resourceService;


    @Override
    public void run(String... args) throws Exception {

		Resource resource999 = new Resource();
		resource999.setId(999L);
		resource999.setCode(999L);
		resource999.setParentCode(Resource.TOP_NODE_ID);
		resource999.setName("系统管理");
		resource999.setType("menu");
		resource999.setOrderIndex(999);
		checkResource(resource999);

		Resource resource999001 = new Resource();
		resource999001.setId(999001L);
		resource999001.setCode(999001L);
		resource999001.setParentCode(999L);
		resource999001.setName("安全配置");
		resource999001.setType("menu");
		resource999001.setOrderIndex(1);
		checkResource(resource999001);

		Resource resource999001001 = new Resource();
		resource999001001.setId(999001001L);
		resource999001001.setCode(999001001L);
		resource999001001.setParentCode(999001L);
		resource999001001.setName("资源配置");
		resource999001001.setType("page");
		resource999001001.setUrl("AM.controller.platform.security.ResourceController");
		resource999001001.setOrderIndex(1);
		checkResource(resource999001001);

		Resource resource999001002 = new Resource();
		resource999001002.setId(999001002L);
		resource999001002.setCode(999001002L);
		resource999001002.setParentCode(999001L);
		resource999001002.setName("角色管理");
		resource999001002.setType("page");
		resource999001002.setUrl("AM.controller.platform.security.RoleController");
		resource999001002.setOrderIndex(1);
		checkResource(resource999001002);

		Resource resource999001003 = new Resource();
		resource999001003.setId(999001003L);
		resource999001003.setCode(999001003L);
		resource999001003.setParentCode(999001L);
		resource999001003.setName("账号管理");
		resource999001003.setType("page");
		resource999001003.setUrl("AM.controller.platform.security.AccountController");
		resource999001003.setOrderIndex(1);
		checkResource(resource999001003);

		Resource resource999002 = new Resource();
		resource999002.setId(999002L);
		resource999002.setCode(999002L);
		resource999002.setParentCode(999L);
		resource999002.setName("参数配置");
		resource999002.setType("menu");
		resource999002.setOrderIndex(1);
		checkResource(resource999002);

		Resource resource999002001 = new Resource();
		resource999002001.setId(999002001L);
		resource999002001.setCode(999002001L);
		resource999002001.setParentCode(999002L);
		resource999002001.setName("平台参数配置");
		resource999002001.setType("page");
		resource999002001.setUrl("AM.controller.platform.config.PlatformConfigController");
		resource999002001.setOrderIndex(1);
		checkResource(resource999002001);


    }

    private void checkResource(Resource resource){
	    LOGGER.info("[check resource]:{}", resource);
    	Resource resource1 = resourceService.find(resource.getId());
    	if(resource1 == null){
    		resourceService.merge(resource);
	    }
    }

}