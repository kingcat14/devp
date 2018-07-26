package net.aicoder.devp.deploy.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyCmpRef;
import org.springframework.stereotype.Repository;



/**
 * 系统元素间关系定义的数据库操作
 * @author icode
 */
@Repository("devpSysDpyCmpRefDao")
public interface DevpSysDpyCmpRefDao extends BaseDao<DevpSysDpyCmpRef, Long>{


}
