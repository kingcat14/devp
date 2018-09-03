package net.aicoder.speedcloud.business.pipeline.exec.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNodeParams;
import org.springframework.stereotype.Repository;


/**
 * 运行实例节点参数的数据库操作
 * @author icode
 */
@Repository("pipelineExecInstanceNodeParamsDao")
public interface PipelineExecInstanceNodeParamsDao extends BaseDao<PipelineExecInstanceNodeParams, Long>{


}
