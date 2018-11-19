package net.aicoder.speedcloud.business.app.dao;

import net.aicoder.speedcloud.business.app.domain.CodeRepository;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CodeRepositorySpecification implements Specification<CodeRepository>{

	private CodeRepositoryCondition condition;

	public CodeRepositorySpecification(CodeRepositoryCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<CodeRepository> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddUrlPredicate(predicateList, root, cb);
		tryAddDevelopTypePredicate(predicateList, root, cb);
		tryAddUsernamePredicate(predicateList, root, cb);
		tryAddPasswordPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<CodeRepository> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(CodeRepository.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(CodeRepository.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(CodeRepository.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<CodeRepository> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(CodeRepository.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<CodeRepository> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.equal(root.get(CodeRepository.PROPERTY_TYPE).as(String.class), condition.getType()));
		}
	}
	private void tryAddUrlPredicate(List<Predicate> predicateList, Root<CodeRepository> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUrl())){
			predicateList.add(cb.like(root.get(CodeRepository.PROPERTY_URL).as(String.class), "%"+condition.getUrl()+"%"));
		}
	}
	private void tryAddDevelopTypePredicate(List<Predicate> predicateList, Root<CodeRepository> root, CriteriaBuilder cb){
	    if (null != condition.getDevelopType() ) {
            predicateList.add(cb.equal(root.get(CodeRepository.PROPERTY_DEVELOP_TYPE).as(Long.class), condition.getDevelopType()));
        }
	}
	private void tryAddUsernamePredicate(List<Predicate> predicateList, Root<CodeRepository> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUsername())){
			predicateList.add(cb.like(root.get(CodeRepository.PROPERTY_USERNAME).as(String.class), "%"+condition.getUsername()+"%"));
		}
	}
	private void tryAddPasswordPredicate(List<Predicate> predicateList, Root<CodeRepository> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getPassword())){
			predicateList.add(cb.like(root.get(CodeRepository.PROPERTY_PASSWORD).as(String.class), "%"+condition.getPassword()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<CodeRepository> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(CodeRepository.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
}


