package net.aicoder.speedcloud.business.pipeline.task.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionTypeProperty;
import org.springframework.stereotype.Repository;


/**
 * 操作类型属性定义的数据库操作
 * @author icode
 */
@Repository("pipelineTaskActionTypePropertyDao")
public interface PipelineTaskActionTypePropertyDao extends BaseDao<PipelineTaskActionTypeProperty, Long>{


}
