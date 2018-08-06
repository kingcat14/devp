package com.yunkang.saas.security.local.business.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.security.local.business.domain.Role;
import org.springframework.stereotype.Repository;


/**
 * 角色的数据库操作
 * @author icode
 */
@Repository("roleDao")
public interface RoleDao extends BaseDao<Role, Long>{


}
