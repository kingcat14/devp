package com.yunkang.saas.security.local.business.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.security.local.business.domain.Account;
import org.springframework.stereotype.Repository;


/**
 * 账号的数据库操作
 * @author icode
 */
@Repository("accountDao")
public interface AccountDao extends BaseDao<Account, Long>{


}
