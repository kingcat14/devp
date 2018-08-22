package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskPublishDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskPublishSpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskPublish;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskPublishService")
public class DevpSysOpsTaskPublishService  extends GenericCrudService<DevpSysOpsTaskPublish, Long, DevpSysOpsTaskPublishCondition, DevpSysOpsTaskPublishDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskPublishService.class);

	@Override
	public Specification<DevpSysOpsTaskPublish> getSpecification(DevpSysOpsTaskPublishCondition condition) {
		return new DevpSysOpsTaskPublishSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsTaskPublish.PROPERTY_TID);
		return sort;
	}
}