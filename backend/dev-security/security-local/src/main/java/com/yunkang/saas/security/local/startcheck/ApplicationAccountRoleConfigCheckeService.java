package com.yunkang.saas.security.local.startcheck;

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

/**
 * 检查角色的初始化配置
 */
@Component
@Order(value=1)
public class ApplicationAccountRoleConfigCheckeService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationAccountRoleConfigCheckeService.class);

	@Value("${sys.id:-1}")
	private long sysId;

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

    	Role role = new Role();
    	role.setId(sysId);
    	role.setName("租户管理员");
		role.setTenantId(sysId);
		role.setAppId(sysId);
		role.setDescription("租户管理员");
		checkRole(role);

		Account account = new Account();
		account.setId(sysId);
		account.setTenantId(sysId);
		account.setAccountName(sysId+"");
		account.setName("租户管理员");
		account.setEnable("true");
		account.setAppId(sysId);
		check(account);

		AccountPassword accountPassword = new AccountPassword();
		accountPassword.setId(sysId);
		accountPassword.setAccountName(sysId+"");
		accountPassword.setTenantId(sysId);
		accountPassword.setAccountId(sysId);
		//默认123456
		accountPassword.setPassword(passwordEncoder.encode("123456"));
		check(accountPassword);


		AccountRoleRelation accountRoleRelation = new AccountRoleRelation();
		accountRoleRelation.setAccountId(sysId);
		accountRoleRelation.setRoleId(sysId);
		accountRoleRelation.setId(sysId);
		check(accountRoleRelation);

    }

    private void checkRole(Role target){
	    LOGGER.info("[check role]:{}", target);
		Role entity = roleService.find(target.getId());
    	if(entity == null){
			roleService.merge(target);
	    }
    }

	private void check(Account target){
		LOGGER.info("[check]:{}", target);
		Account entity = accountService.find(target.getId());
		if(entity == null){
			accountService.merge(target);
		}
	}
	private void check(AccountPassword target){
		LOGGER.info("[check]:{}", target);
		AccountPassword entity = accountPasswordService.find(target.getId());
		if(entity == null){
			accountPasswordService.merge(target);
		}
	}
	private void check(AccountRoleRelation target){
		LOGGER.info("[check]:{}", target);
		AccountRoleRelation entity = accountRoleRelationService.find(target.getId());
		if(entity == null){
			accountRoleRelationService.merge(target);
		}
	}
}