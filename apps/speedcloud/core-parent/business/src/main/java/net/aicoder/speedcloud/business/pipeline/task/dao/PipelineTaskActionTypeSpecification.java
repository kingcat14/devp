package net.aicoder.speedcloud.business.pipeline.task.dao;

import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypeCondition;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class PipelineTaskActionTypeSpecification implements Specification<PipelineTaskActionType>{

	private PipelineTaskActionTypeCondition condition;

	public PipelineTaskActionTypeSpecification(PipelineTaskActionTypeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineTaskActionType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddViewOrderPredicate(predicateList, root, cb);
		tryAddMemoPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddContentPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<PipelineTaskActionType> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(PipelineTaskActionType.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineTaskActionType.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineTaskActionType.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<PipelineTaskActionType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(PipelineTaskActionType.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PipelineTaskActionType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PipelineTaskActionType.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddViewOrderPredicate(List<Predicate> predicateList, Root<PipelineTaskActionType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getViewOrder())){
			predicateList.add(cb.like(root.get(PipelineTaskActionType.PROPERTY_VIEW_ORDER).as(String.class), "%"+condition.getViewOrder()+"%"));
		}
	}
	private void tryAddMemoPredicate(List<Predicate> predicateList, Root<PipelineTaskActionType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMemo())){
			predicateList.add(cb.like(root.get(PipelineTaskActionType.PROPERTY_MEMO).as(String.class), "%"+condition.getMemo()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<PipelineTaskActionType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(PipelineTaskActionType.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddContentPredicate(List<Predicate> predicateList, Root<PipelineTaskActionType> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getContent())){
			predicateList.add(cb.like(root.get(PipelineTaskActionType.PROPERTY_CONTENT).as(String.class), "%"+condition.getContent()+"%"));
		}
	}
}


