package net.aicoder.devp.maintenance.business.config.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.maintenance.business.config.domain.SimpleConfig;
import org.springframework.stereotype.Repository;



/**
 * 通用配置的数据库操作
 * @author icode
 */
@Repository("simpleConfigDao")
public interface SimpleConfigDao extends BaseDao<SimpleConfig, Long> {


}
