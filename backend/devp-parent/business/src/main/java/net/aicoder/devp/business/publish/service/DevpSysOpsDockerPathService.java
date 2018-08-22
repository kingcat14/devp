package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsDockerPathDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsDockerPathSpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsDockerPath;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsDockerPathService")
public class DevpSysOpsDockerPathService  extends GenericCrudService<DevpSysOpsDockerPath, Long, DevpSysOpsDockerPathCondition, DevpSysOpsDockerPathDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsDockerPathService.class);

	@Override
	public Specification<DevpSysOpsDockerPath> getSpecification(DevpSysOpsDockerPathCondition condition) {
		return new DevpSysOpsDockerPathSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsDockerPath.PROPERTY_TID);
		return sort;
	}
}