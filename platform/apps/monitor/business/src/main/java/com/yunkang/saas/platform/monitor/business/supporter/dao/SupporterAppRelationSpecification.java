package com.yunkang.saas.platform.monitor.business.supporter.dao;

import com.yunkang.saas.platform.monitor.business.supporter.domain.SupporterAppRelation;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterAppRelationCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SupporterAppRelationSpecification implements Specification<SupporterAppRelation>{

	private SupporterAppRelationCondition condition;

	public SupporterAppRelationSpecification(SupporterAppRelationCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<SupporterAppRelation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddSupporterPredicate(predicateList, root, cb);
		tryAddApplicationPredicate(predicateList, root, cb);
		tryAddNotificationTypePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<SupporterAppRelation> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(SupporterAppRelation.PROPERTY_TID).as(Long.class), condition.getTid()));
        }  
	}
	private void tryAddSupporterPredicate(List<Predicate> predicateList, Root<SupporterAppRelation> root, CriteriaBuilder cb){
	    if (null != condition.getSupporter() ) {
            predicateList.add(cb.equal(root.get(SupporterAppRelation.PROPERTY_SUPPORTER).as(String.class), condition.getSupporter()));
        }
	}
	private void tryAddApplicationPredicate(List<Predicate> predicateList, Root<SupporterAppRelation> root, CriteriaBuilder cb){
	    if (null != condition.getApplication() ) {
            predicateList.add(cb.equal(root.get(SupporterAppRelation.PROPERTY_APPLICATION).as(String.class), condition.getApplication()));
        }
	}
	private void tryAddNotificationTypePredicate(List<Predicate> predicateList, Root<SupporterAppRelation> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotificationType())){
			predicateList.add(cb.like(root.get(SupporterAppRelation.PROPERTY_NOTIFICATION_TYPE).as(String.class), "%"+condition.getNotificationType()+"%"));
		}
	}
}


