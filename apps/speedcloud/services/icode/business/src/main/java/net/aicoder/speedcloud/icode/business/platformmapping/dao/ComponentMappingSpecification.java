package net.aicoder.speedcloud.icode.business.platformmapping.dao;

import net.aicoder.speedcloud.icode.business.platformmapping.domain.ComponentMapping;
import net.aicoder.speedcloud.icode.business.platformmapping.dto.ComponentMappingCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ComponentMappingSpecification implements Specification<ComponentMapping>{

	private ComponentMappingCondition condition;

	public ComponentMappingSpecification(ComponentMappingCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ComponentMapping> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddComponentPredicate(predicateList, root, cb);
		tryAddPlatformComponentNamePredicate(predicateList, root, cb);
		tryAddPlatformComponentIdPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddComponentPredicate(List<Predicate> predicateList, Root<ComponentMapping> root, CriteriaBuilder cb){
	    if (null != condition.getComponent() ) {
            predicateList.add(cb.equal(root.get(ComponentMapping.PROPERTY_COMPONENT).as(String.class), condition.getComponent()));
        }
	}
	private void tryAddPlatformComponentNamePredicate(List<Predicate> predicateList, Root<ComponentMapping> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPlatformComponentName())){
			predicateList.add(cb.like(root.get(ComponentMapping.PROPERTY_PLATFORM_COMPONENT_NAME).as(String.class), "%"+condition.getPlatformComponentName()+"%"));
		}
	}
	private void tryAddPlatformComponentIdPredicate(List<Predicate> predicateList, Root<ComponentMapping> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPlatformComponentId())){
			predicateList.add(cb.like(root.get(ComponentMapping.PROPERTY_PLATFORM_COMPONENT_ID).as(String.class), "%"+condition.getPlatformComponentId()+"%"));
		}
	}
}


