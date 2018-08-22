package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipePlan;
import org.springframework.stereotype.Repository;



/**
 * 产品运维流水线执行计划的数据库操作
 * @author icode
 */
@Repository("devpSysOpsPipePlanDao")
public interface DevpSysOpsPipePlanDao extends BaseDao<DevpSysOpsPipePlan, Long>{


}
