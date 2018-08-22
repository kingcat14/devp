package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskConfig;
import org.springframework.stereotype.Repository;



/**
 * 配置文件设置的数据库操作
 * @author icode
 */
@Repository("devpSysOpsTaskConfigDao")
public interface DevpSysOpsTaskConfigDao extends BaseDao<DevpSysOpsTaskConfig, Long>{


}
