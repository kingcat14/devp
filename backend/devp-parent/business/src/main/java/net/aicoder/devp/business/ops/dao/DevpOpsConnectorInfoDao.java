package net.aicoder.devp.business.ops.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.ops.domain.DevpOpsConnectorInfo;
import org.springframework.stereotype.Repository;



/**
 * 运维元素连接扩充信息的数据库操作
 * @author icode
 */
@Repository("devpOpsConnectorInfoDao")
public interface DevpOpsConnectorInfoDao extends BaseDao<DevpOpsConnectorInfo, Long>{


}
