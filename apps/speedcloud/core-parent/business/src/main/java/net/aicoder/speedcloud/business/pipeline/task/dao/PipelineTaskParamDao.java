package net.aicoder.speedcloud.business.pipeline.task.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskParam;
import org.springframework.stereotype.Repository;


/**
 * 任务参数的数据库操作
 * @author icode
 */
@Repository("pipelineTaskParamDao")
public interface PipelineTaskParamDao extends BaseDao<PipelineTaskParam, Long>{


}
