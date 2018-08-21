package net.aicoder.devp.deploy.business.ops.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.deploy.business.ops.dao.DevpOpsElementInfoDao;
import net.aicoder.devp.deploy.business.ops.dao.DevpOpsElementInfoSpecification;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsElementInfo;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsElementInfoCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpOpsElementInfoService")
public class DevpOpsElementInfoService  extends CrudService<DevpOpsElementInfo, DevpOpsElementInfoCondition, DevpOpsElementInfoDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsElementInfoService.class);

	@Override
	public Specification<DevpOpsElementInfo> getSpecification(DevpOpsElementInfoCondition condition) {
		return new DevpOpsElementInfoSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , DevpOpsElementInfo.PROPERTY_TID);
		return sort;
	}
}