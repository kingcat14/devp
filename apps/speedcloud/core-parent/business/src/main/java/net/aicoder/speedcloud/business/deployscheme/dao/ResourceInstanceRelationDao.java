package net.aicoder.speedcloud.business.deployscheme.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceInstanceRelation;
import org.springframework.stereotype.Repository;


/**
 * 方案资源关联实例的数据库操作
 * @author icode
 */
@Repository("resourceInstanceRelationDao")
public interface ResourceInstanceRelationDao extends BaseDao<ResourceInstanceRelation, Long>{

    int deleteByResource(Long resourceId);

}
