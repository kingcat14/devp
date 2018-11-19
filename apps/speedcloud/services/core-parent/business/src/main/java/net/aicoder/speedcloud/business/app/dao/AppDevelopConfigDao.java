package net.aicoder.speedcloud.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.app.domain.AppDevelopConfig;
import org.springframework.stereotype.Repository;


/**
 * 应用开发配置的数据库操作
 * @author icode
 */
@Repository("appDevelopConfigDao")
public interface AppDevelopConfigDao extends BaseDao<AppDevelopConfig, Long>{


}
