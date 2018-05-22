package net.aicoder.devp.maintenanceui.business.security.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.maintenanceui.business.security.domain.Resource;
import org.springframework.stereotype.Repository;



/**
 * 资源的数据库操作
 * @author icode
 */
@Repository("resourceDao")
public interface ResourceDao extends BaseDao<Resource, Long>{


}
