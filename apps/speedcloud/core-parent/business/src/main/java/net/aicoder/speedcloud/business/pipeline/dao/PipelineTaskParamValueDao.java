package net.aicoder.speedcloud.business.pipeline.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTaskParamValue;
import org.springframework.stereotype.Repository;


/**
 * 任务参数值的数据库操作
 * @author icode
 */
@Repository("pipelineTaskParamValueDao")
public interface PipelineTaskParamValueDao extends BaseDao<PipelineTaskParamValue, Long>{


}
