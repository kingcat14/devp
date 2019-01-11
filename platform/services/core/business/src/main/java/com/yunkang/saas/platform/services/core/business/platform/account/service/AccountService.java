package com.yunkang.saas.platform.services.core.business.platform.account.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.services.core.business.platform.account.dao.AccountDao;
import com.yunkang.saas.platform.services.core.business.platform.account.dao.AccountSpecification;
import com.yunkang.saas.platform.services.core.business.platform.account.domain.Account;
import com.yunkang.saas.platform.services.core.business.platform.account.dto.AccountCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("accountService")
@Slf4j
public class AccountService  extends GenericCrudService<Account, Long, AccountCondition, AccountDao> {

	@Override
	public Specification<Account> getSpecification(AccountCondition condition) {
		return new AccountSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, Account.PROPERTY_MODIFY_AT);

		return sort;
	}
}