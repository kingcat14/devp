package net.aicoder.devp.business.ops.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.ops.domain.DevpOpsIssue;
import org.springframework.stereotype.Repository;



/**
 * 问题记录的数据库操作
 * @author icode
 */
@Repository("devpOpsIssueDao")
public interface DevpOpsIssueDao extends BaseDao<DevpOpsIssue, Long>{


}
