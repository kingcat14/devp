package net.aicoder.speedcloud.business.pipeline.exec.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 运行实例节点的数据库操作
 * @author icode
 */
@Repository("pipelineExecInstanceNodeDao")
public interface PipelineExecInstanceNodeDao extends BaseDao<PipelineExecInstanceNode, Long>{


    List<PipelineExecInstanceNode> findByTidAndStatus(Long tid, String status);

}
