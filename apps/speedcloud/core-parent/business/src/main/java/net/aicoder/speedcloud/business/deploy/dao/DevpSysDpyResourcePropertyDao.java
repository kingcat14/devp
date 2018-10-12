package net.aicoder.speedcloud.business.deploy.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourceProperty;
import org.springframework.stereotype.Repository;


/**
 * 资源属性的数据库操作
 * @author icode
 */
@Repository("devpSysDpyResourcePropertyDao")
public interface DevpSysDpyResourcePropertyDao extends BaseDao<DevpSysDpyResourceProperty, Long>{


}
