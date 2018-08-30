package com.kingzoo.kingcat.project.icode4.business.microservice.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModel;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelCondition;
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
 * 传输对象的数据库操作
 * @author icode
 */
@Repository("transModelDao")
public class TransModelDao extends SimpleJpaRepository<TransModel, String, TransModelCondition> {

	@Override
	public Specification<TransModel> buildQuery(final TransModelCondition condition){

		return new TransModelSpecification(condition);
	}
}

class TransModelSpecification implements Specification<TransModel>{

	TransModelCondition condition;

	public TransModelSpecification(TransModelCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<TransModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddMemoPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddModulePredicate(predicateList, root, cb);
		tryAddDomainModelPredicate(predicateList, root, cb);
		tryAddViewIndexPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<TransModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(TransModel.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<TransModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(TransModel.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddMemoPredicate(List<Predicate> predicateList, Root<TransModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMemo())){
			predicateList.add(cb.like(root.get(TransModel.PROPERTY_MEMO).as(String.class), "%"+condition.getMemo()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<TransModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(TransModel.PROPERTY_DESCIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddModulePredicate(List<Predicate> predicateList, Root<TransModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModuleId())){
			predicateList.add(cb.equal(root.get(TransModel.PROPERTY_MODULE_ID).as(String.class), condition.getModuleId()));
		}
	}
	private void tryAddDomainModelPredicate(List<Predicate> predicateList, Root<TransModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDomainModelId())){
			predicateList.add(cb.equal(root.get(TransModel.PROPERTY_DOMAIN_MODEL_ID).as(String.class), condition.getDomainModelId()));
		}
	}
	private void tryAddViewIndexPredicate(List<Predicate> predicateList, Root<TransModel> root, CriteriaBuilder cb){
		if (null != condition.getViewIndex() ) {
			predicateList.add(cb.equal(root.get(TransModel.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndex()));
		}
	}
}

