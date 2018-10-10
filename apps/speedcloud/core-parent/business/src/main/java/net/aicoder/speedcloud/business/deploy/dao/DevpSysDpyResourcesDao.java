package net.aicoder.speedcloud.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResources;
import org.springframework.stereotype.Repository;


/**
 * 方案资源的数据库操作
 * @author icode
 */
@Repository("devpSysDpyResourcesDao")
public interface DevpSysDpyResourcesDao extends BaseDao<DevpSysDpyResources, Long>{


}
