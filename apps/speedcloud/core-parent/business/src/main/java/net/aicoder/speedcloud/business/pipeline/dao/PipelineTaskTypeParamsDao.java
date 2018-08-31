package net.aicoder.speedcloud.business.pipeline.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTaskTypeParams;
import org.springframework.stereotype.Repository;


/**
 * 任务类型参数定义的数据库操作
 * @author icode
 */
@Repository("pipelineTaskTypeParamsDao")
public interface PipelineTaskTypeParamsDao extends BaseDao<PipelineTaskTypeParams, Long>{


}
