package net.aicoder.speedcloud.business.pipeline.task.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import org.springframework.stereotype.Repository;


/**
 * 任务的数据库操作
 * @author icode
 */
@Repository("pipelineTaskDao")
public interface PipelineTaskDao extends BaseDao<PipelineTask, Long>{


}
