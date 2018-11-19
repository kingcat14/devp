package net.aicoder.speedcloud.business.deployscheme.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceCategoryDao;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceCategorySpecification;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceCategory;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("resourceCategoryService")
public class ResourceCategoryService  extends GenericCrudService<ResourceCategory, Long, ResourceCategoryCondition, ResourceCategoryDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceCategoryService.class);

	@Override
	public Specification<ResourceCategory> getSpecification(ResourceCategoryCondition condition) {
		return new ResourceCategorySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ResourceCategory.PROPERTY_NAME);
		return sort;
	}
}