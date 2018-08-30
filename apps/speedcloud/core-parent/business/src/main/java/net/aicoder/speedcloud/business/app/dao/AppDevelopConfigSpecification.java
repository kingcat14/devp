package net.aicoder.speedcloud.business.app.dao;

import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigCondition;
import net.aicoder.speedcloud.business.app.domain.AppDevelopConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class AppDevelopConfigSpecification implements Specification<AppDevelopConfig>{

	private AppDevelopConfigCondition condition;

	public AppDevelopConfigSpecification(AppDevelopConfigCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AppDevelopConfig> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddAppPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddTestDatabasePredicate(predicateList, root, cb);
		tryAddTestDomainNamePredicate(predicateList, root, cb);
		tryAddProductionDatabasePredicate(predicateList, root, cb);
		tryAddProductionDomainNamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<AppDevelopConfig> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(AppDevelopConfig.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AppDevelopConfig.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(AppDevelopConfig.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddAppPredicate(List<Predicate> predicateList, Root<AppDevelopConfig> root, CriteriaBuilder cb){
	    if (null != condition.getApp() ) {
            predicateList.add(cb.equal(root.get(AppDevelopConfig.PROPERTY_APP).as(Long.class), condition.getApp()));
        }
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<AppDevelopConfig> root, CriteriaBuilder cb){
	    if (null != condition.getCode() ) {
            predicateList.add(cb.equal(root.get(AppDevelopConfig.PROPERTY_CODE).as(Long.class), condition.getCode()));
        }
	}
	private void tryAddTestDatabasePredicate(List<Predicate> predicateList, Root<AppDevelopConfig> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTestDatabase())){
			predicateList.add(cb.like(root.get(AppDevelopConfig.PROPERTY_TEST_DATABASE).as(String.class), "%"+condition.getTestDatabase()+"%"));
		}
	}
	private void tryAddTestDomainNamePredicate(List<Predicate> predicateList, Root<AppDevelopConfig> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTestDomainName())){
			predicateList.add(cb.like(root.get(AppDevelopConfig.PROPERTY_TEST_DOMAIN_NAME).as(String.class), "%"+condition.getTestDomainName()+"%"));
		}
	}
	private void tryAddProductionDatabasePredicate(List<Predicate> predicateList, Root<AppDevelopConfig> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getProductionDatabase())){
			predicateList.add(cb.like(root.get(AppDevelopConfig.PROPERTY_PRODUCTION_DATABASE).as(String.class), "%"+condition.getProductionDatabase()+"%"));
		}
	}
	private void tryAddProductionDomainNamePredicate(List<Predicate> predicateList, Root<AppDevelopConfig> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getProductionDomainName())){
			predicateList.add(cb.like(root.get(AppDevelopConfig.PROPERTY_PRODUCTION_DOMAIN_NAME).as(String.class), "%"+condition.getProductionDomainName()+"%"));
		}
	}
}


