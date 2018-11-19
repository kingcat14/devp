package net.aicoder.speedcloud.business.env.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.env.domain.AppEnvConfig;
import org.springframework.stereotype.Repository;


/**
 * 应用环境的数据库操作
 * @author icode
 */
@Repository("appEnvConfigDao")
public interface AppEnvConfigDao extends BaseDao<AppEnvConfig, Long>{


}
