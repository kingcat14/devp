package net.aicoder.speedcloud.business.pipeline.task.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionProperty;
import org.springframework.stereotype.Repository;


/**
 * 操作属性的数据库操作
 * @author icode
 */
@Repository("pipelineTaskActionPropertyDao")
public interface PipelineTaskActionPropertyDao extends BaseDao<PipelineTaskActionProperty, Long>{


}
