package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsDockerAccess;
import org.springframework.stereotype.Repository;



/**
 * 部署容器访问参数的数据库操作
 * @author icode
 */
@Repository("devpSysOpsDockerAccessDao")
public interface DevpSysOpsDockerAccessDao extends BaseDao<DevpSysOpsDockerAccess, Long>{


}
