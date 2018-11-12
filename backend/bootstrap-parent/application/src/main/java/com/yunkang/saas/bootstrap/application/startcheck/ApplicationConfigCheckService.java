package com.yunkang.saas.bootstrap.application.startcheck;

import com.yunkang.saas.bootstrap.platform.business.application.domain.App;
import com.yunkang.saas.bootstrap.platform.business.application.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 应用的基础信息
 */
@Component
@Order(value=1)
public class ApplicationConfigCheckService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfigCheckService.class);

	@Value("${application.code:-1}")
	private Long appCode;

	@Value("${application.displayName:未设置}")
	private String displayName;

	@Autowired
    private AppService appService;

    @Override
    public void run(String... args) throws Exception {

    	if(-1L == appCode){
    		LOGGER.error("需要在配置文件中设置 applicationCode.");
			TimeUnit.SECONDS.sleep(1);
    		System.exit(1);
		}


    	//每个新应用, 都有个应用管理员
    	App app = new App();
		app.setId(appCode);
		app.setCode(appCode+"");
    	app.setName(displayName);
		app.setVisible(false);
		app.setOnBoardTime(new Date());
    	check(app);


    }
    private void check(App target){
	    LOGGER.info("[check]:{}", target);
		App entity = appService.findByCode(target.getCode());
    	if(entity == null){
			appService.merge(target);
	    }
    }

}