package com.yunkang.saas.platform.monitor.business.supporter.dao;

import com.yunkang.saas.platform.monitor.business.supporter.domain.Supporter;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SupporterSpecification implements Specification<Supporter>{

	private SupporterCondition condition;

	public SupporterSpecification(SupporterCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Supporter> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddMobilePredicate(predicateList, root, cb);
		tryAddEmailPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<Supporter> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(Supporter.PROPERTY_TID).as(Long.class), condition.getTid()));
        }  
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Supporter> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Supporter.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddMobilePredicate(List<Predicate> predicateList, Root<Supporter> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMobile())){
			predicateList.add(cb.like(root.get(Supporter.PROPERTY_MOBILE).as(String.class), "%"+condition.getMobile()+"%"));
		}
	}
	private void tryAddEmailPredicate(List<Predicate> predicateList, Root<Supporter> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getEmail())){
			predicateList.add(cb.like(root.get(Supporter.PROPERTY_EMAIL).as(String.class), "%"+condition.getEmail()+"%"));
		}
	}
}


