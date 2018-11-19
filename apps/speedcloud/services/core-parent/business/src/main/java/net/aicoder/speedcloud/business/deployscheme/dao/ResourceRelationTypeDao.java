package net.aicoder.speedcloud.business.deployscheme.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceRelationType;
import org.springframework.stereotype.Repository;


/**
 * 资源关系类型的数据库操作
 * @author icode
 */
@Repository("resourceRelationTypeDao")
public interface ResourceRelationTypeDao extends BaseDao<ResourceRelationType, Long>{


}
