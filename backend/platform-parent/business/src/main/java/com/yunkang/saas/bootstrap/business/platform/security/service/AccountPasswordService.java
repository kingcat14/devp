package com.yunkang.saas.bootstrap.business.platform.security.service;


import com.yunkang.saas.common.jpa.CrudService;
import com.yunkang.saas.bootstrap.business.platform.security.dao.AccountPasswordDao;
import com.yunkang.saas.bootstrap.business.platform.security.dao.AccountPasswordSpecification;
import com.yunkang.saas.bootstrap.business.platform.security.domain.AccountPassword;
import com.yunkang.saas.bootstrap.business.platform.security.dto.AccountPasswordCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("accountPasswordService")
public class AccountPasswordService  extends CrudService<AccountPassword, AccountPasswordCondition, AccountPasswordDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountPassword.class);

	public AccountPassword findForAccountName(String accountName){
		AccountPasswordCondition accountPasswordCondition = new AccountPasswordCondition();
		accountPasswordCondition.setAccountName(accountName);
		return this.findOne(accountPasswordCondition);
	}

	public AccountPassword findForAccountId(Long accountId){
		AccountPasswordCondition accountPasswordCondition = new AccountPasswordCondition();
		accountPasswordCondition.setAccountId(accountId);
		return this.findOne(accountPasswordCondition);
	}

	@Override
	public Specification<AccountPassword> getSpecification(AccountPasswordCondition condition) {
		return new AccountPasswordSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , AccountPassword.PROPERTY_ACCOUNT_ID);
		return sort;
	}
}