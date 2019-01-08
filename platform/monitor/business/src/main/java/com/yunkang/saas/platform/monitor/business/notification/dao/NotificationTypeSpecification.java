package com.yunkang.saas.platform.monitor.business.notification.dao;

import com.yunkang.saas.platform.monitor.business.notification.domain.NotificationType;
import com.yunkang.saas.platform.monitor.business.notification.dto.NotificationTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class NotificationTypeSpecification implements Specification<NotificationType>{

	private NotificationTypeCondition condition;

	public NotificationTypeSpecification(NotificationTypeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<NotificationType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<NotificationType> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(NotificationType.PROPERTY_TID).as(Long.class), condition.getTid()));
        }  
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<NotificationType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(NotificationType.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
}

