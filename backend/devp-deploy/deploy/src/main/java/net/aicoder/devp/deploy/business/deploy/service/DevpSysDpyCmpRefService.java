package net.aicoder.devp.deploy.business.deploy.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.deploy.business.deploy.dao.DevpSysDpyCmpRefDao;
import net.aicoder.devp.deploy.business.deploy.dao.DevpSysDpyCmpRefSpecification;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyCmpRef;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyCmpRefCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDpyCmpRefService")
public class DevpSysDpyCmpRefService  extends CrudService<DevpSysDpyCmpRef, DevpSysDpyCmpRefCondition, DevpSysDpyCmpRefDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyCmpRefService.class);

	@Override
	public Specification<DevpSysDpyCmpRef> getSpecification(DevpSysDpyCmpRefCondition condition) {
		return new DevpSysDpyCmpRefSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysDpyCmpRef.PROPERTY_TID);
		return sort;
	}
}