package net.aicoder.devp.security.business.security.dao;

import net.aicoder.devp.security.business.security.dto.RoleResourceRelationCondition;
import net.aicoder.devp.security.business.security.domain.RoleResourceRelation;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class RoleResourceRelationSpecification implements Specification<RoleResourceRelation>{

	RoleResourceRelationCondition condition;

	public RoleResourceRelationSpecification(RoleResourceRelationCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<RoleResourceRelation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddRoleIdPredicate(predicateList, root, cb);
		tryAddResourceIdPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddRoleIdPredicate(List<Predicate> predicateList, Root<RoleResourceRelation> root, CriteriaBuilder cb){
		if(null==(condition.getRoleId())){
			predicateList.add(cb.equal(root.get(RoleResourceRelation.PROPERTY_ROLE_ID).as(String.class), "%"+condition.getRoleId()+"%"));
		}
	}
	private void tryAddResourceIdPredicate(List<Predicate> predicateList, Root<RoleResourceRelation> root, CriteriaBuilder cb){
		if(null==(condition.getResourceId())){
			predicateList.add(cb.equal(root.get(RoleResourceRelation.PROPERTY_RESOURCE_ID).as(String.class), "%"+condition.getResourceId()+"%"));
		}
	}
}


