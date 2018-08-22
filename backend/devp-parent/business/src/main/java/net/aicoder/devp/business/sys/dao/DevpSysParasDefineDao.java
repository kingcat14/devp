package net.aicoder.devp.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.sys.domain.DevpSysParasDefine;
import org.springframework.stereotype.Repository;



/**
 * 系统元素扩充信息的数据库操作
 * @author icode
 */
@Repository("devpSysParasDefineDao")
public interface DevpSysParasDefineDao extends BaseDao<DevpSysParasDefine, Long>{


}
