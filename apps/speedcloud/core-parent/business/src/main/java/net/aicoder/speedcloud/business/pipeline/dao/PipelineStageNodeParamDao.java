package net.aicoder.speedcloud.business.pipeline.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNodeParam;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 阶段执行节点参数的数据库操作
 * @author icode
 */
@Repository("pipelineStageNodeParamDao")
public interface PipelineStageNodeParamDao extends BaseDao<PipelineStageNodeParam, Long>{

    int deleteByPipelineStageNode(Long nodeId);
    List<PipelineStageNodeParam> findByPipelineStageNode(Long nodeId);
}
