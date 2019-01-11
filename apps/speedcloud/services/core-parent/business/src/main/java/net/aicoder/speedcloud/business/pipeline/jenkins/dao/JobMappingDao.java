package net.aicoder.speedcloud.business.pipeline.jenkins.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.jenkins.domain.JobMapping;
import org.springframework.stereotype.Repository;


/**
 * 任务映射的数据库操作
 * @author icode
 */
@Repository("jobMappingDao")
public interface JobMappingDao extends BaseDao<JobMapping, String>{


}
