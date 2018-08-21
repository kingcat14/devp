package com.yunkang.saas.security.local.startcheck;

import com.yunkang.saas.platform.business.platform.application.domain.App;
import com.yunkang.saas.platform.business.platform.application.service.AppService;
import com.yunkang.saas.platform.business.platform.security.Constants;
import com.yunkang.saas.platform.business.platform.security.domain.Account;
import com.yunkang.saas.platform.business.platform.security.domain.AccountPassword;
import com.yunkang.saas.platform.business.platform.security.domain.AccountRoleRelation;
import com.yunkang.saas.platform.business.platform.security.domain.Role;
import com.yunkang.saas.platform.business.platform.security.service.AccountPasswordService;
import com.yunkang.saas.platform.business.platform.security.service.AccountRoleRelationService;
import com.yunkang.saas.platform.business.platform.security.service.AccountService;
import com.yunkang.saas.platform.business.platform.security.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 应用的基础信息
 */
@Component
@Order(value=1)
public class ApplicationConfigCheckeService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfigCheckeService.class);

	@Value("${application.code:-1}")
	private String appCode;

	@Value("${application.displayName:未设置}")
	private String displayName;
	@Autowired
    private AppService appService;



    @Override
    public void run(String... args) throws Exception {

    	//每个新应用, 都有个应用管理员
    	App app = new App();
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

	private void check(Role target){
		LOGGER.info("[check role]:{}", target);
		Role entity = roleService.find(target.getId());
		if(entity == null){
			roleService.merge(target);
		}
	}

	@Autowired
	private RoleService roleService;
}