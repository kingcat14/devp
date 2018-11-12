package com.yunkang.saas.bootstrap.application.business.security.dao;

import com.yunkang.saas.bootstrap.application.business.security.domain.AccountRoleRelation;
import com.yunkang.saas.common.jpa.BaseDao;
import org.springframework.stereotype.Repository;


/**
 * 账号角色关联的数据库操作
 * @author icode
 */
@Repository("accountRoleRelationDao")
public interface AccountRoleRelationDao extends BaseDao<AccountRoleRelation, Long>{

    AccountRoleRelation findByRoleIdAndAccountId(Long roleId, Long accountId);

}
