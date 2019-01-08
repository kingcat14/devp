package net.aicoder.speedcloud.business.pipeline.template.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTaskParam;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 任务模板参数的数据库操作
 * @author icode
 */
@Repository("pipelineTemplateTaskParamDao")
public interface PipelineTemplateTaskParamDao extends BaseDao<PipelineTemplateTaskParam, String>{
    List<PipelineTemplateTaskParam> findByTask(String task);
    int deleteByTask(String taskId);

}
