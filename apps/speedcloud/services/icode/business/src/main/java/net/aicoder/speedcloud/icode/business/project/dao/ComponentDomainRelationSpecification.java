package net.aicoder.speedcloud.icode.business.project.dao;

import net.aicoder.speedcloud.icode.business.project.domain.ComponentDomainRelation;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ComponentDomainRelationSpecification implements Specification<ComponentDomainRelation>{

	private ComponentDomainRelationCondition condition;

	public ComponentDomainRelationSpecification(ComponentDomainRelationCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ComponentDomainRelation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddComponentPredicate(predicateList, root, cb);
		tryAddDomainPredicate(predicateList, root, cb);
		tryAddRelationTypePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<ComponentDomainRelation> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(ComponentDomainRelation.PROPERTY_TID).as(Long.class), condition.getTid()));
        }
	}
	private void tryAddComponentPredicate(List<Predicate> predicateList, Root<ComponentDomainRelation> root, CriteriaBuilder cb){
	    if (null != condition.getComponent() ) {
            predicateList.add(cb.equal(root.get(ComponentDomainRelation.PROPERTY_COMPONENT).as(String.class), condition.getComponent()));
        }  
	}
	private void tryAddDomainPredicate(List<Predicate> predicateList, Root<ComponentDomainRelation> root, CriteriaBuilder cb){
	    if (null != condition.getDomain() ) {
            predicateList.add(cb.equal(root.get(ComponentDomainRelation.PROPERTY_DOMAIN).as(String.class), condition.getDomain()));
        }  
	}
	private void tryAddRelationTypePredicate(List<Predicate> predicateList, Root<ComponentDomainRelation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRelationType())){
			predicateList.add(cb.like(root.get(ComponentDomainRelation.PROPERTY_RELATION_TYPE).as(String.class), "%"+condition.getRelationType()+"%"));
		}
	}
}


