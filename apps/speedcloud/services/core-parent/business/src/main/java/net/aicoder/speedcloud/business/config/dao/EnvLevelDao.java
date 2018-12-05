package net.aicoder.speedcloud.business.config.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.config.domain.EnvLevel;
import org.springframework.stereotype.Repository;


/**
 * 环境级别的数据库操作
 * @author icode
 */
@Repository("envLevelDao")
public interface EnvLevelDao extends BaseDao<EnvLevel, String>{


}
