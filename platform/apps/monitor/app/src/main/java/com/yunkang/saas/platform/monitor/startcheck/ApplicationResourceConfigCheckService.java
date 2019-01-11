package com.yunkang.saas.platform.monitor.startcheck;

import com.yunkang.saas.bootstrap.application.business.resource.domain.Resource;
import com.yunkang.saas.bootstrap.application.business.resource.service.ResourceConstants;
import com.yunkang.saas.bootstrap.application.business.resource.service.ResourceService;
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
@Component("SaaSMonitorApplicationResource")
@Order(value=1)
public class ApplicationResourceConfigCheckService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationResourceConfigCheckService.class);

	public static final Long CODE_MONITOR_ID = 900L;
	
	@Autowired
    private ResourceService resourceService;

	@Value("${application.code:-1}")
	private String appCode;

    @Override
    public void run(String... args) throws Exception {

	    Resource resource900 = new Resource();
		resource900.setAppCode(appCode);
		resource900.setCode(CODE_MONITOR_ID);
	    resource900.setParentCode(Resource.TOP_NODE_ID);
	    resource900.setName("监控管理");
	    resource900.setType("module");
		resource900.setHidden(false);
	    resource900.setOrderIndex(900);
	    checkResource(resource900);

	    Resource resource900001 = new Resource();

		resource900001.setAppCode(appCode);
		resource900001.setCode(ResourceConstants.CODE_YYGL_AQPZ);
	    resource900001.setParentCode(CODE_MONITOR_ID);
	    resource900001.setName("应用列表");
	    resource900001.setUrl("AM.view.monitor.app.ApplicationPanel");
	    resource900001.setType("function");
		resource900001.setHidden(false);
	    resource900001.setOrderIndex(1);
	    checkResource(resource900001);

		Resource resource900002 = new Resource();

		resource900002.setAppCode(appCode);
		resource900002.setCode(ResourceConstants.CODE_YYGL_AQPZ);
		resource900002.setParentCode(CODE_MONITOR_ID);
		resource900002.setName("人员列表");
		resource900002.setUrl("AM.view.monitor.supporter.SupporterPanel");
		resource900002.setType("function");
		resource900002.setHidden(false);
		resource900002.setOrderIndex(2);
		checkResource(resource900002);

		Resource resource900003 = new Resource();

		resource900003.setAppCode(appCode);
		resource900003.setCode(ResourceConstants.CODE_YYGL_AQPZ);
		resource900003.setParentCode(CODE_MONITOR_ID);
		resource900003.setName("人员支持应用列表");
		resource900003.setUrl("AM.view.monitor.supporter.SupporterAppRelationPanel");
		resource900003.setType("function");
		resource900003.setHidden(false);
		resource900003.setOrderIndex(3);
		checkResource(resource900003);


    }

    private void checkResource(Resource resource){
	    LOGGER.info("[check resource]:{}", resource);
    	Resource resource1 = resourceService.findByCodeAndAppCode(resource);
    	if(resource1 == null){
    		resourceService.add(resource);
	    }
    }

}