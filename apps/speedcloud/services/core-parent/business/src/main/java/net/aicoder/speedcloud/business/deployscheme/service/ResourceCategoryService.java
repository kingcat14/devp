package net.aicoder.speedcloud.business.deployscheme.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceCategoryDao;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceCategorySpecification;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceCategory;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service("resourceCategoryService")
@Slf4j
public class ResourceCategoryService  extends GenericCrudService<ResourceCategory, String, ResourceCategoryCondition, ResourceCategoryDao> {

	@Transactional
	public void add(ResourceCategory resourceCategory){
		resourceCategory.setId(resourceCategory.getCode());
		dao.save(resourceCategory);
	}

	@Override
	public Specification<ResourceCategory> getSpecification(ResourceCategoryCondition condition) {
		return new ResourceCategorySpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.ASC, ResourceCategory.PROPERTY_IDX);

		return sort;
	}
}