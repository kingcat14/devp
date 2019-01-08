package net.aicoder.speedcloud.business.config.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.config.domain.PipelineTaskType;
import org.springframework.stereotype.Repository;


/**
 * 任务类型的数据库操作
 * @author icode
 */
@Repository("pipelineTaskTypeDao")
public interface PipelineTaskTypeDao extends BaseDao<PipelineTaskType, String>{


}
