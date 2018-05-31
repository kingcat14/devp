package net.aicoder.devp.product.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.product.business.sys.domain.DevpSysDiagram;
import org.springframework.stereotype.Repository;



/**
 * UML图的数据库操作
 * @author icode
 */
@Repository("devpSysDiagramDao")
public interface DevpSysDiagramDao extends BaseDao<DevpSysDiagram, Long>{


}
