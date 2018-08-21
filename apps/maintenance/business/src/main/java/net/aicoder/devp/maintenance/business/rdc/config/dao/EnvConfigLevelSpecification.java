package net.aicoder.devp.maintenance.business.rdc.config.dao;

import net.aicoder.devp.maintenance.business.rdc.config.domain.EnvConfigLevel;
import net.aicoder.devp.maintenance.business.rdc.config.dto.EnvConfigLevelCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EnvConfigLevelSpecification implements Specification<EnvConfigLevel>{

	private EnvConfigLevelCondition condition;

	public EnvConfigLevelSpecification(EnvConfigLevelCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<EnvConfigLevel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<EnvConfigLevel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(EnvConfigLevel.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<EnvConfigLevel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(EnvConfigLevel.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<EnvConfigLevel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(EnvConfigLevel.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
}


