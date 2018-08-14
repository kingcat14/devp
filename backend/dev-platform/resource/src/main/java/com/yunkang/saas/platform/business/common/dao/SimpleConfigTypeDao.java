package com.yunkang.saas.platform.business.common.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.business.common.domain.SimpleConfigType;
import org.springframework.stereotype.Repository;



/**
 * 通用配置类型的数据库操作
 * @author icode
 */
@Repository("simpleConfigTypeDao")
public interface SimpleConfigTypeDao extends BaseDao<SimpleConfigType, Long>{


}
