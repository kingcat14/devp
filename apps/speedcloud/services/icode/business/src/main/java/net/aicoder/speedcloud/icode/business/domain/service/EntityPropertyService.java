package net.aicoder.speedcloud.icode.business.domain.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.domain.dao.EntityPropertyDao;
import net.aicoder.speedcloud.icode.business.domain.dao.EntityPropertySpecification;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("entityPropertyService")
@Slf4j
public class EntityPropertyService  extends GenericCrudService<EntityProperty, String, EntityPropertyCondition, EntityPropertyDao> {

	@Override
	public Specification<EntityProperty> getSpecification(EntityPropertyCondition condition) {
		return new EntityPropertySpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC, EntityProperty.PROPERTY_IDX);
		return sort;
	}
}