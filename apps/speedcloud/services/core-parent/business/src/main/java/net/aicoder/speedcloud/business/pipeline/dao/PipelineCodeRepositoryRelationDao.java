package net.aicoder.speedcloud.business.pipeline.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineCodeRepositoryRelation;
import org.springframework.stereotype.Repository;


/**
 * 流水线代码库关联的数据库操作
 * @author icode
 */
@Repository("pipelineCodeRepositoryRelationDao")
public interface PipelineCodeRepositoryRelationDao extends BaseDao<PipelineCodeRepositoryRelation, Long>{


}
