package net.aicoder.devp.maintenance.business.common.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.maintenance.business.common.dao.AttachmentDao;
import net.aicoder.devp.maintenance.business.common.dao.AttachmentSpecification;
import net.aicoder.devp.maintenance.business.common.domain.Attachment;
import net.aicoder.devp.maintenance.business.common.dto.AttachmentCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("attachmentService")
public class AttachmentService  extends GenericCrudService<Attachment, Long, AttachmentCondition, AttachmentDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttachmentService.class);

	@Override
	public Specification<Attachment> getSpecification(AttachmentCondition condition) {
		return new AttachmentSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , Attachment.PROPERTY_NAME);
		return sort;
	}
}