package net.aicoder.speedcloud.business.config.dao;

import net.aicoder.speedcloud.business.config.domain.CodeRepositoryType;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CodeRepositoryTypeSpecification implements Specification<CodeRepositoryType>{

	private CodeRepositoryTypeCondition condition;

	public CodeRepositoryTypeSpecification(CodeRepositoryTypeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<CodeRepositoryType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddCodePredicate(List<Predicate> predicateList, Root<CodeRepositoryType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(CodeRepositoryType.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<CodeRepositoryType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(CodeRepositoryType.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
}


