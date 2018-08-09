package com.yunkang.saas.platform.business.platform.security.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.business.platform.security.domain.AccountRoleRelation;

import org.springframework.stereotype.Repository;


/**
 * 账号角色关联的数据库操作
 * @author icode
 */
@Repository("accountRoleRelationDao")
public interface AccountRoleRelationDao extends BaseDao<AccountRoleRelation, Long>{


}
