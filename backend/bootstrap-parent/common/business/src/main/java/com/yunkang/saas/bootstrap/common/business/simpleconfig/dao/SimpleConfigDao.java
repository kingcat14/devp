package com.yunkang.saas.bootstrap.common.business.simpleconfig.dao;

import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.common.jpa.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 通用配置的数据库操作
 * @author icode
 */
@Repository("simpleConfigDao")
public interface SimpleConfigDao extends BaseDao<SimpleConfig, Long>{


    List<SimpleConfig> findByConfigType(String configType);
    SimpleConfig findByConfigTypeAndCode(String configType, String code);
}
