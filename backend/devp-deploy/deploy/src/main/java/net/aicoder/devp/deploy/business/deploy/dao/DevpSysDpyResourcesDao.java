package net.aicoder.devp.deploy.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyResources;
import org.springframework.stereotype.Repository;



/**
 * 部署关联资源定义的数据库操作
 * @author icode
 */
@Repository("devpSysDpyResourcesDao")
public interface DevpSysDpyResourcesDao extends BaseDao<DevpSysDpyResources, Long>{


}
