package net.aicoder.devp.maintenance.business.rdc.config.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.maintenance.business.rdc.config.domain.EnvConfigLevel;
import org.springframework.stereotype.Repository;


/**
 * 环境级别的数据库操作
 * @author icode
 */
@Repository("envConfigLevelDao")
public interface EnvConfigLevelDao extends BaseDao<EnvConfigLevel, String>{


}
