package com.yunkang.saas.bootstrap.platform.business.tenant.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.bootstrap.platform.business.tenant.domain.TenantType;
import org.springframework.stereotype.Repository;


/**
 * 租户类型的数据库操作
 * @author icode
 */
@Repository("tenantTypeDao")
public interface TenantTypeDao extends BaseDao<TenantType, Long>{


}
