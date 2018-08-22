package net.aicoder.devp.business.ops.service;


import com.yunkang.saas.common.jpa.CrudService;

import net.aicoder.devp.business.ops.dao.DevpOpsParasDefineDao;
import net.aicoder.devp.business.ops.dao.DevpOpsParasDefineSpecification;
import net.aicoder.devp.business.ops.domain.DevpOpsParasDefine;
import net.aicoder.devp.business.ops.dto.DevpOpsParasDefineCondition;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpOpsParasDefineService")
public class DevpOpsParasDefineService  extends CrudService<DevpOpsParasDefine, DevpOpsParasDefineCondition, DevpOpsParasDefineDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsParasDefineService.class);

	@Override
	public Specification<DevpOpsParasDefine> getSpecification(DevpOpsParasDefineCondition condition) {
		return new DevpOpsParasDefineSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpOpsParasDefine.PROPERTY_TID);
		return sort;
	}
}