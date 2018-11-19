package net.aicoder.speedcloud.business.pipeline.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 阶段的数据库操作
 * @author icode
 */
@Repository("pipelineStageDao")
public interface PipelineStageDao extends BaseDao<PipelineStage, Long>{
    int deleteByPipeline(Long pipelineId);
    List<PipelineStage> findByPipeline(Long pipelineId);

}
