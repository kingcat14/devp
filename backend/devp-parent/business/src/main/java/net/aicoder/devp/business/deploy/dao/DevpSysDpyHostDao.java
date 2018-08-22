package net.aicoder.devp.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyHost;
import org.springframework.stereotype.Repository;



/**
 * 部署主机节点的数据库操作
 * @author icode
 */
@Repository("devpSysDpyHostDao")
public interface DevpSysDpyHostDao extends BaseDao<DevpSysDpyHost, Long>{


}
