package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsDockerPath;
import org.springframework.stereotype.Repository;



/**
 * 存储路径定义的数据库操作
 * @author icode
 */
@Repository("devpSysOpsDockerPathDao")
public interface DevpSysOpsDockerPathDao extends BaseDao<DevpSysOpsDockerPath, Long>{


}
