package com.yunkang.saas.bootstrap.platform.business.tenant.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.bootstrap.platform.business.tenant.domain.Tenant;
import org.springframework.stereotype.Repository;


/**
 * 租户的数据库操作
 * @author icode
 */
@Repository("tenantDao")
public interface TenantDao extends BaseDao<Tenant, Long>{


}
