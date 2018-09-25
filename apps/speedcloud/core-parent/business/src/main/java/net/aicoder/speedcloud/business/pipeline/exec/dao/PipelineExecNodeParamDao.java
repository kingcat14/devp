package net.aicoder.speedcloud.business.pipeline.exec.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNodeParam;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 运行实例节点参数的数据库操作
 * @author icode
 */
@Repository("pipelineExecNodeParamDao")
public interface PipelineExecNodeParamDao extends BaseDao<PipelineExecNodeParam, Long>{

    List<PipelineExecNodeParam> findByNode(long nodeId);
}
