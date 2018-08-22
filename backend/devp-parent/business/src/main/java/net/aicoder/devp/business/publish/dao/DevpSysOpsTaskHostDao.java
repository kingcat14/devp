package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskHost;
import org.springframework.stereotype.Repository;



/**
 * 部署主机节点的数据库操作
 * @author icode
 */
@Repository("devpSysOpsTaskHostDao")
public interface DevpSysOpsTaskHostDao extends BaseDao<DevpSysOpsTaskHost, Long>{


}
