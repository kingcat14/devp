package net.aicoder.devp.product.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.product.business.sys.domain.DevpSysDgmElement;
import org.springframework.stereotype.Repository;



/**
 * UML图包含元素的数据库操作
 * @author icode
 */
@Repository("devpSysDgmElementDao")
public interface DevpSysDgmElementDao extends BaseDao<DevpSysDgmElement, Long>{


}
