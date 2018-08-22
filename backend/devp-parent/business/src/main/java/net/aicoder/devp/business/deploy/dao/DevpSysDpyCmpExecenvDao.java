package net.aicoder.devp.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyCmpExecenv;
import org.springframework.stereotype.Repository;



/**
 * 组件部署环境节点的数据库操作
 * @author icode
 */
@Repository("devpSysDpyCmpExecenvDao")
public interface DevpSysDpyCmpExecenvDao extends BaseDao<DevpSysDpyCmpExecenv, Long>{


}
