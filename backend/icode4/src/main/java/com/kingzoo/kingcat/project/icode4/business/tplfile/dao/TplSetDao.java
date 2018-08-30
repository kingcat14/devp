package com.kingzoo.kingcat.project.icode4.business.tplfile.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplSet;
import com.kingzoo.kingcat.project.icode4.business.tplfile.vo.TplSetCondition;
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
 * 模板集合的数据库操作
 * @author icode
 */
@Repository("tplSetDao")
public class TplSetDao extends SimpleJpaRepository<TplSet, String, TplSetCondition> {

	@Override
	public Specification<TplSet> buildQuery(final TplSetCondition condition){

		return new TplSetSpecification(condition);
	}
}

class TplSetSpecification implements Specification<TplSet>{

	TplSetCondition condition;

	public TplSetSpecification(TplSetCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<TplSet> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddParentPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<TplSet> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(TplSet.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<TplSet> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.equal(root.get(TplSet.PROPERTY_CODE).as(String.class), condition.getCode()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<TplSet> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(TplSet.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddParentPredicate(List<Predicate> predicateList, Root<TplSet> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getParentId())){
			predicateList.add(cb.equal(root.get(TplSet.PROPERTY_PARENT_ID).as(String.class), condition.getParentId()));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<TplSet> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(TplSet.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
}

