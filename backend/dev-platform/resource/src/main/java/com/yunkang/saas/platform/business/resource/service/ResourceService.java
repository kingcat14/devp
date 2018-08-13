package com.yunkang.saas.platform.business.resource.service;


import com.yunkang.saas.common.jpa.CrudService;
import com.yunkang.saas.common.util.IdSnowflake;
import com.yunkang.saas.platform.business.resource.dao.ResourceDao;
import com.yunkang.saas.platform.business.resource.dao.ResourceSpecification;
import com.yunkang.saas.platform.business.resource.domain.Resource;
import com.yunkang.saas.platform.business.resource.dto.ResourceCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service("resourceService")
public class ResourceService  extends CrudService<Resource, ResourceCondition, ResourceDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Resource.class);



	public void add(Resource resource){

		if(resource.getId() == null) {
			resource.setId(IdSnowflake.getLocalInstance().nextId(getClass()));
		}

		dao.save(resource);

	}



	public Resource findByCodeAndAppId(Resource resource){
		return dao.findByCodeAndAppId(resource.getCode(), resource.getAppId());
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