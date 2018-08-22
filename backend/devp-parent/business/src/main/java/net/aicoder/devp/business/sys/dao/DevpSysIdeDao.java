package net.aicoder.devp.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.sys.domain.DevpSysIde;
import org.springframework.stereotype.Repository;



/**
 * 开发工程的数据库操作
 * @author icode
 */
@Repository("devpSysIdeDao")
public interface DevpSysIdeDao extends BaseDao<DevpSysIde, Long>{


}
