package net.aicoder.speedcloud.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.app.domain.SecurityConfig;
import org.springframework.stereotype.Repository;


/**
 * 应用私密配置的数据库操作
 * @author icode
 */
@Repository("securityConfigDao")
public interface SecurityConfigDao extends BaseDao<SecurityConfig, Long>{


}
