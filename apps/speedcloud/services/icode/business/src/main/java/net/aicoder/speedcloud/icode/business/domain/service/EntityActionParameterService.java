package net.aicoder.speedcloud.icode.business.domain.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.domain.dao.EntityActionParameterDao;
import net.aicoder.speedcloud.icode.business.domain.dao.EntityActionParameterSpecification;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityActionParameter;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("entityActionParameterService")
@Slf4j
public class EntityActionParameterService  extends GenericCrudService<EntityActionParameter, String, EntityActionParameterCondition, EntityActionParameterDao> {

	/**
	 * 查询行为的所有参数
	 * @param actionId
	 * @return
	 */
	public List<EntityActionParameter> findByAction(String actionId){
		return dao.findByEntityAction(actionId);
	}

	/**
	 * 删除行为的所有参数
	 * @param actionId
	 */
	@Transactional
	public int deleteByAction(String actionId){
		return dao.deleteByEntityAction(actionId);
	}

	@Override
	public Specification<EntityActionParameter> getSpecification(EntityActionParameterCondition condition) {
		return new EntityActionParameterSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, EntityActionParameter.PROPERTY_CODE);
		return sort;
	}
}