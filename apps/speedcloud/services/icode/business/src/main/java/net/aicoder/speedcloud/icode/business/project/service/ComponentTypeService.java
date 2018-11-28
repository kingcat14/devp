package net.aicoder.speedcloud.icode.business.project.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.project.dao.ComponentTypeDao;
import net.aicoder.speedcloud.icode.business.project.dao.ComponentTypeSpecification;
import net.aicoder.speedcloud.icode.business.project.domain.ComponentType;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("componentTypeService")
@Slf4j
public class ComponentTypeService  extends GenericCrudService<ComponentType, String, ComponentTypeCondition, ComponentTypeDao> {

	@Override
	public Specification<ComponentType> getSpecification(ComponentTypeCondition condition) {
		return new ComponentTypeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ComponentType.PROPERTY_CODE);
		return sort;
	}
}