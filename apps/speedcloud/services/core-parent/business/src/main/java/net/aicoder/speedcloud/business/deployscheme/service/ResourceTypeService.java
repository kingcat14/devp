package net.aicoder.speedcloud.business.deployscheme.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceTypeDao;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceTypeSpecification;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceType;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("resourceTypeService")
public class ResourceTypeService  extends GenericCrudService<ResourceType, Long, ResourceTypeCondition, ResourceTypeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceTypeService.class);

	@Override
	public Specification<ResourceType> getSpecification(ResourceTypeCondition condition) {
		return new ResourceTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ResourceType.PROPERTY_NAME);
		return sort;
	}
}