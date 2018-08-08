package com.yunkang.saas.security.service.business.platform.service;


import com.yunkang.saas.common.jpa.CrudService;
import com.yunkang.saas.common.util.IdSnowflake;
import com.yunkang.saas.security.service.business.platform.dao.ResourceDao;
import com.yunkang.saas.security.service.business.platform.dao.ResourceSpecification;
import com.yunkang.saas.security.service.business.platform.domain.Resource;
import com.yunkang.saas.security.model.dto.ResourceCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


	/**
	 * 根据parentId,index排序
	 * @param resourceList
	 */
	public void sortResourceList(List<Resource> resourceList){
		Collections.sort(resourceList, new Comparator<Resource>() {
			@Override
			public int compare(Resource o1, Resource o2) {



				o1.setOrderIndex(o1.getOrderIndex()==null?0:o1.getOrderIndex());
				o2.setOrderIndex(o2.getOrderIndex()==null?0:o2.getOrderIndex());

				//先按父节点ID排序
				if(o1.getParentId().compareTo(o2.getParentId()) != 0){
					return o1.getParentId().compareTo(o2.getParentId());
				}

				//如果父节点ID一样

				//如果排序相等，则比较ID
				return o1.getOrderIndex().compareTo(o2.getOrderIndex());
			}
		});
	}

	@Override
	public Specification<Resource> getSpecification(ResourceCondition condition) {
		return new ResourceSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC , Resource.PROPERTY_ORDER_INDEX);
		return sort;
	}
}