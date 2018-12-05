package net.aicoder.speedcloud.icode.business.domain.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.icode.business.domain.dao.DomainDao;
import net.aicoder.speedcloud.icode.business.domain.dao.DomainSpecification;
import net.aicoder.speedcloud.icode.business.domain.domain.Domain;
import net.aicoder.speedcloud.icode.business.domain.domain.Entity;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("domainService")
@Slf4j
public class DomainService  extends GenericCrudService<Domain, String, DomainCondition, DomainDao> {

	@Autowired
	private EntityService entityService;


	public Domain copy(String moduleId){
		Domain oldDomain = dao.findOne(moduleId);
		if(oldDomain == null){
			return null;
		}
		Domain module = new Domain();
		BeanUtils.copyProperties(oldDomain, module);
		module.setCode("COPY_"+oldDomain.getCode());
		module.setName("COPY_"+oldDomain.getName());
		module.setId(null);
		this.add(module);

		//复制领域对象
		List<Entity> domainModelList = entityService.findEntityByDomainId(oldDomain.getId());
		for(Entity target : domainModelList){
			Entity domainModel = entityService.copy(target.getId());
			domainModel.setDomain(module.getId());

			domainModel.setName(domainModel.getName().replaceFirst("COPY_", ""));
			domainModel.setCode(domainModel.getCode().replaceFirst("COPY_", ""));
			entityService.merge(domainModel);
		}

		//复制下级模块
		List<Domain> subDomain1Lst = this.findSubDomain(oldDomain.getId());
		for(Domain subDomainTarget:subDomain1Lst){
			Domain subDomain = copy(subDomainTarget.getId());
			subDomain.setParent(module.getId());
			subDomain.setName(subDomain.getName().replaceFirst("COPY_", ""));
			subDomain.setCode(subDomain.getCode().replaceFirst("COPY_", ""));
			this.merge(subDomain);
		}

		return module;
	}

	/**
	 * 查询模块的所有下级模块
	 * @param parentDomainId
	 * @return
	 */
	public List<Domain> findSubDomain(String parentDomainId){

		List<Domain> result = new ArrayList<>();

		List<Domain> subDomainList = this.findChildDomain(parentDomainId);

		if(CollectionUtils.isEmpty(subDomainList)){
			return result;
		}

		result.addAll(subDomainList);
		subDomainList.forEach((domain)->{
			List<Domain> grandDomainList = findSubDomain(domain.getId());
			result.addAll(grandDomainList);
		});

		return result;
	}

	/**
	 * 查询模块的子模块
	 * @param parentDomainId
	 * @return
	 */
	public List<Domain> findChildDomain(String parentDomainId){
		List<Domain> result = new ArrayList<>();

		DomainCondition condition = new DomainCondition();
		condition.setParent(parentDomainId);
		List<Domain> subDomainList= this.findAll(condition);
		if(CollectionUtils.isNotEmpty(subDomainList)){
			result.addAll(subDomainList);
		}
		return result;
	}
	
	public String getCodePath(String id){

		String result = "";
		Domain current = null;
		if(StringUtils.isNotEmpty(id)) {
			current = dao.findOne(id);
			result = current.getCode();
		}
		while(current != null &&  StringUtils.isNotEmpty(current.getParent())){
			current = dao.findOne(current.getParent());
			result = current.getCode()+"."+result;
		}

		return result;
	}

	public String findNamePath(String id){

		String result = "";
		Domain current = null;
		if(StringUtils.isNotEmpty(id)) {
			current = dao.findOne(id);
			result = current.getName();
		}
		while(current != null &&  StringUtils.isNotEmpty(current.getParent())){
			current = dao.findOne(current.getParent());
			//module.setCode(current.getCode()+"."+module.getCode());
			result = current.getName()+"."+result;
		}

		return result;
	}

	/**
	 * 找到最上层的Domain
	 * @param id 当前module的id
	 * @return
	 */
	public Domain findTopDomain(String id){

		Domain current = null;
		if(StringUtils.isNotEmpty(id)) {
			current = dao.findOne(id);
		}
		while(current != null &&  StringUtils.isNotEmpty(current.getParent())){
			current = dao.findOne(current.getParent());
			//module.setCode(current.getCode()+"."+module.getCode());
		}

		return current;
	}
	
	
	@Override
	public Specification<Domain> getSpecification(DomainCondition condition) {
		return new DomainSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Domain.PROPERTY_NAME);
		return sort;
	}
}