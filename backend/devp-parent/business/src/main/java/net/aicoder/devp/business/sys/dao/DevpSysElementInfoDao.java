package net.aicoder.devp.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.sys.domain.DevpSysElementInfo;
import org.springframework.stereotype.Repository;



/**
 * 系统元素扩充信息的数据库操作
 * @author icode
 */
@Repository("devpSysElementInfoDao")
public interface DevpSysElementInfoDao extends BaseDao<DevpSysElementInfo, Long>{


}
