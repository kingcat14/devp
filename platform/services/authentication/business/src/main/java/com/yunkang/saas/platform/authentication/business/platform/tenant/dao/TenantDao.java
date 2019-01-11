package com.yunkang.saas.platform.authentication.business.platform.tenant.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.authentication.business.platform.tenant.domain.Tenant;
import org.springframework.stereotype.Repository;


/**
 * 租户的数据库操作
 * @author icode
 */
@Repository("tenantDao")
public interface TenantDao extends BaseDao<Tenant, Long>{


}
