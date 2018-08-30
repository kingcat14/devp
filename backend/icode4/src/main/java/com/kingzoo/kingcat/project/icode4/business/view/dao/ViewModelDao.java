package com.kingzoo.kingcat.project.icode4.business.view.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModel;
import com.kingzoo.kingcat.project.icode4.business.view.vo.ViewModelCondition;
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
 * 展现对象的数据库操作
 * @author icode
 */
@Repository("viewModelDao")
public class ViewModelDao extends SimpleJpaRepository<ViewModel, String, ViewModelCondition> {

	@Override
	public Specification<ViewModel> buildQuery(final ViewModelCondition condition){

		return new ViewModelSpecification(condition);
	}
}

class ViewModelSpecification implements Specification<ViewModel>{

	ViewModelCondition condition;

	public ViewModelSpecification(ViewModelCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<ViewModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddMemoPredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);
		tryAddViewIndexPredicate(predicateList, root, cb);
		tryAddDomainModelPredicate(predicateList, root, cb);
		tryAddModulePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<ViewModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(ViewModel.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<ViewModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(ViewModel.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddMemoPredicate(List<Predicate> predicateList, Root<ViewModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getMemo())){
			predicateList.add(cb.like(root.get(ViewModel.PROPERTY_MEMO).as(String.class), "%"+condition.getMemo()+"%"));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<ViewModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(ViewModel.PROPERTY_DESCIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
	private void tryAddViewIndexPredicate(List<Predicate> predicateList, Root<ViewModel> root, CriteriaBuilder cb){
		if (null != condition.getViewIndex() ) {
			predicateList.add(cb.equal(root.get(ViewModel.PROPERTY_VIEW_INDEX).as(Integer.class), condition.getViewIndex()));
		}
	}
	private void tryAddDomainModelPredicate(List<Predicate> predicateList, Root<ViewModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDomainModelId())){
			predicateList.add(cb.equal(root.get(ViewModel.PROPERTY_DOMAIN_MODEL_ID).as(String.class), condition.getDomainModelId()));
		}
	}
	private void tryAddModulePredicate(List<Predicate> predicateList, Root<ViewModel> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModuleId())){
			predicateList.add(cb.equal(root.get(ViewModel.PROPERTY_MODULE_ID).as(String.class), condition.getModuleId()));
		}
	}
}

