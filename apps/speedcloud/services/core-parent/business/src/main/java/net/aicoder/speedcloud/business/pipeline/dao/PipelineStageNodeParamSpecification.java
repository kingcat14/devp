package net.aicoder.speedcloud.business.pipeline.dao;

import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNodeParam;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PipelineStageNodeParamSpecification implements Specification<PipelineStageNodeParam>{

	private PipelineStageNodeParamCondition condition;

	public PipelineStageNodeParamSpecification(PipelineStageNodeParamCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<PipelineStageNodeParam> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddValuePredicate(predicateList, root, cb);
		tryAddViewOrderPredicate(predicateList, root, cb);
		tryAddPipelineStageNodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<PipelineStageNodeParam> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(PipelineStageNodeParam.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<PipelineStageNodeParam> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(PipelineStageNodeParam.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddValuePredicate(List<Predicate> predicateList, Root<PipelineStageNodeParam> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getValue())){
			predicateList.add(cb.like(root.get(PipelineStageNodeParam.PROPERTY_VALUE).as(String.class), "%"+condition.getValue()+"%"));
		}
	}
	private void tryAddViewOrderPredicate(List<Predicate> predicateList, Root<PipelineStageNodeParam> root, CriteriaBuilder cb){

		if (null != condition.getViewOrder() ) {
			predicateList.add(cb.equal(root.get(PipelineStageNodeParam.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrder()));
		}

		if (null != condition.getViewOrderMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(PipelineStageNodeParam.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrderMax()));
		}

		if (null != condition.getViewOrderMin() ) {
			predicateList.add(cb.lessThan(root.get(PipelineStageNodeParam.PROPERTY_VIEW_ORDER).as(Integer.class), condition.getViewOrderMin()));
		}
	}
	private void tryAddPipelineStageNodePredicate(List<Predicate> predicateList, Root<PipelineStageNodeParam> root, CriteriaBuilder cb){
	    if (null != condition.getPipelineStageNode() ) {
            predicateList.add(cb.equal(root.get(PipelineStageNodeParam.PROPERTY_PIPELINE_STAGE_NODE).as(Long.class), condition.getPipelineStageNode()));
        }
	}
}


