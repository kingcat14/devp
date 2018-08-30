package net.aicoder.speedcloud.business.config.dao;

import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionCondition;
import net.aicoder.speedcloud.business.config.domain.ConfigDevelopLanguageVersion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ConfigDevelopLanguageVersionSpecification implements Specification<ConfigDevelopLanguageVersion>{

	private ConfigDevelopLanguageVersionCondition condition;

	public ConfigDevelopLanguageVersionSpecification(ConfigDevelopLanguageVersionCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ConfigDevelopLanguageVersion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddNamePredicate(predicateList, root, cb);
		tryAddLanguagePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<ConfigDevelopLanguageVersion> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(ConfigDevelopLanguageVersion.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddLanguagePredicate(List<Predicate> predicateList, Root<ConfigDevelopLanguageVersion> root, CriteriaBuilder cb){
	    if (null != condition.getLanguage() ) {
            predicateList.add(cb.equal(root.get(ConfigDevelopLanguageVersion.PROPERTY_LANGUAGE).as(Long.class), condition.getLanguage()));
        }
	}
}


