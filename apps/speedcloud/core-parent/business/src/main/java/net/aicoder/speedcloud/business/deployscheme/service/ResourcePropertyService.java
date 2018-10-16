package net.aicoder.speedcloud.business.deployscheme.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourcePropertyDao;
import net.aicoder.speedcloud.business.deployscheme.dao.ResourcePropertySpecification;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceProperty;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcePropertyCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("resourcePropertyService")
public class ResourcePropertyService  extends GenericCrudService<ResourceProperty, Long, ResourcePropertyCondition, ResourcePropertyDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourcePropertyService.class);

	@Override
	public Specification<ResourceProperty> getSpecification(ResourcePropertyCondition condition) {
		return new ResourcePropertySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ResourceProperty.PROPERTY_RESOURCE);
		return sort;
	}
}