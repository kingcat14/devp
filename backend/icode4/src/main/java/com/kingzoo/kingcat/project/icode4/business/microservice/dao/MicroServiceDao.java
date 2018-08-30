package com.kingzoo.kingcat.project.icode4.business.microservice.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroService;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceCondition;
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
 * 微服务的数据库操作
 * @author icode
 */
@Repository("microServiceDao")
public class MicroServiceDao extends SimpleJpaRepository<MicroService, String, MicroServiceCondition> {

	@Override
	public Specification<MicroService> buildQuery(final MicroServiceCondition condition){

		return new MicroServiceSpecification(condition);
	}
}

class MicroServiceSpecification implements Specification<MicroService>{

	MicroServiceCondition condition;

	public MicroServiceSpecification(MicroServiceCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<MicroService> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddModulePredicate(predicateList, root, cb);
		tryAddDescriptionPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<MicroService> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(MicroService.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<MicroService> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(MicroService.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddModulePredicate(List<Predicate> predicateList, Root<MicroService> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getModuleId())){
			predicateList.add(cb.equal(root.get(MicroService.PROPERTY_MODULE_ID).as(String.class), condition.getModuleId()));
		}
	}
	private void tryAddDescriptionPredicate(List<Predicate> predicateList, Root<MicroService> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getDescription())){
			predicateList.add(cb.like(root.get(MicroService.PROPERTY_DESCRIPTION).as(String.class), "%"+condition.getDescription()+"%"));
		}
	}
}

