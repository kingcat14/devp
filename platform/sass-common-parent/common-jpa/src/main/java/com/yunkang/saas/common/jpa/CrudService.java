package com.yunkang.saas.common.jpa;

import com.yunkang.saas.common.framework.eo.BaseEntity;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.util.IdSnowflake;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gonghongrui on 2018/4/17.
 */
public abstract class CrudService<T extends BaseEntity, C, D extends BaseDao<T, Long>> {

	@Autowired
	protected D dao;

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Transactional
	public void add(T t){
		t.setId(IdSnowflake.getLocalInstance().nextId(getClass()));
		dao.save(t);
	}

	@Transactional
	public void add(List<T> tList){
		for(T t:tList){
			this.add(t);
		}
	}

	@Transactional
	public void delete(T t){
		LOGGER.debug("delete t:{}", t);
		dao.delete(t.getId());
	}

	@Transactional
	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return;
		}
		LOGGER.debug("delete t:{}", id);
		dao.delete(id);
	}

	@Transactional
	public void delete(List<T> ts){
		for(T t: ts){
			delete(t.getId());
		}
	}

	@Transactional
	public void merge(T t){
		dao.save(t);
	}

	@Transactional
	public void merge(List<T> tList){
		for(T t:tList){
			this.merge(t);
		}
	}

	@Transactional(readOnly=true)
	public T find(Long id){
		T t = null;
		if(null != id){
			t = dao.findOne(id);
		}

		return t;
	}

	@Transactional(readOnly=true)
	public T findOne(C condition){

		List<T> tList = dao.findAll(getSpecification(condition));

		T result = null;
		if(CollectionUtils.isNotEmpty(tList)){
			result = tList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<T> findAll(C condition){

		List<T> tList = dao.findAll(getSpecification(condition), getDefaultSort());

		return tList;
	}

	@Transactional(readOnly=true)
	public Page<T> find(PageRequest pageRequest){
		if(pageRequest.getSort()==null) {
			pageRequest.setSort(getDefaultSort());
		}
		Page<T> tList = dao.findAll(pageRequest);
		return tList;
	}

	@Transactional(readOnly=true)
	public Page<T> find(C condition, PageRequest pageable){
		if(pageable.getSort()==null) {
			pageable.setSort(getDefaultSort());
		}
		Page<T> tList = dao.findAll(getSpecification(condition), pageable);
		return tList;
	}

	@Transactional(readOnly=true)
	public long count(C condition){
		long count = dao.count(getSpecification(condition));
		return count;
	}


	public abstract Specification<T> getSpecification(C condition);

	public Sort getDefaultSort(){
		return null;
	}

}
