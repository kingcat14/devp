package net.aicoder.devp.maintenanceui.business.security.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.maintenanceui.business.security.dao.AccountDao;
import net.aicoder.devp.maintenanceui.business.security.dao.AccountSpecification;
import net.aicoder.devp.maintenanceui.business.security.domain.Account;
import net.aicoder.devp.maintenanceui.business.security.dto.AccountCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("accountService")
public class AccountService  extends CrudService<Account, AccountCondition, AccountDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Account.class);

	@Override
	public Specification<Account> getSpecification(AccountCondition condition) {
		return new AccountSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , Account.PROPERTY_NICK_NAME);
		return sort;
	}
}