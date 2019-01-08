package net.aicoder.speedcloud.business.pipeline.exec.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNodeLog;
import org.springframework.stereotype.Repository;


/**
 * 运行实例节点的数据库操作
 * @author icode
 */
@Repository("pipelineExecNodeLogDao")
public interface PipelineExecNodeLogDao extends BaseDao<PipelineExecNodeLog, String>{





}
