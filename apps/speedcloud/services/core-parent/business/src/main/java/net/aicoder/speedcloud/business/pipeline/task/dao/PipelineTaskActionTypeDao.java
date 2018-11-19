package net.aicoder.speedcloud.business.pipeline.task.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionType;
import org.springframework.stereotype.Repository;


/**
 * 操作类型的数据库操作
 * @author icode
 */
@Repository("pipelineTaskActionTypeDao")
public interface PipelineTaskActionTypeDao extends BaseDao<PipelineTaskActionType, Long>{


}
