package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskDeploy;
import org.springframework.stereotype.Repository;



/**
 * 组件部署设置的数据库操作
 * @author icode
 */
@Repository("devpSysOpsTaskDeployDao")
public interface DevpSysOpsTaskDeployDao extends BaseDao<DevpSysOpsTaskDeploy, Long>{


}
