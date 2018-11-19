package net.aicoder.speedcloud.business.deployscheme.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceRelationDao;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceRelationSpecification;
import net.aicoder.speedcloud.business.deployscheme.domain.Resource;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceRelation;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("resourceRelationService")
public class ResourceRelationService  extends GenericCrudService<ResourceRelation, Long, ResourceRelationCondition, ResourceRelationDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceRelationService.class);

	@Autowired
	private ResourceService resourceService;

	@Transactional
	public void add(ResourceRelation relation){
		relation.fillId();

		Resource resource = resourceService.find(relation.getResource());
		relation.setScheme(resource.getScheme());

		dao.save(relation);
	}

	@Override
	public Specification<ResourceRelation> getSpecification(ResourceRelationCondition condition) {
		return new ResourceRelationSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ResourceRelation.PROPERTY_RESOURCE);
		return sort;
	}
}