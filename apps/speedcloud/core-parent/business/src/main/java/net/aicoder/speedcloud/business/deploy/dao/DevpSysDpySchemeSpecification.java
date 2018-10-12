package net.aicoder.speedcloud.business.deploy.dao;

import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpySchemeCondition;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyScheme;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class DevpSysDpySchemeSpecification implements Specification<DevpSysDpyScheme>{

	private DevpSysDpySchemeCondition condition;

	public DevpSysDpySchemeSpecification(DevpSysDpySchemeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DevpSysDpyScheme> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddAliasPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddVersionPredicate(predicateList, root, cb);
		tryAddVerPostfixPredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);
		tryAddProjectPredicate(predicateList, root, cb);
		tryAddEnvPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<DevpSysDpyScheme> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyScheme.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(DevpSysDpyScheme.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(DevpSysDpyScheme.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DevpSysDpyScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DevpSysDpyScheme.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DevpSysDpyScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DevpSysDpyScheme.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<DevpSysDpyScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(DevpSysDpyScheme.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DevpSysDpyScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DevpSysDpyScheme.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<DevpSysDpyScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(DevpSysDpyScheme.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddVersionPredicate(List<Predicate> predicateList, Root<DevpSysDpyScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getVersion())){
			predicateList.add(cb.like(root.get(DevpSysDpyScheme.PROPERTY_VERSION).as(String.class), "%"+condition.getVersion()+"%"));
		}
	}
	private void tryAddVerPostfixPredicate(List<Predicate> predicateList, Root<DevpSysDpyScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getVerPostfix())){
			predicateList.add(cb.like(root.get(DevpSysDpyScheme.PROPERTY_VER_POSTFIX).as(String.class), "%"+condition.getVerPostfix()+"%"));
		}
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<DevpSysDpyScheme> root, CriteriaBuilder cb){
		if (null != condition.getStatus() ) {
			predicateList.add(cb.equal(root.get(DevpSysDpyScheme.PROPERTY_STATUS).as(Boolean.class), condition.getStatus()));
		}
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<DevpSysDpyScheme> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(DevpSysDpyScheme.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddProjectPredicate(List<Predicate> predicateList, Root<DevpSysDpyScheme> root, CriteriaBuilder cb){
	    if (null != condition.getProject() ) {
            predicateList.add(cb.equal(root.get(DevpSysDpyScheme.PROPERTY_PROJECT).as(Long.class), condition.getProject()));
        }
	}
	private void tryAddEnvPredicate(List<Predicate> predicateList, Root<DevpSysDpyScheme> root, CriteriaBuilder cb){
	    if (null != condition.getEnv() ) {
            predicateList.add(cb.equal(root.get(DevpSysDpyScheme.PROPERTY_ENV).as(Long.class), condition.getEnv()));
        }
	}
}


