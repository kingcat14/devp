package net.aicoder.devp.maintenance.business.common.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.maintenance.business.common.domain.SimpleConfig;
import org.springframework.stereotype.Repository;


/**
 * 通用配置的数据库操作
 * @author icode
 */
@Repository("simpleConfigDao")
public interface SimpleConfigDao extends BaseDao<SimpleConfig, Long>{


}
