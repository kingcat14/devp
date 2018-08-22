package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskDockerDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskDockerSpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskDocker;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskDockerService")
public class DevpSysOpsTaskDockerService  extends GenericCrudService<DevpSysOpsTaskDocker, Long, DevpSysOpsTaskDockerCondition, DevpSysOpsTaskDockerDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskDockerService.class);

	@Override
	public Specification<DevpSysOpsTaskDocker> getSpecification(DevpSysOpsTaskDockerCondition condition) {
		return new DevpSysOpsTaskDockerSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsTaskDocker.PROPERTY_TID);
		return sort;
	}
}