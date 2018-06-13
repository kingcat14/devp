package net.aicoder.devp.deploy.business.ops.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsElementInfo;
import org.springframework.stereotype.Repository;



/**
 * 系统元素扩充信息的数据库操作
 * @author icode
 */
@Repository("devpOpsElementInfoDao")
public interface DevpOpsElementInfoDao extends BaseDao<DevpOpsElementInfo, Long>{


}
