package net.aicoder.speedcloud.business.deployscheme.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceTypeDao;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceTypeSpecification;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceType;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("resourceTypeService")
@Slf4j
public class ResourceTypeService  extends GenericCrudService<ResourceType, String, ResourceTypeCondition, ResourceTypeDao> {

	public ResourceType findByCode(String code){
		return dao.findByCode(code);
	}
	@Override
	public Specification<ResourceType> getSpecification(ResourceTypeCondition condition) {
		return new ResourceTypeSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.ASC, ResourceType.PROPERTY_CATEGORY, ResourceType.PROPERTY_IDX, ResourceType.PROPERTY_MODIFY_AT);

		return sort;
	}
}