package net.aicoder.speedcloud.business.pipeline.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import org.springframework.stereotype.Repository;


/**
 * 流水线的数据库操作
 * @author icode
 */
@Repository("pipelineDao")
public interface PipelineDao extends BaseDao<Pipeline, Long>{


}
