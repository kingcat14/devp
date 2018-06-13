package net.aicoder.devp.deploy.business.ops.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.deploy.business.ops.dao.DevpOpsCiGroupDao;
import net.aicoder.devp.deploy.business.ops.dao.DevpOpsCiGroupSpecification;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsCiGroup;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsCiGroupCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpOpsCiGroupService")
public class DevpOpsCiGroupService  extends CrudService<DevpOpsCiGroup, DevpOpsCiGroupCondition, DevpOpsCiGroupDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsCiGroupService.class);

	@Override
	public Specification<DevpOpsCiGroup> getSpecification(DevpOpsCiGroupCondition condition) {
		return new DevpOpsCiGroupSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpOpsCiGroup.PROPERTY_TID);
		return sort;
	}
}