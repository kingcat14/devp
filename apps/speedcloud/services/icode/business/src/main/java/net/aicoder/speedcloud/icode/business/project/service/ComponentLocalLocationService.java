package net.aicoder.speedcloud.icode.business.project.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.project.dao.ComponentLocalLocationDao;
import net.aicoder.speedcloud.icode.business.project.dao.ComponentLocalLocationSpecification;
import net.aicoder.speedcloud.icode.business.project.domain.ComponentLocalLocation;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("componentLocalLocationService")
@Slf4j
public class ComponentLocalLocationService  extends GenericCrudService<ComponentLocalLocation, String, ComponentLocalLocationCondition, ComponentLocalLocationDao> {

	@Override
	public Specification<ComponentLocalLocation> getSpecification(ComponentLocalLocationCondition condition) {
		return new ComponentLocalLocationSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ComponentLocalLocation.PROPERTY_ACCOUNT_ID);
		return sort;
	}
}