package net.aicoder.speedcloud.business.deployscheme.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceRelationTypeDao;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceRelationTypeSpecification;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceRelationType;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationTypeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("resourceRelationTypeService")
public class ResourceRelationTypeService  extends GenericCrudService<ResourceRelationType, Long, ResourceRelationTypeCondition, ResourceRelationTypeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceRelationTypeService.class);

	@Override
	public Specification<ResourceRelationType> getSpecification(ResourceRelationTypeCondition condition) {
		return new ResourceRelationTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ResourceRelationType.PROPERTY_NAME);
		return sort;
	}
}