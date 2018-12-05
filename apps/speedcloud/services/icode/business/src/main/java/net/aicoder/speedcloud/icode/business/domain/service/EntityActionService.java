package net.aicoder.speedcloud.icode.business.domain.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.domain.dao.EntityActionDao;
import net.aicoder.speedcloud.icode.business.domain.dao.EntityActionSpecification;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityAction;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("entityActionService")
@Slf4j
public class EntityActionService  extends GenericCrudService<EntityAction, String, EntityActionCondition, EntityActionDao> {

	/**
	 * 查询模型的所有属性
	 * @param entityId
	 * @return
	 */
	public List<EntityAction> findByEntity(String entityId){
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
	public Specification<EntityAction> getSpecification(EntityActionCondition condition) {
		return new EntityActionSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, EntityAction.PROPERTY_CODE);
		return sort;
	}
}