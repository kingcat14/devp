package net.aicoder.speedcloud.business.deployscheme.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceDao;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourceSpecification;
import net.aicoder.speedcloud.business.deployscheme.domain.Resource;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("resourceService")
public class ResourceService  extends GenericCrudService<Resource, Long, ResourceCondition, ResourceDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceService.class);

	@Override
	public Specification<Resource> getSpecification(ResourceCondition condition) {
		return new ResourceSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Resource.PROPERTY_NAME);
		return sort;
	}
}