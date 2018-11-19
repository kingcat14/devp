package net.aicoder.speedcloud.business.pipeline.task.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskAction;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 操作的数据库操作
 * @author icode
 */
@Repository("pipelineTaskActionDao")
public interface PipelineTaskActionDao extends BaseDao<PipelineTaskAction, Long>{

    List<PipelineTaskAction> findByTask(Long taskId);

    int deleteByTask(Long taskId);
}
