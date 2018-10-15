package com.yunkang.saas.bootstrap.business.platform.security.service;


import com.yunkang.saas.common.jpa.CrudService;
import com.yunkang.saas.bootstrap.business.platform.security.dao.AccountDao;
import com.yunkang.saas.bootstrap.business.platform.security.dao.AccountSpecification;
import com.yunkang.saas.bootstrap.business.platform.security.domain.Account;
import com.yunkang.saas.bootstrap.business.platform.security.dto.AccountCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("accountService")
public class AccountService  extends CrudService<Account, AccountCondition, AccountDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Account.class);


	public Account findByAccountName(String accountName){
		return dao.findByAccountName(accountName);
	}

	@Override
	public Specification<Account> getSpecification(AccountCondition condition) {
		return new AccountSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , Account.PROPERTY_NICK_NAME);
		return sort;
	}
}