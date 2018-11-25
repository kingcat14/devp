package net.aicoder.speedcloud.icode.business.domain.dao;

import net.aicoder.speedcloud.icode.business.domain.domain.Domain;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DomainSpecification implements Specification<Domain>{

	private DomainCondition condition;

	public DomainSpecification(DomainCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Domain> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddParentPredicate(predicateList, root, cb);
		tryAddPrefixPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<Domain> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(Domain.PROPERTY_TID).as(Long.class), condition.getTid()));
        }
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Domain> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Domain.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<Domain> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(Domain.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddParentPredicate(List<Predicate> predicateList, Root<Domain> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getParent())){
			predicateList.add(cb.like(root.get(Domain.PROPERTY_PARENT).as(String.class), "%"+condition.getParent()+"%"));
		}
	}
	private void tryAddPrefixPredicate(List<Predicate> predicateList, Root<Domain> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPrefix())){
			predicateList.add(cb.like(root.get(Domain.PROPERTY_PREFIX).as(String.class), "%"+condition.getPrefix()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<Domain> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(Domain.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
}


