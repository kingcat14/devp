package net.aicoder.devp.product.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmInstance;
import org.springframework.stereotype.Repository;



/**
 * 系统元素实例的数据库操作
 * @author icode
 */
@Repository("devpSysElmInstanceDao")
public interface DevpSysElmInstanceDao extends BaseDao<DevpSysElmInstance, Long>{


}
