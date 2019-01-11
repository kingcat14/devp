package com.yunkang.saas.platform.authentication.business.platform.security.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.authentication.business.platform.security.domain.Account;
import org.springframework.stereotype.Repository;


/**
 * 账号的数据库操作
 * @author icode
 */
@Repository("accountDao")
public interface AccountDao extends BaseDao<Account, Long>{


}
