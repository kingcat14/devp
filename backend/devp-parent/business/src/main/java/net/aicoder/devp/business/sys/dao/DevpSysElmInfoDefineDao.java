package net.aicoder.devp.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.sys.domain.DevpSysElmInfoDefine;
import org.springframework.stereotype.Repository;



/**
 * 系统元素扩充信息定义的数据库操作
 * @author icode
 */
@Repository("devpSysElmInfoDefineDao")
public interface DevpSysElmInfoDefineDao extends BaseDao<DevpSysElmInfoDefine, Long>{


}
