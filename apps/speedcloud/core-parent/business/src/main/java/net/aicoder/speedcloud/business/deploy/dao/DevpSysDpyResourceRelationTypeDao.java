package net.aicoder.speedcloud.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourceRelationType;
import org.springframework.stereotype.Repository;


/**
 * 资源关系类型的数据库操作
 * @author icode
 */
@Repository("devpSysDpyResourceRelationTypeDao")
public interface DevpSysDpyResourceRelationTypeDao extends BaseDao<DevpSysDpyResourceRelationType, Long>{


}
