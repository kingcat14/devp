package com.kingzoo.kingcat.project.icode4.startcheck;


import com.kingzoo.kingcat.project.icode4.business.security.dao.ResourceDao;
import com.kingzoo.kingcat.project.icode4.business.security.service.ResourceService;
import com.kingzoo.kingcat.project.icode4.business.security.dto.ResourceAddDto;
import com.kingzoo.kingcat.project.icode4.business.security.entity.Resource;
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
public class ResourceConfigCheckService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceConfigCheckService.class);


	@Autowired
    private ResourceService resourceService;

	@Autowired
	private ResourceDao resourceDao;


    @Override
    public void run(String... args) throws Exception {

	    ResourceAddDto resource999 = new ResourceAddDto();
	    resource999.setId(""+999L);
	    resource999.setParentId(""+-1L);
	    resource999.setName("系统管理");
	    resource999.setType("module");
	    resource999.setOrderIndex(""+999);
	    checkResource(resource999);

	    ResourceAddDto resource999001 = new ResourceAddDto();
	    resource999001.setId(""+999001L);
	    resource999001.setParentId(""+999L);
	    resource999001.setName("资源管理");
	    resource999001.setType("function");
	    resource999001.setUrl("AM.controller.security.ResourceController");
	    resource999001.setOrderIndex(""+1);
	    checkResource(resource999001);


    }

    private void checkResource(ResourceAddDto resource){
	    LOGGER.info("[check resource]:{}", resource);
    	Resource resource1 = resourceService.find(resource.getId());
    	if(resource1 == null){
		    resourceService.add(resource);
	    }
    }

}