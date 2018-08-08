package com.yunkang.saas.security.service.business.platform.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.security.service.business.platform.domain.RoleResourceRelation;
import org.springframework.stereotype.Repository;


/**
 * 角色资源关系的数据库操作
 * @author icode
 */
@Repository("roleResourceRelationDao")
public interface RoleResourceRelationDao extends BaseDao<RoleResourceRelation, Long>{


}
