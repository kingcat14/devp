package net.aicoder.speedcloud.business.deploy.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deploy.dao.DevpSysDpyResourcesTypeDao;
import net.aicoder.speedcloud.business.deploy.dao.DevpSysDpyResourcesTypeSpecification;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourcesType;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResourcesTypeService")
public class DevpSysDpyResourcesTypeService  extends GenericCrudService<DevpSysDpyResourcesType, Long, DevpSysDpyResourcesTypeCondition, DevpSysDpyResourcesTypeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesTypeService.class);

	@Override
	public Specification<DevpSysDpyResourcesType> getSpecification(DevpSysDpyResourcesTypeCondition condition) {
		return new DevpSysDpyResourcesTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysDpyResourcesType.PROPERTY_NAME);
		return sort;
	}
}