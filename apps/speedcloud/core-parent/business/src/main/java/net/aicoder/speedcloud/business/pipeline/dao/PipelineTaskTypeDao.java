package net.aicoder.speedcloud.business.pipeline.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTaskType;
import org.springframework.stereotype.Repository;


/**
 * 任务类型的数据库操作
 * @author icode
 */
@Repository("pipelineTaskTypeDao")
public interface PipelineTaskTypeDao extends BaseDao<PipelineTaskType, Long>{


}
