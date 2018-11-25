package net.aicoder.speedcloud.icode.business.domain.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.domain.dao.EntityDao;
import net.aicoder.speedcloud.icode.business.domain.dao.EntitySpecification;
import net.aicoder.speedcloud.icode.business.domain.domain.Entity;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("entityService")
@Slf4j
public class EntityService  extends GenericCrudService<Entity, String, EntityCondition, EntityDao> {


	@Autowired
	private EntityPropertyService entityPropertyService;

	public Entity copy(String id){
		Entity oldModel = this.find(id);
		Entity domainModel = new Entity();
		BeanUtils.copyProperties(oldModel, domainModel);
		domainModel.setName("COPY_"+domainModel.getName());
		domainModel.setCode("COPY_"+domainModel.getCode());
		this.add(domainModel);

		EntityPropertyCondition propertyCondition = new EntityPropertyCondition();
		propertyCondition.setEntity(id);
		List<EntityProperty> propertyList = entityPropertyService.findAll(propertyCondition);

		for(EntityProperty property : propertyList){
			EntityProperty newProperty = new EntityProperty();
			BeanUtils.copyProperties(property, newProperty);
			newProperty.setEntity(domainModel.getId()+"");
			newProperty.setId(null);
			entityPropertyService.add(newProperty);
		}

		return domainModel;
	}



	/**
	 * 得到当前领域下的对象
	 * @param domainId
	 * @return
	 */
	public List<Entity> findEntityByDomainId(String domainId){
		List<Entity> result = new ArrayList<>();
		//得到当前模块下的对象
		EntityCondition condition = new EntityCondition();
		condition.setDomain(domainId);
		List<Entity> modelList = this.findAll(condition);
		if(CollectionUtils.isNotEmpty(modelList)) {
			result.addAll(modelList);
		}
		return result;
	}

	@Override
	public Specification<Entity> getSpecification(EntityCondition condition) {
		return new EntitySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Entity.PROPERTY_CODE);
		return sort;
	}
}