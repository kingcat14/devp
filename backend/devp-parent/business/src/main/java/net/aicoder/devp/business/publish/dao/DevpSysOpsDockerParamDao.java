package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsDockerParam;
import org.springframework.stereotype.Repository;



/**
 * 部署容器参数定义的数据库操作
 * @author icode
 */
@Repository("devpSysOpsDockerParamDao")
public interface DevpSysOpsDockerParamDao extends BaseDao<DevpSysOpsDockerParam, Long>{


}
