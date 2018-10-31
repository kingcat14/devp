package com.yunkang.saas.bootstrap.platform.business.resource.service;


import com.yunkang.saas.bootstrap.platform.business.resource.dao.ResourceDao;
import com.yunkang.saas.bootstrap.platform.business.resource.dao.ResourceSpecification;
import com.yunkang.saas.bootstrap.platform.business.resource.domain.Resource;
import com.yunkang.saas.bootstrap.platform.business.resource.dto.ResourceCondition;
import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.common.util.IdSnowflake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("resourceService")
public class ResourceService  extends GenericCrudService<Resource, Long, ResourceCondition, ResourceDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Resource.class);



	public void add(Resource resource){

		if(resource.getId() == null) {
			resource.setId(IdSnowflake.getLocalInstance().nextId(getClass()));
		}

		dao.save(resource);

	}


	public int countByCode(Long code){
		return dao.countByCode(code);
	}
	public Resource findByCode(Long code){
		return dao.findByCode(code);
	}

	public Resource findByCodeAndAppCode(Resource resource){
		return dao.findByCodeAndAppCode(resource.getCode(), resource.getAppCode());
	}

	@Override
	public Specification<Resource> getSpecification(ResourceCondition condition) {
		return new ResourceSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC , Resource.PROPERTY_ORDER_INDEX, Resource.PROPERTY_CODE);
		return sort;
	}
}