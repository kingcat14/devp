package net.aicoder.devp.business.publish.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeNode;
import org.springframework.stereotype.Repository;



/**
 * 流水线执行节点的数据库操作
 * @author icode
 */
@Repository("devpSysOpsPipeNodeDao")
public interface DevpSysOpsPipeNodeDao extends BaseDao<DevpSysOpsPipeNode, Long>{


}
