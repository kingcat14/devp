package net.aicoder.devp.product.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmConnector;
import org.springframework.stereotype.Repository;



/**
 * 系统元素间关系的数据库操作
 * @author icode
 */
@Repository("devpSysElmConnectorDao")
public interface DevpSysElmConnectorDao extends BaseDao<DevpSysElmConnector, Long>{


}
