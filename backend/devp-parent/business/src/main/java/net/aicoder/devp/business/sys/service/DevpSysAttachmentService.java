package net.aicoder.devp.business.sys.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.sys.dao.DevpSysAttachmentDao;
import net.aicoder.devp.business.sys.dao.DevpSysAttachmentSpecification;
import net.aicoder.devp.business.sys.domain.DevpSysAttachment;
import net.aicoder.devp.business.sys.dto.DevpSysAttachmentCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysAttachmentService")
public class DevpSysAttachmentService  extends GenericCrudService<DevpSysAttachment, Long, DevpSysAttachmentCondition, DevpSysAttachmentDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysAttachmentService.class);

	@Override
	public Specification<DevpSysAttachment> getSpecification(DevpSysAttachmentCondition condition) {
		return new DevpSysAttachmentSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysAttachment.PROPERTY_TID);
		return sort;
	}
}