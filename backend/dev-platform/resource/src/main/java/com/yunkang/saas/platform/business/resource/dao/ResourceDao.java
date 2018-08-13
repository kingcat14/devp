package com.yunkang.saas.platform.business.resource.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.business.resource.domain.Resource;
import org.springframework.stereotype.Repository;

/**
 * 资源的数据库操作
 * @author icode
 */
@Repository("resourceDao")
public interface ResourceDao extends BaseDao<Resource, Long>{

    Resource findByCodeAndAppId(Long code, Long appId);

}
