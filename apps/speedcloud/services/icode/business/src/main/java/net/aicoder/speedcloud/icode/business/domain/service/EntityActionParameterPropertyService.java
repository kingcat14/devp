package net.aicoder.speedcloud.icode.business.domain.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.domain.dao.EntityActionParameterPropertyDao;
import net.aicoder.speedcloud.icode.business.domain.dao.EntityActionParameterPropertySpecification;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityActionParameterProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("entityActionParameterPropertyService")
@Slf4j
public class EntityActionParameterPropertyService  extends GenericCrudService<EntityActionParameterProperty, String, EntityActionParameterPropertyCondition, EntityActionParameterPropertyDao> {

	/**
	 * 查询参数的所有属性
	 * @param parameterId
	 * @return
	 */
	public List<EntityActionParameterProperty> findByParameter(String parameterId){
		return dao.findByActionParameter(parameterId);
	}

	/**
	 * 删除参数的所有属性
	 * @param parameterId
	 */
	@Transactional
	public int deleteByAction(String parameterId){
		return dao.deleteByActionParameter(parameterId);
	}

	@Override
	public Specification<EntityActionParameterProperty> getSpecification(EntityActionParameterPropertyCondition condition) {
		return new EntityActionParameterPropertySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, EntityActionParameterProperty.PROPERTY_ACTION_PARAMETER);
		return sort;
	}
}