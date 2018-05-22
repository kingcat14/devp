package net.aicoder.devp.maintenanceui.business.security.service;


import com.yunkang.saas.common.jpa.CrudService;
import net.aicoder.devp.maintenanceui.business.security.dao.ResourceDao;
import net.aicoder.devp.maintenanceui.business.security.dao.ResourceSpecification;
import net.aicoder.devp.maintenanceui.business.security.domain.Resource;
import net.aicoder.devp.maintenanceui.business.security.dto.ResourceCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service("resourceService")
public class ResourceService  extends CrudService<Resource, ResourceCondition, ResourceDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Resource.class);



	@Transactional(readOnly=true)
	public List<Resource> findResourceTree(ResourceCondition resourceCondition){

		Sort sort = new Sort(Sort.Direction.ASC, "parentId", "orderIndex" );
		List<Resource> resourceList = dao.findAll(getSpecification(resourceCondition), sort);
		this.fillResourceTree(resourceList);

		return resourceList;
	}


	public void fillResourceTree(List<Resource> resourceList){
		HashMap<Long, Resource> resourceHashMap = new HashMap<>();
		List<Resource> result = new ArrayList<>();

		for(Resource resource : resourceList){
			//将查询到的资源整理成map
			resourceHashMap.put(resource.getId(), resource);
			//将根目录放入结果集
			if("-1".equals(resource.getParentId())){
				result.add(resource);
			}
		}

		//开始拼装资源树
		Long tempParentId ;
		Resource tempParentResource;
		for(Resource resource : resourceList){

			tempParentId = resource.getParentId();

			//如果是顶级节点, 则处理下一个
			if("-1".equals(resource.getParentId())){
				continue;
			}

			tempParentResource = resourceHashMap.get(tempParentId);
			if(tempParentResource!=null){
				tempParentResource.addChild(resource);
			}

		}

	}

	/**
	 * 根据index排序
	 * @param resourceList
	 */
	public void sortResourceList(List<Resource> resourceList){
		Collections.sort(resourceList, new Comparator<Resource>() {
			@Override
			public int compare(Resource o1, Resource o2) {
				o1.setOrderIndex(o1.getOrderIndex()==null?0:o1.getOrderIndex());
				o2.setOrderIndex(o2.getOrderIndex()==null?0:o2.getOrderIndex());

				//如果排序不相等
				if(o1.getOrderIndex().intValue() != o2.getOrderIndex().intValue()){
					return o1.getOrderIndex().compareTo(o2.getOrderIndex());
				}

				//如果排序相等，则比较ID
				return o1.getId().compareTo(o2.getId());
			}
		});
	}

	@Override
	public Specification<Resource> getSpecification(ResourceCondition condition) {
		return new ResourceSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC , Resource.PROPERTY_NAME);
		return sort;
	}
}