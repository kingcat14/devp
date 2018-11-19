package net.aicoder.speedcloud.business.deployscheme.dao;

import net.aicoder.speedcloud.business.deployscheme.domain.Resource;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ResourceSpecification implements Specification<Resource>{

	private ResourceCondition condition;

	public ResourceSpecification(ResourceCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Resource> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddAliasPredicate(predicateList, root, cb);
		tryAddCategoryPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddNotesPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddVersionPredicate(predicateList, root, cb);
		tryAddSeqPredicate(predicateList, root, cb);
		tryAddEvnPredicate(predicateList, root, cb);
		tryAddStatusPredicate(predicateList, root, cb);
		tryAddProjectPredicate(predicateList, root, cb);
		tryAddOuterResourcePredicate(predicateList, root, cb);
		tryAddSchemePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(Resource.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Resource.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(Resource.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddAliasPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAlias())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_ALIAS).as(String.class), "%"+condition.getAlias()+"%"));
		}
	}
	private void tryAddCategoryPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
	    if (null != condition.getCategory() ) {
            predicateList.add(cb.equal(root.get(Resource.PROPERTY_CATEGORY).as(Long.class), condition.getCategory()));
        }
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
	    if (null != condition.getType() ) {
            predicateList.add(cb.equal(root.get(Resource.PROPERTY_TYPE).as(Long.class), condition.getType()));
        }
	}
	private void tryAddNotesPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getNotes())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_NOTES).as(String.class), "%"+condition.getNotes()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddVersionPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getVersion())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_VERSION).as(String.class), "%"+condition.getVersion()+"%"));
		}
	}
	private void tryAddSeqPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){

		if (null != condition.getSeq() ) {
			predicateList.add(cb.equal(root.get(Resource.PROPERTY_SEQ).as(Integer.class), condition.getSeq()));
		}

		if (null != condition.getSeqMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Resource.PROPERTY_SEQ).as(Integer.class), condition.getSeqMax()));
		}

		if (null != condition.getSeqMin() ) {
			predicateList.add(cb.lessThan(root.get(Resource.PROPERTY_SEQ).as(Integer.class), condition.getSeqMin()));
		}
	}
	private void tryAddEvnPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
	    if (null != condition.getEvn() ) {
            predicateList.add(cb.equal(root.get(Resource.PROPERTY_EVN).as(Long.class), condition.getEvn()));
        }
	}
	private void tryAddStatusPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getStatus())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_STATUS).as(String.class), "%"+condition.getStatus()+"%"));
		}
	}
	private void tryAddProjectPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
	    if (null != condition.getProject() ) {
            predicateList.add(cb.equal(root.get(Resource.PROPERTY_PROJECT).as(Long.class), condition.getProject()));
        }
	}
	private void tryAddOuterResourcePredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if (null != condition.getOuterResource() ) {
			predicateList.add(cb.equal(root.get(Resource.PROPERTY_OUTER_RESOURCE).as(Boolean.class), condition.getOuterResource()));
		}
	}
	private void tryAddSchemePredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
	    if (null != condition.getScheme() ) {
            predicateList.add(cb.equal(root.get(Resource.PROPERTY_SCHEME).as(Long.class), condition.getScheme()));
        }
	}
}


