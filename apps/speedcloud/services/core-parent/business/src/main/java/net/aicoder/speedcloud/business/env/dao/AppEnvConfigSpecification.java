package net.aicoder.speedcloud.business.env.dao;

import net.aicoder.speedcloud.business.env.domain.AppEnvConfig;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AppEnvConfigSpecification implements Specification<AppEnvConfig>{

	private AppEnvConfigCondition condition;

	public AppEnvConfigSpecification(AppEnvConfigCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AppEnvConfig> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddLevelPredicate(predicateList, root, cb);
		tryAddProjectPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<AppEnvConfig> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(AppEnvConfig.PROPERTY_TID).as(Long.class), condition.getTid()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<AppEnvConfig> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(AppEnvConfig.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddLevelPredicate(List<Predicate> predicateList, Root<AppEnvConfig> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getLevel())){
			predicateList.add(cb.like(root.get(AppEnvConfig.PROPERTY_LEVEL).as(String.class), "%"+condition.getLevel()+"%"));
		}
	}
	private void tryAddProjectPredicate(List<Predicate> predicateList, Root<AppEnvConfig> root, CriteriaBuilder cb){
	    if (null != condition.getProject() ) {
            predicateList.add(cb.equal(root.get(AppEnvConfig.PROPERTY_PROJECT).as(Long.class), condition.getProject()));
        }
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<AppEnvConfig> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(AppEnvConfig.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(AppEnvConfig.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(AppEnvConfig.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
}


