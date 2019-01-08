package net.aicoder.speedcloud.business.pipeline.template.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTask;
import org.springframework.stereotype.Repository;


/**
 * 任务模板的数据库操作
 * @author icode
 */
@Repository("pipelineTemplateTaskDao")
public interface PipelineTemplateTaskDao extends BaseDao<PipelineTemplateTask, String>{


}
