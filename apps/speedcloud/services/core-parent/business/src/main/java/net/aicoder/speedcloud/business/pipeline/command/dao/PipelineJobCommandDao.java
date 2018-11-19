package net.aicoder.speedcloud.business.pipeline.command.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.command.domain.PipelineJobCommand;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 创建Job指令的数据库操作
 * @author icode
 */
@Repository("pipelineJobCommandDao")
public interface PipelineJobCommandDao extends BaseDao<PipelineJobCommand, Long>{

    List<PipelineJobCommand> findByTidAndStatus(Long tid, String status);

}
