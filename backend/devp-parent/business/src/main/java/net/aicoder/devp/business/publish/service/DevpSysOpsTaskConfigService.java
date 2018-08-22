package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskConfigDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskConfigSpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskConfig;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskConfigCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskConfigService")
public class DevpSysOpsTaskConfigService  extends GenericCrudService<DevpSysOpsTaskConfig, Long, DevpSysOpsTaskConfigCondition, DevpSysOpsTaskConfigDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskConfigService.class);

	@Override
	public Specification<DevpSysOpsTaskConfig> getSpecification(DevpSysOpsTaskConfigCondition condition) {
		return new DevpSysOpsTaskConfigSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsTaskConfig.PROPERTY_TID);
		return sort;
	}
}