package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskCompile;
import org.springframework.stereotype.Repository;



/**
 * 编译设置的数据库操作
 * @author icode
 */
@Repository("devpSysOpsTaskCompileDao")
public interface DevpSysOpsTaskCompileDao extends BaseDao<DevpSysOpsTaskCompile, Long>{


}
