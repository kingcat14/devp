package net.aicoder.devp.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.sys.domain.DevpSysIdeRef;
import org.springframework.stereotype.Repository;



/**
 * 开发工程引用组件的数据库操作
 * @author icode
 */
@Repository("devpSysIdeRefDao")
public interface DevpSysIdeRefDao extends BaseDao<DevpSysIdeRef, Long>{


}
