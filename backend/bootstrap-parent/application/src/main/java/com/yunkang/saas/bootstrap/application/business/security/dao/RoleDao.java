package com.yunkang.saas.bootstrap.application.business.security.dao;

import com.yunkang.saas.bootstrap.application.business.security.domain.Role;
import com.yunkang.saas.common.jpa.BaseDao;
import org.springframework.stereotype.Repository;


/**
 * 角色的数据库操作
 * @author icode
 */
@Repository("roleDao")
public interface RoleDao extends BaseDao<Role, Long>{

    Role findByTidAndAppCodeAndCode(Long tenantId, String appCode, String code);

}
