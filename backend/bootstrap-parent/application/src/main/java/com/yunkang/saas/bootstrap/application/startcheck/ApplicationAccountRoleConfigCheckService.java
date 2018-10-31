package com.yunkang.saas.bootstrap.application.startcheck;

import com.yunkang.saas.bootstrap.platform.business.platform.security.domain.AccountRoleRelation;
import com.yunkang.saas.bootstrap.platform.business.platform.security.service.AccountPasswordService;
import com.yunkang.saas.bootstrap.platform.business.platform.security.service.AccountRoleRelationService;
import com.yunkang.saas.bootstrap.platform.business.platform.security.service.AccountService;
import com.yunkang.saas.bootstrap.platform.business.platform.security.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 检查角色的初始化配置
 */
@Component
@Order(value=1)
public class ApplicationAccountRoleConfigCheckService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationAccountRoleConfigCheckService.class);

	@Autowired
    private AccountService accountService;

	@Autowired
	private AccountPasswordService accountPasswordService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AccountRoleRelationService accountRoleRelationService;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {


		AccountRoleRelation accountRoleRelation = new AccountRoleRelation();
		accountRoleRelation.setAccountId(1L);
		accountRoleRelation.setRoleId(1L);
		accountRoleRelation.setId(1L);
		check(accountRoleRelation);

    }

	private void check(AccountRoleRelation target){
		LOGGER.info("[check]:{}", target);
		AccountRoleRelation entity = accountRoleRelationService.find(target.getId());
		if(entity == null){
			accountRoleRelationService.merge(target);
		}
	}
}