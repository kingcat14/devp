package com.yunkang.saas.platform.authentication.business.platform.security.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.authentication.business.platform.security.dao.AccountDao;
import com.yunkang.saas.platform.authentication.business.platform.security.dao.AccountSpecification;
import com.yunkang.saas.platform.authentication.business.platform.security.domain.Account;
import com.yunkang.saas.platform.authentication.business.platform.security.dto.AccountCondition;
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
		Sort sort = new Sort(Sort.Direction.DESC);

		return sort;
	}
}