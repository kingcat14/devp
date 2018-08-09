package com.yunkang.saas.platform.business.platform.security.service;


import com.yunkang.saas.common.jpa.CrudService;
import com.yunkang.saas.platform.business.platform.security.dao.AccountRoleRelationDao;
import com.yunkang.saas.platform.business.platform.security.dao.AccountRoleRelationSpecification;
import com.yunkang.saas.platform.business.platform.security.domain.AccountRoleRelation;
import com.yunkang.saas.platform.business.platform.security.dto.AccountRoleRelationCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("accountRoleRelationService")
public class AccountRoleRelationService  extends CrudService<AccountRoleRelation, AccountRoleRelationCondition, AccountRoleRelationDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountRoleRelation.class);

	@Override
	public Specification<AccountRoleRelation> getSpecification(AccountRoleRelationCondition condition) {
		return new AccountRoleRelationSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , AccountRoleRelation.PROPERTY_ACCOUNT_ID);
		return sort;
	}
}