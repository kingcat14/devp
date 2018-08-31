package net.aicoder.speedcloud.business.pipeline.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTask;
import org.springframework.stereotype.Repository;


/**
 * 任务的数据库操作
 * @author icode
 */
@Repository("pipelineTaskDao")
public interface PipelineTaskDao extends BaseDao<PipelineTask, Long>{


}
