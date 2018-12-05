package net.aicoder.speedcloud.business.app.dao;

import net.aicoder.speedcloud.business.app.domain.AppDevelopConfig;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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
		tryAddDevelopDatabasePredicate(predicateList, root, cb);
		tryAddDevelopDomainNamePredicate(predicateList, root, cb);
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
	}
	private void tryAddAppPredicate(List<Predicate> predicateList, Root<AppDevelopConfig> root, CriteriaBuilder cb){
	    if (null != condition.getApp() ) {
            predicateList.add(cb.equal(root.get(AppDevelopConfig.PROPERTY_APP).as(String.class), condition.getApp()));
        }
	}
	private void tryAddDevelopDatabasePredicate(List<Predicate> predicateList, Root<AppDevelopConfig> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDevelopDatabase())){
			predicateList.add(cb.like(root.get(AppDevelopConfig.PROPERTY_DEVELOP_DATABASE).as(String.class), "%"+condition.getDevelopDatabase()+"%"));
		}
	}
	private void tryAddDevelopDomainNamePredicate(List<Predicate> predicateList, Root<AppDevelopConfig> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDevelopDomainName())){
			predicateList.add(cb.like(root.get(AppDevelopConfig.PROPERTY_DEVELOP_DOMAIN_NAME).as(String.class), "%"+condition.getDevelopDomainName()+"%"));
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


