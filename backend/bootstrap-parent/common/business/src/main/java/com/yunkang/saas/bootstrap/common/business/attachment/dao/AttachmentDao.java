package com.yunkang.saas.bootstrap.common.business.attachment.dao;

import com.yunkang.saas.bootstrap.common.business.attachment.domain.Attachment;
import com.yunkang.saas.common.jpa.BaseDao;
import org.springframework.stereotype.Repository;


/**
 * Attachment的数据库操作
 * @author icode
 */
@Repository("attachmentDao")
public interface AttachmentDao extends BaseDao<Attachment, Long> {


}
