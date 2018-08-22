package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskDeployDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsTaskDeploySpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskDeploy;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsTaskDeployService")
public class DevpSysOpsTaskDeployService  extends GenericCrudService<DevpSysOpsTaskDeploy, Long, DevpSysOpsTaskDeployCondition, DevpSysOpsTaskDeployDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskDeployService.class);

	@Override
	public Specification<DevpSysOpsTaskDeploy> getSpecification(DevpSysOpsTaskDeployCondition condition) {
		return new DevpSysOpsTaskDeploySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsTaskDeploy.PROPERTY_TID);
		return sort;
	}
}