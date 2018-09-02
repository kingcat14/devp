package net.aicoder.speedcloud.business.pipeline.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageTaskRelation;
import org.springframework.stereotype.Repository;


/**
 * 阶段任务关联的数据库操作
 * @author icode
 */
@Repository("pipelineStageTaskRelationDao")
public interface PipelineStageTaskRelationDao extends BaseDao<PipelineStageTaskRelation, Long>{


}
