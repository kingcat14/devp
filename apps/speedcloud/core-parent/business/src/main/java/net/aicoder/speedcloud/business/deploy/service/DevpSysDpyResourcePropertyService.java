package net.aicoder.speedcloud.business.deploy.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deploy.dao.DevpSysDpyResourcePropertyDao;
import net.aicoder.speedcloud.business.deploy.dao.DevpSysDpyResourcePropertySpecification;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourceProperty;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcePropertyCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResourcePropertyService")
public class DevpSysDpyResourcePropertyService  extends GenericCrudService<DevpSysDpyResourceProperty, Long, DevpSysDpyResourcePropertyCondition, DevpSysDpyResourcePropertyDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcePropertyService.class);

	@Override
	public Specification<DevpSysDpyResourceProperty> getSpecification(DevpSysDpyResourcePropertyCondition condition) {
		return new DevpSysDpyResourcePropertySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysDpyResourceProperty.PROPERTY_RESOURCE);
		return sort;
	}
}