package net.aicoder.devp.deploy.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyResInstHost;
import org.springframework.stereotype.Repository;



/**
 * 资源实例部署主机节点的数据库操作
 * @author icode
 */
@Repository("devpSysDpyResInstHostDao")
public interface DevpSysDpyResInstHostDao extends BaseDao<DevpSysDpyResInstHost, Long>{


}
