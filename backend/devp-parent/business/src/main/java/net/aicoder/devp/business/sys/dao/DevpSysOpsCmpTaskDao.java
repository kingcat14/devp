package net.aicoder.devp.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.sys.domain.DevpSysOpsCmpTask;
import org.springframework.stereotype.Repository;



/**
 * 组件任务的数据库操作
 * @author icode
 */
@Repository("devpSysOpsCmpTaskDao")
public interface DevpSysOpsCmpTaskDao extends BaseDao<DevpSysOpsCmpTask, Long>{


}
