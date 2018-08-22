package net.aicoder.devp.business.product.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.product.domain.DevpPrdProduct;
import org.springframework.stereotype.Repository;



/**
 * 产品的数据库操作
 * @author icode
 */
@Repository("devpPrdProductDao")
public interface DevpPrdProductDao extends BaseDao<DevpPrdProduct, Long>{


}
