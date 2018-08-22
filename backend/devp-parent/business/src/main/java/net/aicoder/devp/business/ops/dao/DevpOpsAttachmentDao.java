package net.aicoder.devp.business.ops.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.ops.domain.DevpOpsAttachment;
import org.springframework.stereotype.Repository;



/**
 * 附件的数据库操作
 * @author icode
 */
@Repository("devpOpsAttachmentDao")
public interface DevpOpsAttachmentDao extends BaseDao<DevpOpsAttachment, Long>{


}
