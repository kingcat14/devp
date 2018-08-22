package net.aicoder.devp.business.deploy.service;


import com.yunkang.saas.common.jpa.CrudService;

import net.aicoder.devp.business.deploy.dao.DevpSysDpyHostDao;
import net.aicoder.devp.business.deploy.dao.DevpSysDpyHostSpecification;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyHost;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostCondition;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDpyHostService")
public class DevpSysDpyHostService  extends CrudService<DevpSysDpyHost, DevpSysDpyHostCondition, DevpSysDpyHostDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyHostService.class);

	@Override
	public Specification<DevpSysDpyHost> getSpecification(DevpSysDpyHostCondition condition) {
		return new DevpSysDpyHostSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysDpyHost.PROPERTY_TID);
		return sort;
	}
}