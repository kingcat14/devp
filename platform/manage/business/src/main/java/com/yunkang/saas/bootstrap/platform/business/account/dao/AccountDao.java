package com.yunkang.saas.bootstrap.platform.business.account.dao;

import com.yunkang.saas.bootstrap.platform.business.account.domain.Account;
import com.yunkang.saas.common.jpa.BaseDao;
import org.springframework.stereotype.Repository;


/**
 * 账号的数据库操作
 * @author icode
 */
@Repository("accountDao")
public interface AccountDao extends BaseDao<Account, Long>{

    Account findByAccountName(String accountName);

}
