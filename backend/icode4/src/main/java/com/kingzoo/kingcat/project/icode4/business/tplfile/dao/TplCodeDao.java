package com.kingzoo.kingcat.project.icode4.business.tplfile.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplCode;
import com.kingzoo.kingcat.project.icode4.business.tplfile.vo.TplCodeCondition;
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
 * 文件模板的数据库操作
 * @author icode
 */
@Repository("tplCodeDao")
public class TplCodeDao extends SimpleJpaRepository<TplCode, String, TplCodeCondition> {


	/**
	 * 得到一个摸个模板集合中的所有模板类型
	 * @param codeSetId
	 * @return
	 */
	public List<String> findCodeTplTypes(String codeSetId){
		List l = em.createQuery(
				"SELECT DISTINCT tc.type FROM TplCode tc WHERE tc.tplSetId = '"+codeSetId+"'")
				.getResultList();
		return l;
	}

	@Override
	public Specification<TplCode> buildQuery(final TplCodeCondition condition){

		return new TplCodeSpecification(condition);
	}
}

class TplCodeSpecification implements Specification<TplCode>{

	TplCodeCondition condition;

	public TplCodeSpecification(TplCodeCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<TplCode> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddAcceptModelTypePredicate(predicateList, root, cb);
		tryAddFilePathPredicate(predicateList, root, cb);
		tryAddFileNamePredicate(predicateList, root, cb);
		tryAddTplSetPredicate(predicateList, root, cb);
		tryAddOverridablePredicate(predicateList, root, cb);
		tryAddContentPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(TplCode.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.equal(root.get(TplCode.PROPERTY_CODE).as(String.class), condition.getCode()));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.equal(root.get(TplCode.PROPERTY_TYPE).as(String.class), condition.getType()));
		}
	}
	private void tryAddAcceptModelTypePredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getAcceptModelTypeId())){
			predicateList.add(cb.equal(root.get(TplCode.PROPERTY_ACCEPT_MODEL_TYPE_ID).as(String.class), condition.getAcceptModelTypeId()));
		}
	}
	private void tryAddFilePathPredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFilePath())){
			predicateList.add(cb.like(root.get(TplCode.PROPERTY_FILE_PATH).as(String.class), "%"+condition.getFilePath()+"%"));
		}
	}
	private void tryAddFileNamePredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getFileName())){
			predicateList.add(cb.like(root.get(TplCode.PROPERTY_FILE_NAME).as(String.class), "%"+condition.getFileName()+"%"));
		}
	}
	private void tryAddTplSetPredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTplSetId())){
			predicateList.add(cb.equal(root.get(TplCode.PROPERTY_TPL_SET_ID).as(String.class), condition.getTplSetId()));
		}
	}
	private void tryAddOverridablePredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if (null != condition.getOverridable() ) {
			predicateList.add(cb.equal(root.get(TplCode.PROPERTY_OVERRIDABLE).as(Boolean.class), condition.getOverridable()));
		}
	}
	private void tryAddContentPredicate(List<Predicate> predicateList, Root<TplCode> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getContent())){
			predicateList.add(cb.like(root.get(TplCode.PROPERTY_CONTENT).as(String.class), "%"+condition.getContent()+"%"));
		}
	}
}

