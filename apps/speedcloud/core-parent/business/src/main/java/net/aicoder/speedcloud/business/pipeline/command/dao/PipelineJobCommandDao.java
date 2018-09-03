package net.aicoder.speedcloud.business.pipeline.command.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.command.domain.PipelineJobCommand;
import org.springframework.stereotype.Repository;


/**
 * 创建Job指令的数据库操作
 * @author icode
 */
@Repository("pipelineJobCommandDao")
public interface PipelineJobCommandDao extends BaseDao<PipelineJobCommand, Long>{


}
