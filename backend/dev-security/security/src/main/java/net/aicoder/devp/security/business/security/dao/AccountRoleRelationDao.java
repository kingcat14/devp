package net.aicoder.devp.security.business.security.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.security.business.security.domain.AccountRoleRelation;
import org.springframework.stereotype.Repository;


/**
 * 账号角色关联的数据库操作
 * @author icode
 */
@Repository("accountRoleRelationDao")
public interface AccountRoleRelationDao extends BaseDao<AccountRoleRelation, Long>{


}
