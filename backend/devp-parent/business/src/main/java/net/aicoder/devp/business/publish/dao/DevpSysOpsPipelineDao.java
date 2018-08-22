package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeline;
import org.springframework.stereotype.Repository;



/**
 * 产品运维流水线的数据库操作
 * @author icode
 */
@Repository("devpSysOpsPipelineDao")
public interface DevpSysOpsPipelineDao extends BaseDao<DevpSysOpsPipeline, Long>{


}
