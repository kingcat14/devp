package net.aicoder.speedcloud.business.pipeline.exec.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 运行计划的数据库操作
 * @author icode
 */
@Repository("pipelineExecInstanceDao")
public interface PipelineExecInstanceDao extends BaseDao<PipelineExecInstance, Long>{


    List<PipelineExecInstance> findByTidAndStatus(Long tid, String status);
}
