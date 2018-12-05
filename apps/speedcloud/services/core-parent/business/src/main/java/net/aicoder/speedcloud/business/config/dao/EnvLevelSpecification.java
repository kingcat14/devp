package net.aicoder.speedcloud.business.config.dao;

import net.aicoder.speedcloud.business.config.domain.EnvLevel;
import net.aicoder.speedcloud.business.config.dto.EnvLevelCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EnvLevelSpecification implements Specification<EnvLevel>{

	private EnvLevelCondition condition;

	public EnvLevelSpecification(EnvLevelCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<EnvLevel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<EnvLevel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(EnvLevel.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<EnvLevel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(EnvLevel.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<EnvLevel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(EnvLevel.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
}


