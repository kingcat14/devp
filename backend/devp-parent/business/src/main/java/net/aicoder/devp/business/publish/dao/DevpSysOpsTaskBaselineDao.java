package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskBaseline;
import org.springframework.stereotype.Repository;



/**
 * 基线设置的数据库操作
 * @author icode
 */
@Repository("devpSysOpsTaskBaselineDao")
public interface DevpSysOpsTaskBaselineDao extends BaseDao<DevpSysOpsTaskBaseline, Long>{


}
