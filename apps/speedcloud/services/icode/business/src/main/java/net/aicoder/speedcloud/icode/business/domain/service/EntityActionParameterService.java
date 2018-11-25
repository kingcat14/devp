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


@Service("entityActionParameterService")
@Slf4j
public class EntityActionParameterService  extends GenericCrudService<EntityActionParameter, String, EntityActionParameterCondition, EntityActionParameterDao> {

	@Override
	public Specification<EntityActionParameter> getSpecification(EntityActionParameterCondition condition) {
		return new EntityActionParameterSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, EntityActionParameter.PROPERTY_CODE);
		return sort;
	}
}