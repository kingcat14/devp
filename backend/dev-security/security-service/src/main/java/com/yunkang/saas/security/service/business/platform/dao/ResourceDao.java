package com.yunkang.saas.security.service.business.platform.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.security.service.business.platform.domain.Resource;
import org.springframework.stereotype.Repository;

/**
 * 资源的数据库操作
 * @author icode
 */
@Repository("resourceDao")
public interface ResourceDao extends BaseDao<Resource, Long>{


}
