package net.aicoder.speedcloud.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourceRef;
import org.springframework.stereotype.Repository;


/**
 * 方案资源间关系的数据库操作
 * @author icode
 */
@Repository("devpSysDpyResourceRefDao")
public interface DevpSysDpyResourceRefDao extends BaseDao<DevpSysDpyResourceRef, Long>{


}
