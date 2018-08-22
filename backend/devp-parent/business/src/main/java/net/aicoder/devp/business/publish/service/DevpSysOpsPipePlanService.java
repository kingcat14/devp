package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsPipePlanDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsPipePlanSpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipePlan;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsPipePlanService")
public class DevpSysOpsPipePlanService  extends GenericCrudService<DevpSysOpsPipePlan, Long, DevpSysOpsPipePlanCondition, DevpSysOpsPipePlanDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipePlanService.class);

	@Override
	public Specification<DevpSysOpsPipePlan> getSpecification(DevpSysOpsPipePlanCondition condition) {
		return new DevpSysOpsPipePlanSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsPipePlan.PROPERTY_TID);
		return sort;
	}
}