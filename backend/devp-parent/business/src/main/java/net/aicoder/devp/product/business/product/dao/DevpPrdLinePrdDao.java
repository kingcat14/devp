package net.aicoder.devp.product.business.product.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.product.business.product.domain.DevpPrdLinePrd;
import org.springframework.stereotype.Repository;


/**
 * 产品所属产品线定义的数据库操作
 * @author icode
 */
@Repository("devpPrdLinePrdDao")
public interface DevpPrdLinePrdDao extends BaseDao<DevpPrdLinePrd, Long>{


}
