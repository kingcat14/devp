package net.aicoder.devp.security.business.security.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.security.business.security.dao.AccountRoleRelationDao;
import net.aicoder.devp.security.business.security.dao.AccountRoleRelationSpecification;
import net.aicoder.devp.security.business.security.domain.AccountRoleRelation;
import net.aicoder.devp.security.business.security.dto.AccountRoleRelationCondition;
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