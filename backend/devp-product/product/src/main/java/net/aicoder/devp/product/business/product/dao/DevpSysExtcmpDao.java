package net.aicoder.devp.product.business.product.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.product.business.product.domain.DevpSysExtcmp;
import org.springframework.stereotype.Repository;



/**
 * 产品包含的外部组件的数据库操作
 * @author icode
 */
@Repository("devpSysExtcmpDao")
public interface DevpSysExtcmpDao extends BaseDao<DevpSysExtcmp, Long>{


}
