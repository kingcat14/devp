package net.aicoder.speedcloud.icode.business.domain.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.domain.dao.PropertyTypeDao;
import net.aicoder.speedcloud.icode.business.domain.dao.PropertyTypeSpecification;
import net.aicoder.speedcloud.icode.business.domain.domain.Entity;
import net.aicoder.speedcloud.icode.business.domain.domain.PropertyType;
import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeCondition;
import net.aicoder.speedcloud.icode.business.domain.vo.PropertyTypeVO;
import net.aicoder.speedcloud.icode.business.project.service.ComponentService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("propertyTypeService")
@Slf4j
public class PropertyTypeService  extends GenericCrudService<PropertyType, String, PropertyTypeCondition, PropertyTypeDao> {


	@Autowired
	private EntityService entityService;

	@Autowired
	private DomainService domainService;

	@Autowired
	private ComponentService componentService;

	/**
	 * 选择一个组件下所有可被引用的属性列表
	 * @param componentId
	 * @return
	 */
	public List<PropertyTypeVO> findReferencePropertyType(String componentId){

		List<PropertyTypeVO> result = new ArrayList<>();

		List<Entity> entityList = componentService.findEntityInComponent(componentId);

		for(int i = 0; i < CollectionUtils.size(entityList); i++){
			result.add(initViewProperty(entityList.get(i)));
		}

		return result;

	}

	private PropertyTypeVO initViewProperty(Entity entity){
		PropertyTypeVO vo = new PropertyTypeVO();

//		Module module  = moduleService.find(domainModel.getModuleId());
		vo.setName(domainService.findNamePath(entity.getDomain())+"."+entity.getName());
		vo.setFullName(domainService.findNamePath(entity.getDomain())+"."+entity.getName());
		vo.setId(entity.getId());
		vo.setCode(entity.getId());
		//初始化其他对象
		return vo;
	}


	@Override
	public Specification<PropertyType> getSpecification(PropertyTypeCondition condition) {
		return new PropertyTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC, PropertyType.PROPERTY_VIEW_INDEX);
		return sort;
	}
}