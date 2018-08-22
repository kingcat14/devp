package net.aicoder.devp.business.deploy.service;


import com.yunkang.saas.common.jpa.CrudService;

import net.aicoder.devp.business.deploy.dao.DevpSysDpyResInstHostDao;
import net.aicoder.devp.business.deploy.dao.DevpSysDpyResInstHostSpecification;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyResInstHost;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostCondition;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResInstHostService")
public class DevpSysDpyResInstHostService  extends CrudService<DevpSysDpyResInstHost, DevpSysDpyResInstHostCondition, DevpSysDpyResInstHostDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResInstHostService.class);

	@Override
	public Specification<DevpSysDpyResInstHost> getSpecification(DevpSysDpyResInstHostCondition condition) {
		return new DevpSysDpyResInstHostSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpSysDpyResInstHost.PROPERTY_TID);
		return sort;
	}
}