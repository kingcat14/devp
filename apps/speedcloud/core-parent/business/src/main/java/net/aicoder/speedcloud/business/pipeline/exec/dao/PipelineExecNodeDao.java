package net.aicoder.speedcloud.business.pipeline.exec.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 运行实例节点的数据库操作
 * @author icode
 */
@Repository("pipelineExecNodeDao")
public interface PipelineExecNodeDao extends BaseDao<PipelineExecNode, Long>{


    List<PipelineExecNode> findByTidAndStatus(Long tid, String status);

    List<PipelineExecNode> findByTidAndStatusAndNodeType(Long tid, String status, String nodeType);

    List<PipelineExecNode> findByParentIdOrderByExecIndex(Long parentId);

    List<PipelineExecNode> findByParentIdAndStatusOrderByExecIndex(Long parentId, String status, Pageable pageable);



}
