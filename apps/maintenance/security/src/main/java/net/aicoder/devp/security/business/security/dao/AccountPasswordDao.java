package net.aicoder.devp.security.business.security.dao;

import com.yunkang.saas.common.jpa.BaseDao;

import net.aicoder.devp.security.business.security.domain.AccountPassword;
import org.springframework.stereotype.Repository;



/**
 * 账号密码的数据库操作
 * @author icode
 */
@Repository("accountPasswordDao")
public interface AccountPasswordDao extends BaseDao<AccountPassword, Long>{


}
