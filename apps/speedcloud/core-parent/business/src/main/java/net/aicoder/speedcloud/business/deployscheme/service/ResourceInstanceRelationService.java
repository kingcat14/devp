package net.aicoder.speedcloud.business.deployscheme.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceInstanceRelationDao;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceInstanceRelationSpecification;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceInstanceRelation;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("resourceInstanceRelationService")
public class ResourceInstanceRelationService  extends GenericCrudService<ResourceInstanceRelation, Long, ResourceInstanceRelationCondition, ResourceInstanceRelationDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceInstanceRelationService.class);

	@Override
	public Specification<ResourceInstanceRelation> getSpecification(ResourceInstanceRelationCondition condition) {
		return new ResourceInstanceRelationSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ResourceInstanceRelation.PROPERTY_TYPE);
		return sort;
	}
}