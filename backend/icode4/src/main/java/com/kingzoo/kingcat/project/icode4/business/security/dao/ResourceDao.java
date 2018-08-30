package com.kingzoo.kingcat.project.icode4.business.security.dao;


import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.security.dto.ResourceSearchDto;
import com.kingzoo.kingcat.project.icode4.business.security.entity.Resource;
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
 * 资源的数据库操作
 */
@Repository("resourceDao")
public class ResourceDao extends SimpleJpaRepository<Resource, String, ResourceSearchDto> {

	@Override
	public Specification<Resource> buildQuery(final ResourceSearchDto condition){

		return new ResourceSpecification(condition);
	}
}

class ResourceSpecification implements Specification<Resource>{

	ResourceSearchDto condition;

	public ResourceSpecification(ResourceSearchDto condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Resource> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
        tryAddTenantPredicate(predicateList, root, cb);
		tryAddParentIdPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddOrderIndexPredicate(predicateList, root, cb);
		tryAddUrlPredicate(predicateList, root, cb);
		tryAddTypePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}

	private void tryAddTenantPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(null != condition.getTenantId()){
			predicateList.add(cb.equal(root.get(Resource.PROPERTY_TENANT_ID).as(String.class), condition.getTenantId()));
		}
    	}
	private void tryAddParentIdPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getParentId())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_PARENT_ID).as(String.class), "%"+condition.getParentId()+"%"));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddOrderIndexPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getOrderIndex())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_ORDER_INDEX).as(String.class), "%"+condition.getOrderIndex()+"%"));
		}
	}
	private void tryAddUrlPredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getUrl())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_URL).as(String.class), "%"+condition.getUrl()+"%"));
		}
	}
	private void tryAddTypePredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getType())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_TYPE).as(String.class), "%"+condition.getType()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<Resource> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(Resource.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
}
