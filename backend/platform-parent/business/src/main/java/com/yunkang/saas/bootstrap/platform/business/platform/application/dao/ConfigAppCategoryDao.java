package com.yunkang.saas.bootstrap.platform.business.platform.application.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.bootstrap.platform.business.platform.application.domain.ConfigAppCategory;
import org.springframework.stereotype.Repository;


/**
 * 应用类别的数据库操作
 * @author icode
 */
@Repository("configAppCategoryDao")
public interface ConfigAppCategoryDao extends BaseDao<ConfigAppCategory, Long>{


}
