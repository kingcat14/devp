package net.aicoder.speedcloud.business.pipeline.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineParam;
import org.springframework.stereotype.Repository;


/**
 * 流水线参数的数据库操作
 * @author icode
 */
@Repository("pipelineParamDao")
public interface PipelineParamDao extends BaseDao<PipelineParam, Long>{

    int deleteByPipeline(Long pipelineId);
}
