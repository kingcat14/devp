package net.aicoder.devp.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.sys.domain.DevpSysCmp;
import org.springframework.stereotype.Repository;



/**
 * 系统组件的数据库操作
 * @author icode
 */
@Repository("devpSysCmpDao")
public interface DevpSysCmpDao extends BaseDao<DevpSysCmp, Long>{


}
