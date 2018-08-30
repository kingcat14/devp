package com.kingzoo.kingcat.project.icode4.business.system.dao;

import com.kingzoo.kingcat.commons.framework.spring.jpa.SimpleJpaRepository;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ModuleCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 模块的数据库操作
 * @author icode
 */
@Repository("moduleDao")
public class ModuleDao extends SimpleJpaRepository<Module, String, ModuleCondition> {

	@Override
	public Specification<Module> buildQuery(final ModuleCondition condition){

		return new ModuleSpecification(condition);
	}


	public List<Module> findTopModule(String projectId){

		Query a = this.em.createQuery("from Module where " + Module.PROPERTY_PARENT_MODULE_ID + " is null and " + Module.PROPERTY_SYSTEM_ID + "= :projectId");
		a.setParameter("projectId", projectId);

		return a.getResultList();

	}
}

class ModuleSpecification implements Specification<Module>{

	ModuleCondition condition;

	public ModuleSpecification(ModuleCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Module> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}
       
		tryAddNamePredicate(predicateList, root, cb);
		tryAddCodePredicate(predicateList, root, cb);
		tryAddParentModulePredicate(predicateList, root, cb);
		tryAddSystemPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
	}


	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Module> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Module.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddCodePredicate(List<Predicate> predicateList, Root<Module> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getCode())){
			predicateList.add(cb.like(root.get(Module.PROPERTY_CODE).as(String.class), "%"+condition.getCode()+"%"));
		}
	}
	private void tryAddParentModulePredicate(List<Predicate> predicateList, Root<Module> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getParentModuleId())){
			predicateList.add(cb.equal(root.get(Module.PROPERTY_PARENT_MODULE_ID).as(String.class), condition.getParentModuleId()));
		}
	}
	private void tryAddSystemPredicate(List<Predicate> predicateList, Root<Module> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getSystemId())){
			predicateList.add(cb.equal(root.get(Module.PROPERTY_SYSTEM_ID).as(String.class), condition.getSystemId()));
		}
	}
}

