package net.aicoder.speedcloud.business.deployscheme.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceRelation;
import org.springframework.stereotype.Repository;


/**
 * 方案资源间关系的数据库操作
 * @author icode
 */
@Repository("resourceRelationDao")
public interface ResourceRelationDao extends BaseDao<ResourceRelation, Long>{


}
