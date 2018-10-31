package com.yunkang.saas.bootstrap.platform.business.platform.security.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.bootstrap.platform.business.platform.security.domain.Role;
import org.springframework.stereotype.Repository;


/**
 * 角色的数据库操作
 * @author icode
 */
@Repository("roleDao")
public interface RoleDao extends BaseDao<Role, Long>{

    Role findByTenantIdAndAppCodeAndCode(Long tenantId, String appCode, String code);

}
