package net.aicoder.speedcloud.business.app.dao;

import net.aicoder.speedcloud.business.app.domain.AppBaseInfo;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AppBaseInfoSpecification implements Specification<AppBaseInfo>{

	private AppBaseInfoCondition condition;

	public AppBaseInfoSpecification(AppBaseInfoCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<AppBaseInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddRegistTimePredicate(predicateList, root, cb);
		tryAddProjectPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<AppBaseInfo> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(AppBaseInfo.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<AppBaseInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(AppBaseInfo.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<AppBaseInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(AppBaseInfo.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<AppBaseInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(AppBaseInfo.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<AppBaseInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(AppBaseInfo.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddRegistTimePredicate(List<Predicate> predicateList, Root<AppBaseInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getRegistTime())){
			predicateList.add(cb.like(root.get(AppBaseInfo.PROPERTY_REGIST_TIME).as(String.class), "%"+condition.getRegistTime()+"%"));
		}
	}
	private void tryAddProjectPredicate(List<Predicate> predicateList, Root<AppBaseInfo> root, CriteriaBuilder cb){
	    if (null != condition.getProject() ) {
            predicateList.add(cb.equal(root.get(AppBaseInfo.PROPERTY_PROJECT).as(Long.class), condition.getProject()));
        }
	}
}


