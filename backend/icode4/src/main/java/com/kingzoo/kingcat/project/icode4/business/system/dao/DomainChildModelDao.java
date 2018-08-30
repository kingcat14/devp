package com.kingzoo.kingcat.project.icode4.business.system.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainChildModel;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainChildModelCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 领域子对象的数据库操作
 * @author icode
 */
@Repository("domainChildModelDao")
public class DomainChildModelDao extends SimpleJpaRepository<DomainChildModel, String, DomainChildModelCondition> {

	@Override
	public Specification<DomainChildModel> buildQuery(final DomainChildModelCondition condition){

		return new DomainChildModelSpecification(condition);
	}
}

class DomainChildModelSpecification implements Specification<DomainChildModel>{

	DomainChildModelCondition condition;

	public DomainChildModelSpecification(DomainChildModelCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DomainChildModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddViewIndexPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddDomainModelPredicate(predicateList, root, cb);
		tryAddPersistPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DomainChildModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DomainChildModel.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DomainChildModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DomainChildModel.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddViewIndexPredicate(List<Predicate> predicateList, Root<DomainChildModel> root, CriteriaBuilder cb){
		if (null != condition.getViewIndex() ) {
			predicateList.add(cb.equal(root.get(DomainChildModel.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndex()));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DomainChildModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DomainChildModel.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddDomainModelPredicate(List<Predicate> predicateList, Root<DomainChildModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDomainModelId())){
			predicateList.add(cb.equal(root.get(DomainChildModel.PROPERTY_DOMAIN_MODEL_ID).as(String.class), condition.getDomainModelId()));
		}
	}
	private void tryAddPersistPredicate(List<Predicate> predicateList, Root<DomainChildModel> root, CriteriaBuilder cb){
		if (null != condition.getPersist() ) {
			predicateList.add(cb.equal(root.get(DomainChildModel.PROPERTY_PERSIST).as(Boolean.class), condition.getPersist()));
		}
	}
}

