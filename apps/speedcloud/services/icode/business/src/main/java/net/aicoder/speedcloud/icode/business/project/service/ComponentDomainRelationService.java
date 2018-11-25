package net.aicoder.speedcloud.icode.business.project.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.project.dao.ComponentDomainRelationDao;
import net.aicoder.speedcloud.icode.business.project.dao.ComponentDomainRelationSpecification;
import net.aicoder.speedcloud.icode.business.project.domain.ComponentDomainRelation;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("componentDomainRelationService")
@Slf4j
public class ComponentDomainRelationService  extends GenericCrudService<ComponentDomainRelation, String, ComponentDomainRelationCondition, ComponentDomainRelationDao> {

	public List<ComponentDomainRelation> findByComponent(String componentId){
		return dao.findByComponent(componentId);
	}


	@Override
	public Specification<ComponentDomainRelation> getSpecification(ComponentDomainRelationCondition condition) {
		return new ComponentDomainRelationSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ComponentDomainRelation.PROPERTY_COMPONENT);
		return sort;
	}
}