package net.aicoder.devp.business.ops.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.ops.dao.DevpOpsConnectorInfoDao;
import net.aicoder.devp.business.ops.dao.DevpOpsConnectorInfoSpecification;
import net.aicoder.devp.business.ops.domain.DevpOpsConnectorInfo;
import net.aicoder.devp.business.ops.dto.DevpOpsConnectorInfoCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpOpsConnectorInfoService")
public class DevpOpsConnectorInfoService  extends GenericCrudService<DevpOpsConnectorInfo, Long, DevpOpsConnectorInfoCondition, DevpOpsConnectorInfoDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsConnectorInfoService.class);

	@Override
	public Specification<DevpOpsConnectorInfo> getSpecification(DevpOpsConnectorInfoCondition condition) {
		return new DevpOpsConnectorInfoSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpOpsConnectorInfo.PROPERTY_TID);
		return sort;
	}
}