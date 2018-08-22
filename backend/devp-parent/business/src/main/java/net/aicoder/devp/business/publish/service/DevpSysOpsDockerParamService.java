package net.aicoder.devp.business.publish.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.devp.business.publish.dao.DevpSysOpsDockerParamDao;
import net.aicoder.devp.business.publish.dao.DevpSysOpsDockerParamSpecification;
import net.aicoder.devp.business.publish.domain.DevpSysOpsDockerParam;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysOpsDockerParamService")
public class DevpSysOpsDockerParamService  extends GenericCrudService<DevpSysOpsDockerParam, Long, DevpSysOpsDockerParamCondition, DevpSysOpsDockerParamDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsDockerParamService.class);

	@Override
	public Specification<DevpSysOpsDockerParam> getSpecification(DevpSysOpsDockerParamCondition condition) {
		return new DevpSysOpsDockerParamSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysOpsDockerParam.PROPERTY_TID);
		return sort;
	}
}