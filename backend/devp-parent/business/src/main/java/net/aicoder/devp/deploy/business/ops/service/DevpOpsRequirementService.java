package net.aicoder.devp.deploy.business.ops.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.deploy.business.ops.dao.DevpOpsRequirementDao;
import net.aicoder.devp.deploy.business.ops.dao.DevpOpsRequirementSpecification;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsRequirement;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsRequirementCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpOpsRequirementService")
public class DevpOpsRequirementService  extends CrudService<DevpOpsRequirement, DevpOpsRequirementCondition, DevpOpsRequirementDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsRequirementService.class);

	@Override
	public Specification<DevpOpsRequirement> getSpecification(DevpOpsRequirementCondition condition) {
		return new DevpOpsRequirementSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpOpsRequirement.PROPERTY_TID);
		return sort;
	}
}