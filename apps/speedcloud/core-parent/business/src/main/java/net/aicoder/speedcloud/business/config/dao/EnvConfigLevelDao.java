package net.aicoder.speedcloud.business.config.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.config.domain.EnvConfigLevel;
import org.springframework.stereotype.Repository;


/**
 * 环境级别的数据库操作
 * @author icode
 */
@Repository("envConfigLevelDao")
public interface EnvConfigLevelDao extends BaseDao<EnvConfigLevel, Long>{


}
