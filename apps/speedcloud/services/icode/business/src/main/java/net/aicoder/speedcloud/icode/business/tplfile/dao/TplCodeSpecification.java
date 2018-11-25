package net.aicoder.speedcloud.icode.business.tplfile.dao;

import net.aicoder.speedcloud.icode.business.tplfile.domain.TplCode;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TplCodeSpecification implements Specification<TplCode>{

	private TplCodeCondition condition;

	public TplCodeSpecification(TplCodeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<TplCode> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddContentPredicate(predicateList, root, cb);
		tryAddFileNamePredicate(predicateList, root, cb);
		tryAddFilePathPredicate(predicateList, root, cb);
		tryAddTplSetPredicate(predicateList, root, cb);
		tryAddAcceptModelTypePredicate(predicateList, root, cb);
		tryAddOverridablePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(TplCode.PROPERTY_TID).as(Long.class), condition.getTid()));
        }
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(TplCode.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(TplCode.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(TplCode.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddContentPredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getContent())){
			predicateList.add(cb.like(root.get(TplCode.PROPERTY_CONTENT).as(String.class), "%"+condition.getContent()+"%"));
		}
	}
	private void tryAddFileNamePredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFileName())){
			predicateList.add(cb.like(root.get(TplCode.PROPERTY_FILE_NAME).as(String.class), "%"+condition.getFileName()+"%"));
		}
	}
	private void tryAddFilePathPredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFilePath())){
			predicateList.add(cb.like(root.get(TplCode.PROPERTY_FILE_PATH).as(String.class), "%"+condition.getFilePath()+"%"));
		}
	}
	private void tryAddTplSetPredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTplSet())){
			predicateList.add(cb.like(root.get(TplCode.PROPERTY_TPL_SET).as(String.class), "%"+condition.getTplSet()+"%"));
		}
	}
	private void tryAddAcceptModelTypePredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAcceptModelType())){
			predicateList.add(cb.like(root.get(TplCode.PROPERTY_ACCEPT_MODEL_TYPE).as(String.class), "%"+condition.getAcceptModelType()+"%"));
		}
	}
	private void tryAddOverridablePredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if (null != condition.getOverridable() ) {
			predicateList.add(cb.equal(root.get(TplCode.PROPERTY_OVERRIDABLE).as(Boolean.class), condition.getOverridable()));
		}
	}
}


