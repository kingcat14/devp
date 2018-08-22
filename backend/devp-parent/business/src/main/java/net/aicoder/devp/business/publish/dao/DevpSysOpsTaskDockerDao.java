package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskDocker;
import org.springframework.stereotype.Repository;



/**
 * 部署容器的数据库操作
 * @author icode
 */
@Repository("devpSysOpsTaskDockerDao")
public interface DevpSysOpsTaskDockerDao extends BaseDao<DevpSysOpsTaskDocker, Long>{


}
