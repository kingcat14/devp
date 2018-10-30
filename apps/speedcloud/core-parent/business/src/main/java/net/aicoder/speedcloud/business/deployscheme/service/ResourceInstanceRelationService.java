package net.aicoder.speedcloud.business.deployscheme.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceInstanceRelationDao;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceInstanceRelationSpecification;
import net.aicoder.speedcloud.business.deployscheme.domain.Resource;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceInstanceRelation;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("resourceInstanceRelationService")
public class ResourceInstanceRelationService  extends GenericCrudService<ResourceInstanceRelation, Long, ResourceInstanceRelationCondition, ResourceInstanceRelationDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceInstanceRelationService.class);

	@Autowired
	private ResourceService resourceService;



	/**
	 * 更新资源关联的所有实例
	 * @param resourceId
	 * @param tList
	 */
	@Transactional
	public void updateAll(Long resourceId, List<ResourceInstanceRelation> tList){

		Resource resource = resourceService.find(resourceId);

		dao.deleteByResource(resourceId);

		for(ResourceInstanceRelation relation : tList){
			relation.setScheme(resource.getScheme());
			relation.setProject(resource.getProject());
			this.add(relation);
		}
	}

	@Override
	public Specification<ResourceInstanceRelation> getSpecification(ResourceInstanceRelationCondition condition) {
		return new ResourceInstanceRelationSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ResourceInstanceRelation.PROPERTY_TYPE);
		return sort;
	}
}