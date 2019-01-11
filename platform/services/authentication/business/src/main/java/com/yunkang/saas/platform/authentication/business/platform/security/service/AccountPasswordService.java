package com.yunkang.saas.platform.authentication.business.platform.security.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.authentication.business.platform.security.dao.AccountPasswordDao;
import com.yunkang.saas.platform.authentication.business.platform.security.dao.AccountPasswordSpecification;
import com.yunkang.saas.platform.authentication.business.platform.security.domain.AccountPassword;
import com.yunkang.saas.platform.authentication.business.platform.security.dto.AccountPasswordCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("accountPasswordService")
@Slf4j
public class AccountPasswordService  extends GenericCrudService<AccountPassword, String, AccountPasswordCondition, AccountPasswordDao> {

	@Override
	public Specification<AccountPassword> getSpecification(AccountPasswordCondition condition) {
		return new AccountPasswordSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC);

		return sort;
	}
}