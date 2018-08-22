package net.aicoder.devp.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyResExecenv;
import org.springframework.stereotype.Repository;



/**
 * 资源部署环境节点的数据库操作
 * @author icode
 */
@Repository("devpSysDpyResExecenvDao")
public interface DevpSysDpyResExecenvDao extends BaseDao<DevpSysDpyResExecenv, Long>{


}
