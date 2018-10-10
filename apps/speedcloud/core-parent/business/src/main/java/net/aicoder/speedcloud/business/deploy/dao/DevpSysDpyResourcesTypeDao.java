package net.aicoder.speedcloud.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourcesType;
import org.springframework.stereotype.Repository;


/**
 * 部署资源类型的数据库操作
 * @author icode
 */
@Repository("devpSysDpyResourcesTypeDao")
public interface DevpSysDpyResourcesTypeDao extends BaseDao<DevpSysDpyResourcesType, Long>{


}
