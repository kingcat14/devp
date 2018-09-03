package net.aicoder.speedcloud.business.pipeline.exec.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNodeParam;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 运行实例节点参数的数据库操作
 * @author icode
 */
@Repository("pipelineExecInstanceNodeParamDao")
public interface PipelineExecInstanceNodeParamDao extends BaseDao<PipelineExecInstanceNodeParam, Long>{

    List<PipelineExecInstanceNodeParam> findByNode(long nodeId);
}
