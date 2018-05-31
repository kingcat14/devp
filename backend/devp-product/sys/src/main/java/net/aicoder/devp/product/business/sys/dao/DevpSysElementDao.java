package net.aicoder.devp.product.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.product.business.sys.domain.DevpSysElement;
import org.springframework.stereotype.Repository;



/**
 * 系统元素定义的数据库操作
 * @author icode
 */
@Repository("devpSysElementDao")
public interface DevpSysElementDao extends BaseDao<DevpSysElement, Long>{


}
