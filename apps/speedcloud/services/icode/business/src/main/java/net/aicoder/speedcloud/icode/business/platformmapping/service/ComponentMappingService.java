package net.aicoder.speedcloud.icode.business.platformmapping.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.platformmapping.dao.ComponentMappingDao;
import net.aicoder.speedcloud.icode.business.platformmapping.dao.ComponentMappingSpecification;
import net.aicoder.speedcloud.icode.business.platformmapping.domain.ComponentMapping;
import net.aicoder.speedcloud.icode.business.platformmapping.dto.ComponentMappingCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("componentMappingService")
@Slf4j
public class ComponentMappingService  extends GenericCrudService<ComponentMapping, String, ComponentMappingCondition, ComponentMappingDao> {

	@Override
	public Specification<ComponentMapping> getSpecification(ComponentMappingCondition condition) {
		return new ComponentMappingSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, ComponentMapping.PROPERTY_MODIFY_AT);

		return sort;
	}
}