package net.aicoder.speedcloud.icode.business.project.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.project.dao.ComponentDao;
import net.aicoder.speedcloud.icode.business.project.dao.ComponentSpecification;
import net.aicoder.speedcloud.icode.business.project.domain.Component;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("componentService")
@Slf4j
public class ComponentService  extends GenericCrudService<Component, String, ComponentCondition, ComponentDao> {

	@Override
	public Specification<Component> getSpecification(ComponentCondition condition) {
		return new ComponentSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Component.PROPERTY_BASE_PACKAGE);
		return sort;
	}
}