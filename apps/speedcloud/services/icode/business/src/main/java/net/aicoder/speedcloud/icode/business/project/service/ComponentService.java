package net.aicoder.speedcloud.icode.business.project.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.domain.domain.Domain;
import net.aicoder.speedcloud.icode.business.domain.domain.Entity;
import net.aicoder.speedcloud.icode.business.domain.service.DomainService;
import net.aicoder.speedcloud.icode.business.domain.service.EntityService;
import net.aicoder.speedcloud.icode.business.project.dao.ComponentDao;
import net.aicoder.speedcloud.icode.business.project.dao.ComponentSpecification;
import net.aicoder.speedcloud.icode.business.project.domain.Component;
import net.aicoder.speedcloud.icode.business.project.domain.ComponentDomainRelation;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("componentService")
@Slf4j
public class ComponentService  extends GenericCrudService<Component, String, ComponentCondition, ComponentDao> {


	@Autowired
	private EntityService entityService;

	@Autowired
	private DomainService domainService;

	@Autowired
	private ComponentDomainRelationService relationService;

	/**
	 * 查出component下的所有实体
	 * @return
	 */
	public List<Entity> findEntityInComponent(String componentId){

		List<Entity> result = new ArrayList<>();

		List<ComponentDomainRelation> componentDomainRelationList = relationService.findByComponent(componentId);

		if(CollectionUtils.isEmpty(componentDomainRelationList)){
			return result;
		}

		List<Domain> domainList = new ArrayList<>();

		componentDomainRelationList.forEach((relation)->{
			Domain domain = domainService.find(relation.getDomain());
			domainList.add(domain);
			domainList.addAll(domainService.findSubDomain(relation.getDomain()));
		});

		domainList.forEach(domain -> {
			List<Entity> entities = entityService.findEntityByDomainId(domain.getId());
			if(CollectionUtils.isNotEmpty(entities)){
				result.addAll(entities);
			}
		});

		return result;
	}

	@Override
	public Specification<Component> getSpecification(ComponentCondition condition) {
		return new ComponentSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Component.PROPERTY_BASE_PACKAGE);
		return sort;
	}
}