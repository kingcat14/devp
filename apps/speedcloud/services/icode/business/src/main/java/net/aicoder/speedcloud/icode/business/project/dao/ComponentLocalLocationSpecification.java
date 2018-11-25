package net.aicoder.speedcloud.icode.business.project.dao;

import net.aicoder.speedcloud.icode.business.project.domain.ComponentLocalLocation;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ComponentLocalLocationSpecification implements Specification<ComponentLocalLocation>{

	private ComponentLocalLocationCondition condition;

	public ComponentLocalLocationSpecification(ComponentLocalLocationCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ComponentLocalLocation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddAccountIdPredicate(predicateList, root, cb);
		tryAddComponentPredicate(predicateList, root, cb);
		tryAddLocationPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<ComponentLocalLocation> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(ComponentLocalLocation.PROPERTY_TID).as(Long.class), condition.getTid()));
        }
	}
	private void tryAddAccountIdPredicate(List<Predicate> predicateList, Root<ComponentLocalLocation> root, CriteriaBuilder cb){

		if (null != condition.getAccountId() ) {
			predicateList.add(cb.equal(root.get(ComponentLocalLocation.PROPERTY_ACCOUNT_ID).as(Long.class), condition.getAccountId()));
		}

		if (null != condition.getAccountIdMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ComponentLocalLocation.PROPERTY_ACCOUNT_ID).as(Long.class), condition.getAccountIdMax()));
		}

		if (null != condition.getAccountIdMin() ) {
			predicateList.add(cb.lessThan(root.get(ComponentLocalLocation.PROPERTY_ACCOUNT_ID).as(Long.class), condition.getAccountIdMin()));
		}
	}
	private void tryAddComponentPredicate(List<Predicate> predicateList, Root<ComponentLocalLocation> root, CriteriaBuilder cb){
	    if (null != condition.getComponent() ) {
            predicateList.add(cb.equal(root.get(ComponentLocalLocation.PROPERTY_COMPONENT).as(String.class), condition.getComponent()));
        }  
	}
	private void tryAddLocationPredicate(List<Predicate> predicateList, Root<ComponentLocalLocation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getLocation())){
			predicateList.add(cb.like(root.get(ComponentLocalLocation.PROPERTY_LOCATION).as(String.class), "%"+condition.getLocation()+"%"));
		}
	}
}


