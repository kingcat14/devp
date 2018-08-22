package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeCmp;
import org.springframework.stereotype.Repository;



/**
 * 产品运维流水线对应的组件的数据库操作
 * @author icode
 */
@Repository("devpSysOpsPipeCmpDao")
public interface DevpSysOpsPipeCmpDao extends BaseDao<DevpSysOpsPipeCmp, Long>{


}
