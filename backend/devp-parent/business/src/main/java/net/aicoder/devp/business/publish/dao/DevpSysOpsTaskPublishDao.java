package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskPublish;
import org.springframework.stereotype.Repository;



/**
 * 发布设置的数据库操作
 * @author icode
 */
@Repository("devpSysOpsTaskPublishDao")
public interface DevpSysOpsTaskPublishDao extends BaseDao<DevpSysOpsTaskPublish, Long>{


}
