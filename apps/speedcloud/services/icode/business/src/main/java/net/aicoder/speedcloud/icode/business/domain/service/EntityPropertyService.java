package net.aicoder.speedcloud.icode.business.domain.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.domain.dao.EntityPropertyDao;
import net.aicoder.speedcloud.icode.business.domain.dao.EntityPropertySpecification;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("entityPropertyService")
@Slf4j
public class EntityPropertyService  extends GenericCrudService<EntityProperty, String, EntityPropertyCondition, EntityPropertyDao> {

	@Autowired
	EntityViewPropertyService entityViewPropertyService;

	@Transactional
	public void add(EntityProperty domainModelProperty){

		/*
		 * 1.新增属性
		 * 2.新增显示属性
		 */

		//1.新增属性
		domainModelProperty.fillId();
		dao.save(domainModelProperty);

		//2.新增显示属性
		entityViewPropertyService.create(domainModelProperty);

	}

	/**
	 * 查询模型的所有属性
	 * @param entityId
	 * @return
	 */
	public List<EntityProperty> findByEntity(String entityId){
		return dao.findByEntity(entityId);
	}


	public EntityProperty findPrimaryKeyFormEntity(String entityId){
		EntityPropertyCondition condition = new EntityPropertyCondition();
		condition.setEntity(entityId);
		condition.setPrimaryKey(true);

		return dao.findOne(getSpecification(condition));
	}

	/**
	 * 删除对象的所有属性
	 * @param entityId
	 */
	@Transactional
	public int deleteByEntity(String entityId){
		return dao.deleteByEntity(entityId);
	}

	@Override
	public Specification<EntityProperty> getSpecification(EntityPropertyCondition condition) {
		return new EntityPropertySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC, EntityProperty.PROPERTY_IDX);
		return sort;
	}
}