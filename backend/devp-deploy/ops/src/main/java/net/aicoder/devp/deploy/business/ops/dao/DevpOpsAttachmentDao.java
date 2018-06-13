package net.aicoder.devp.deploy.business.ops.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsAttachment;
import org.springframework.stereotype.Repository;



/**
 * 附件定义的数据库操作
 * @author icode
 */
@Repository("devpOpsAttachmentDao")
public interface DevpOpsAttachmentDao extends BaseDao<DevpOpsAttachment, Long>{


}
