package net.aicoder.speedcloud.business.deploy.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deploy.dao.DevpSysDpyResourceRelationTypeDao;
import net.aicoder.speedcloud.business.deploy.dao.DevpSysDpyResourceRelationTypeSpecification;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourceRelationType;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRelationTypeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("devpSysDpyResourceRelationTypeService")
public class DevpSysDpyResourceRelationTypeService  extends GenericCrudService<DevpSysDpyResourceRelationType, Long, DevpSysDpyResourceRelationTypeCondition, DevpSysDpyResourceRelationTypeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourceRelationTypeService.class);

	@Override
	public Specification<DevpSysDpyResourceRelationType> getSpecification(DevpSysDpyResourceRelationTypeCondition condition) {
		return new DevpSysDpyResourceRelationTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, DevpSysDpyResourceRelationType.PROPERTY_NAME);
		return sort;
	}
}