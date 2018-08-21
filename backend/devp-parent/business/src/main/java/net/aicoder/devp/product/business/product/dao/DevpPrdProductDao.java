package net.aicoder.devp.product.business.product.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.product.business.product.domain.DevpPrdProduct;
import org.springframework.stereotype.Repository;


/**
 * 产品定义的数据库操作
 * @author icode
 */
@Repository("devpPrdProductDao")
public interface DevpPrdProductDao extends BaseDao<DevpPrdProduct, Long>{


}
