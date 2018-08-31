package net.aicoder.speedcloud.business.app.dao;

import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoCondition;
import net.aicoder.speedcloud.business.app.domain.CodeBaseInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class CodeBaseInfoSpecification implements Specification<CodeBaseInfo>{

	private CodeBaseInfoCondition condition;

	public CodeBaseInfoSpecification(CodeBaseInfoCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<CodeBaseInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddCodeRepositoryPredicate(predicateList, root, cb);
		tryAddLanguagePredicate(predicateList, root, cb);
		tryAddLanguageLevelPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<CodeBaseInfo> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(CodeBaseInfo.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(CodeBaseInfo.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(CodeBaseInfo.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddCodeRepositoryPredicate(List<Predicate> predicateList, Root<CodeBaseInfo> root, CriteriaBuilder cb){
	    if (null != condition.getCodeRepository() ) {
            predicateList.add(cb.equal(root.get(CodeBaseInfo.PROPERTY_CODE_REPERTORY).as(Long.class), condition.getCodeRepository()));
        }
	}
	private void tryAddLanguagePredicate(List<Predicate> predicateList, Root<CodeBaseInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getLanguage())){
			predicateList.add(cb.like(root.get(CodeBaseInfo.PROPERTY_LANGUAGE).as(String.class), "%"+condition.getLanguage()+"%"));
		}
	}
	private void tryAddLanguageLevelPredicate(List<Predicate> predicateList, Root<CodeBaseInfo> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getLanguageLevel())){
			predicateList.add(cb.like(root.get(CodeBaseInfo.PROPERTY_LANGUAGE_LEVEL).as(String.class), "%"+condition.getLanguageLevel()+"%"));
		}
	}
}


