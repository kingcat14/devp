package com.yunkang.saas.platform.authentication.business.platform.tenant.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.authentication.business.platform.tenant.domain.AppTenantRelation;
import org.springframework.stereotype.Repository;


/**
 * 租户开通的应用的数据库操作
 * @author icode
 */
@Repository("appTenantRelationDao")
public interface AppTenantRelationDao extends BaseDao<AppTenantRelation, Long>{


}
