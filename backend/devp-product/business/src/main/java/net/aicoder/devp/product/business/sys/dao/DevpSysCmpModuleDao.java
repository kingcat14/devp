package net.aicoder.devp.product.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.product.business.sys.domain.DevpSysCmpModule;
import org.springframework.stereotype.Repository;


/**
 * 组件对应模块的数据库操作
 * @author icode
 */
@Repository("devpSysCmpModuleDao")
public interface DevpSysCmpModuleDao extends BaseDao<DevpSysCmpModule, Long>{


}
