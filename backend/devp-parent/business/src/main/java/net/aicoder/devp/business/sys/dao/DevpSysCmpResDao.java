package net.aicoder.devp.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.sys.domain.DevpSysCmpRes;
import org.springframework.stereotype.Repository;



/**
 * 组件关联资源的数据库操作
 * @author icode
 */
@Repository("devpSysCmpResDao")
public interface DevpSysCmpResDao extends BaseDao<DevpSysCmpRes, Long>{


}
