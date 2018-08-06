package net.aicoder.devp.maintenance.business.common.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.maintenance.business.common.domain.Attachment;
import org.springframework.stereotype.Repository;


/**
 * Attachment的数据库操作
 * @author icode
 */
@Repository("attachmentDao")
public interface AttachmentDao extends BaseDao<Attachment, Long>{


}
