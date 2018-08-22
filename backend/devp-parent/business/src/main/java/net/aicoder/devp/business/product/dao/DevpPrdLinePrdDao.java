package net.aicoder.devp.business.product.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.product.domain.DevpPrdLinePrd;
import org.springframework.stereotype.Repository;



/**
 * 产品所属产品线的数据库操作
 * @author icode
 */
@Repository("devpPrdLinePrdDao")
public interface DevpPrdLinePrdDao extends BaseDao<DevpPrdLinePrd, Long>{


}
