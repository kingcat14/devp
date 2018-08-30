package com.kingzoo.kingcat.project.icode4.business.system.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelCondition;
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
 * 领域根对象的数据库操作
 * @author icode
 */
@Repository("domainModelDao")
public class DomainModelDao extends SimpleJpaRepository<DomainModel, String, DomainModelCondition> {

	@Override
	public Specification<DomainModel> buildQuery(final DomainModelCondition condition){

		return new DomainModelSpecification(condition);
	}
}

class DomainModelSpecification implements Specification<DomainModel>{

	DomainModelCondition condition;

	public DomainModelSpecification(DomainModelCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<DomainModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddViewIndexPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddModulePredicate(predicateList, root, cb);
		tryAddSystemPredicate(predicateList, root, cb);
		tryAddPersistPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<DomainModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(DomainModel.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<DomainModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(DomainModel.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddViewIndexPredicate(List<Predicate> predicateList, Root<DomainModel> root, CriteriaBuilder cb){
		if (null != condition.getViewIndex() ) {
			predicateList.add(cb.equal(root.get(DomainModel.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndex()));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<DomainModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(DomainModel.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddModulePredicate(List<Predicate> predicateList, Root<DomainModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModuleId())){
			predicateList.add(cb.equal(root.get(DomainModel.PROPERTY_MODULE_ID).as(String.class), condition.getModuleId()));
		}
	}
	private void tryAddSystemPredicate(List<Predicate> predicateList, Root<DomainModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSystemId())){
			predicateList.add(cb.equal(root.get(DomainModel.PROPERTY_SYSTEM_ID).as(String.class), condition.getSystemId()));
		}
	}
	private void tryAddPersistPredicate(List<Predicate> predicateList, Root<DomainModel> root, CriteriaBuilder cb){
		if (null != condition.getPersist() ) {
			predicateList.add(cb.equal(root.get(DomainModel.PROPERTY_PERSIST).as(Boolean.class), condition.getPersist()));
		}
	}
}

