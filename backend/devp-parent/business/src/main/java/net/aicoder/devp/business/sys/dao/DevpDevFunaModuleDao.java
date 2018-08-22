package net.aicoder.devp.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.sys.domain.DevpDevFunaModule;
import org.springframework.stereotype.Repository;



/**
 * 功能模块的数据库操作
 * @author icode
 */
@Repository("devpDevFunaModuleDao")
public interface DevpDevFunaModuleDao extends BaseDao<DevpDevFunaModule, Long>{


}
