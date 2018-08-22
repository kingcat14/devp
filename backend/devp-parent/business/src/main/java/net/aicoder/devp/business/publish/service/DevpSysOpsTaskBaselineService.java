package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskBaselineDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskBaselineSpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskBaseline;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskBaselineCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskBaselineService")
public class DevpSysOpsTaskBaselineService  extends GenericCrudService<DevpSysOpsTaskBaseline, Long, DevpSysOpsTaskBaselineCondition, DevpSysOpsTaskBaselineDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskBaselineService.class);

	@Override
	public Specification<DevpSysOpsTaskBaseline> getSpecification(DevpSysOpsTaskBaselineCondition condition) {
		return new DevpSysOpsTaskBaselineSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsTaskBaseline.PROPERTY_TID);
		return sort;
	}
}