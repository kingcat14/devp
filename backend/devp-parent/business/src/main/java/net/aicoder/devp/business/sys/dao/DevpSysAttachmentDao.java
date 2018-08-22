package net.aicoder.devp.business.sys.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.business.sys.domain.DevpSysAttachment;
import org.springframework.stereotype.Repository;



/**
 * 附件的数据库操作
 * @author icode
 */
@Repository("devpSysAttachmentDao")
public interface DevpSysAttachmentDao extends BaseDao<DevpSysAttachment, Long>{


}
