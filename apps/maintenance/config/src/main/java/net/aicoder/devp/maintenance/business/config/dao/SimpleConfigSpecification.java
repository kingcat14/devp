package net.aicoder.devp.maintenance.business.config.dao;

import net.aicoder.devp.maintenance.business.config.dto.SimpleConfigCondition;
import net.aicoder.devp.maintenance.business.config.domain.SimpleConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class SimpleConfigSpecification implements Specification<SimpleConfig>{

	private SimpleConfigCondition condition;

	public SimpleConfigSpecification(SimpleConfigCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<SimpleConfig> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddConfigTypePredicate(predicateList, root, cb);
		tryAddDisplayNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<SimpleConfig> root, CriteriaBuilder cb){
		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(SimpleConfig.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddConfigTypePredicate(List<Predicate> predicateList, Root<SimpleConfig> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getConfigType())){
			predicateList.add(cb.like(root.get(SimpleConfig.PROPERTY_CONFIG_TYPE).as(String.class), "%"+condition.getConfigType()+"%"));
		}
	}
	private void tryAddDisplayNamePredicate(List<Predicate> predicateList, Root<SimpleConfig> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDisplayName())){
			predicateList.add(cb.like(root.get(SimpleConfig.PROPERTY_DISPLAY_NAME).as(String.class), "%"+condition.getDisplayName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<SimpleConfig> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(SimpleConfig.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
}


