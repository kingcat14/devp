package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsDockerAccessDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsDockerAccessSpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsDockerAccess;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerAccessCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsDockerAccessService")
public class DevpSysOpsDockerAccessService  extends GenericCrudService<DevpSysOpsDockerAccess, Long, DevpSysOpsDockerAccessCondition, DevpSysOpsDockerAccessDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsDockerAccessService.class);

	@Override
	public Specification<DevpSysOpsDockerAccess> getSpecification(DevpSysOpsDockerAccessCondition condition) {
		return new DevpSysOpsDockerAccessSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsDockerAccess.PROPERTY_TID);
		return sort;
	}
}