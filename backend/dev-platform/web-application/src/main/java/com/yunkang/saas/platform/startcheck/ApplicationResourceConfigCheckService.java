package com.yunkang.saas.platform.startcheck;

import com.yunkang.saas.platform.business.resource.domain.Resource;
import com.yunkang.saas.platform.business.resource.service.ResourceService;
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
public class ApplicationResourceConfigCheckService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationResourceConfigCheckService.class);

	@Autowired
    private ResourceService resourceService;

	@Value("${sys.id:-1}")
	private Long appId;

    @Override
    public void run(String... args) throws Exception {

	    Resource resource998 = new Resource();
	    resource998.setId(998L);
		resource998.setAppId(appId);
		resource998.setCode(998L);
	    resource998.setParentCode(Resource.TOP_NODE_ID);
	    resource998.setName("系统管理");
	    resource998.setType("menu");
	    resource998.setOrderIndex(998);
	    checkResource(resource998);

	    Resource resource998001 = new Resource();
	    resource998001.setId(998001L);
		resource998001.setAppId(appId);
		resource998001.setCode(998001L);
	    resource998001.setParentCode(998L);
	    resource998001.setName("安全配置");
	    resource998001.setType("menu");
	    resource998001.setOrderIndex(1);
	    checkResource(resource998001);

//	    Resource resource998001001 = new Resource();
//	    resource998001001.setId(998001001L);
//	    resource998001001.setParentCode(998001L);
//	    resource998001001.setName("资源配置");
//	    resource998001001.setType("page");
//	    resource998001001.setUrl("AM.controller.application.security.ResourceController");
//	    resource998001001.setOrderIndex(1);
//	    checkResource(resource998001001);

	    Resource resource998001002 = new Resource();
	    resource998001002.setId(998001002L);
		resource998001002.setAppId(appId);
		resource998001002.setCode(998001002L);
	    resource998001002.setParentCode(998001L);
	    resource998001002.setName("角色管理");
	    resource998001002.setType("page");
	    resource998001002.setUrl("AM.controller.application.security.RoleController");
	    resource998001002.setOrderIndex(1);
	    checkResource(resource998001002);

	    Resource resource998001003 = new Resource();
	    resource998001003.setId(998001003L);
		resource998001003.setAppId(appId);
		resource998001003.setCode(998001003L);
	    resource998001003.setParentCode(998001L);
	    resource998001003.setName("账号管理");
	    resource998001003.setType("page");
	    resource998001003.setUrl("AM.controller.application.security.AccountController");
	    resource998001003.setOrderIndex(1);
	    checkResource(resource998001003);

	    Resource resource998002 = new Resource();
	    resource998002.setId(998002L);
		resource998002.setAppId(appId);
		resource998002.setCode(998002L);
	    resource998002.setParentCode(998L);
	    resource998002.setName("参数配置");
	    resource998002.setType("menu");
	    resource998002.setOrderIndex(1);
	    checkResource(resource998002);

	    Resource resource998002001 = new Resource();
	    resource998002001.setId(998002001L);
		resource998002001.setAppId(appId);
		resource998002001.setCode(998002001L);
	    resource998002001.setParentCode(998002L);
	    resource998002001.setName("业务参数配置");
	    resource998002001.setType("page");
	    resource998002001.setUrl("AM.controller.application.config.SimpleConfigController");
	    resource998002001.setOrderIndex(1);
	    checkResource(resource998002001);


    }

    private void checkResource(Resource resource){
	    LOGGER.info("[check resource]:{}", resource);
    	Resource resource1 = resourceService.find(resource.getId());
    	if(resource1 == null){
    		resourceService.merge(resource);
	    }
    }

}