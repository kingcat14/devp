package net.aicoder.speedcloud.business.pipeline.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNode;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 阶段执行节点的数据库操作
 * @author icode
 */
@Repository("pipelineStageNodeDao")
public interface PipelineStageNodeDao extends BaseDao<PipelineStageNode, Long>{


    int deleteByStage(Long stageId);


    List<PipelineStageNode> findByStage(Long stageId);
}
