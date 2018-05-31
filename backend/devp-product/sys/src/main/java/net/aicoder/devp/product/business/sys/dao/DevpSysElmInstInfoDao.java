package net.aicoder.devp.product.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmInstInfo;
import org.springframework.stereotype.Repository;



/**
 * 系统元素实例扩充信息的数据库操作
 * @author icode
 */
@Repository("devpSysElmInstInfoDao")
public interface DevpSysElmInstInfoDao extends BaseDao<DevpSysElmInstInfo, Long>{


}
