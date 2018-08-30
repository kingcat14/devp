package net.aicoder.speedcloud.business.app.dao;

import net.aicoder.speedcloud.business.app.dto.CodeRepertoryCondition;
import net.aicoder.speedcloud.business.app.domain.CodeRepertory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class CodeRepertorySpecification implements Specification<CodeRepertory>{

	private CodeRepertoryCondition condition;

	public CodeRepertorySpecification(CodeRepertoryCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<CodeRepertory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddUrlPredicate(predicateList, root, cb);
		tryAddDevelopTypePredicate(predicateList, root, cb);
		tryAddUsernamePredicate(predicateList, root, cb);
		tryAddPasswordPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<CodeRepertory> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(CodeRepertory.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(CodeRepertory.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(CodeRepertory.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<CodeRepertory> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.equal(root.get(CodeRepertory.PROPERTY_TYPE).as(String.class), condition.getType()));
		}
	}
	private void tryAddUrlPredicate(List<Predicate> predicateList, Root<CodeRepertory> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUrl())){
			predicateList.add(cb.like(root.get(CodeRepertory.PROPERTY_URL).as(String.class), "%"+condition.getUrl()+"%"));
		}
	}
	private void tryAddDevelopTypePredicate(List<Predicate> predicateList, Root<CodeRepertory> root, CriteriaBuilder cb){
	    if (null != condition.getDevelopType() ) {
            predicateList.add(cb.equal(root.get(CodeRepertory.PROPERTY_DEVELOP_TYPE).as(Long.class), condition.getDevelopType()));
        }
	}
	private void tryAddUsernamePredicate(List<Predicate> predicateList, Root<CodeRepertory> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUsername())){
			predicateList.add(cb.like(root.get(CodeRepertory.PROPERTY_USERNAME).as(String.class), "%"+condition.getUsername()+"%"));
		}
	}
	private void tryAddPasswordPredicate(List<Predicate> predicateList, Root<CodeRepertory> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPassword())){
			predicateList.add(cb.like(root.get(CodeRepertory.PROPERTY_PASSWORD).as(String.class), "%"+condition.getPassword()+"%"));
		}
	}
}


