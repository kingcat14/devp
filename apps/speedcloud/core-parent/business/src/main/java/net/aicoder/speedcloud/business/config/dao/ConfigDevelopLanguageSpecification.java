package net.aicoder.speedcloud.business.config.dao;

import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageCondition;
import net.aicoder.speedcloud.business.config.domain.ConfigDevelopLanguage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ConfigDevelopLanguageSpecification implements Specification<ConfigDevelopLanguage>{

	private ConfigDevelopLanguageCondition condition;

	public ConfigDevelopLanguageSpecification(ConfigDevelopLanguageCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ConfigDevelopLanguage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddNamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<ConfigDevelopLanguage> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(ConfigDevelopLanguage.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
}


