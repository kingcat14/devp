package net.aicoder.speedcloud.business.pipeline.template.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTaskAction;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 操作模板的数据库操作
 * @author icode
 */
@Repository("pipelineTemplateTaskActionDao")
public interface PipelineTemplateTaskActionDao extends BaseDao<PipelineTemplateTaskAction, String>{

    List<PipelineTemplateTaskAction> findByTask(String taskId);

    int deleteByTask(String taskId);
}
