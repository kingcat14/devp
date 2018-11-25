package net.aicoder.speedcloud.icode.business.domain.dao;

import net.aicoder.speedcloud.icode.business.domain.domain.ModelType;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ModelTypeSpecification implements Specification<ModelType>{

	private ModelTypeCondition condition;

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
		tryAddIdxPredicate(predicateList, root, cb);


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
	private void tryAddIdxPredicate(List<Predicate> predicateList, Root<ModelType> root, CriteriaBuilder cb){

		if (null != condition.getIdx() ) {
			predicateList.add(cb.equal(root.get(ModelType.PROPERTY_IDX).as(Integer.class), condition.getIdx()));
		}

		if (null != condition.getIdxMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(ModelType.PROPERTY_IDX).as(Integer.class), condition.getIdxMax()));
		}

		if (null != condition.getIdxMin() ) {
			predicateList.add(cb.lessThan(root.get(ModelType.PROPERTY_IDX).as(Integer.class), condition.getIdxMin()));
		}
	}
}


