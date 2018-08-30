package net.aicoder.speedcloud.business.config.dao;

import net.aicoder.speedcloud.business.config.dto.DevelopTypeCondition;
import net.aicoder.speedcloud.business.config.domain.DevelopType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevelopTypeSpecification implements Specification<DevelopType>{

	private DevelopTypeCondition condition;

	public DevelopTypeSpecification(DevelopTypeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevelopType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddNamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevelopType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevelopType.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
}


