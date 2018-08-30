package com.kingzoo.kingcat.project.icode4.business.system.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.system.domain.ModelType;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ModelTypeCondition;
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
 * 对象类型的数据库操作
 * @author icode
 */
@Repository("modelTypeDao")
public class ModelTypeDao extends SimpleJpaRepository<ModelType, String, ModelTypeCondition> {

	@Override
	public Specification<ModelType> buildQuery(final ModelTypeCondition condition){

		return new ModelTypeSpecification(condition);
	}
}

class ModelTypeSpecification implements Specification<ModelType>{

	ModelTypeCondition condition;

	public ModelTypeSpecification(ModelTypeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ModelType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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


	private void tryAddCodePredicate(List<Predicate> predicateList, Root<ModelType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(ModelType.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<ModelType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(ModelType.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
}

