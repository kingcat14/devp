package net.aicoder.speedcloud.icode.business.domain.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.domain.dao.EntityViewPropertyDao;
import net.aicoder.speedcloud.icode.business.domain.dao.EntityViewPropertySpecification;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityProperty;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityViewProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyCondition;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("entityViewPropertyService")
@Slf4j
public class EntityViewPropertyService  extends GenericCrudService<EntityViewProperty, String, EntityViewPropertyCondition, EntityViewPropertyDao> {


	/**
	 * 通过对象属性创建一个
	 * @param domainModelProperty
	 */
	@Transactional
	public EntityViewProperty create(EntityProperty domainModelProperty){

		EntityViewProperty domainModelViewProperty = new EntityViewProperty();
		BeanUtils.copyProperties(domainModelProperty, domainModelViewProperty);
		domainModelViewProperty.setAddable(!domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setAddRequired(false);
		domainModelViewProperty.setAddViewable(!domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setEditable(!domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setEditRequired(domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setEditViewable(!domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setSearchCondition(!domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setGirdColumn(!domainModelProperty.getPrimaryKey());
		domainModelViewProperty.setSimpleSearchCondition(false);
		checkTid(domainModelViewProperty);
		dao.save(domainModelViewProperty);

		return domainModelViewProperty;
	}
	private void checkTid(EntityViewProperty domainModelViewProperty){
		if(!"tid".equals(domainModelViewProperty.getCode())){
			return;
		}
		domainModelViewProperty.setAddable(false);
		domainModelViewProperty.setAddRequired(false);
		domainModelViewProperty.setAddViewable(false);
		domainModelViewProperty.setEditable(false);
		domainModelViewProperty.setEditRequired(false);
		domainModelViewProperty.setEditViewable(false);
		domainModelViewProperty.setSearchCondition(false);
		domainModelViewProperty.setGirdColumn(false);
		domainModelViewProperty.setSimpleSearchCondition(false);
	}


	/**
	 * 查询模型的所有属性
	 * @param entityId
	 * @return
	 */
	public List<EntityViewProperty> findByEntity(String entityId){
		return dao.findByEntity(entityId);
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
	public Specification<EntityViewProperty> getSpecification(EntityViewPropertyCondition condition) {
		return new EntityViewPropertySpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, EntityViewProperty.PROPERTY_MODIFY_AT);

		return sort;
	}
}