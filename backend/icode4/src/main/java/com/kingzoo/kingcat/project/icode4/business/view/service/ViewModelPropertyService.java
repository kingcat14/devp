package com.kingzoo.kingcat.project.icode4.business.view.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.view.dao.ViewModelPropertyDao;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModelProperty;
import com.kingzoo.kingcat.project.icode4.business.view.vo.ViewModelPropertyCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("viewModelPropertyService")
public class ViewModelPropertyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ViewModelProperty.class);

	@Autowired
	@Qualifier("viewModelPropertyDao")
	private ViewModelPropertyDao viewModelPropertyDao;

	@Transactional
	public void add(ViewModelProperty viewModelProperty){
		viewModelProperty.setId(IDGenerator.get());
		viewModelPropertyDao.insert(viewModelProperty);
	}

	@Transactional
	public void add(List<ViewModelProperty> viewModelPropertyList){
		for(ViewModelProperty viewModelProperty:viewModelPropertyList){
			this.add(viewModelProperty);
		}
	}

	@Transactional
	public void delete(ViewModelProperty viewModelProperty){
	    LOGGER.debug("delete viewModelProperty:{}", viewModelProperty);
		viewModelPropertyDao.delete(viewModelProperty.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete ViewModelProperty by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete viewModelProperty:{}", id);
		viewModelPropertyDao.delete(id);
	}

	@Transactional
	public void delete(List<ViewModelProperty> viewModelPropertys){
		for(ViewModelProperty viewModelProperty: viewModelPropertys){
			delete(viewModelProperty.getId());
		}
	}

	@Transactional
	public void merge(ViewModelProperty viewModelProperty){
		viewModelPropertyDao.save(viewModelProperty);
	}

	@Transactional
	public void merge(List<ViewModelProperty> viewModelPropertyList){
		for(ViewModelProperty viewModelProperty:viewModelPropertyList){
			this.merge(viewModelProperty);
		}
	}

	@Transactional(readOnly=true)
	public ViewModelProperty find(String id){
	    ViewModelProperty viewModelProperty = null;
		if(StringUtils.isNotEmpty(id)) {
            viewModelProperty = viewModelPropertyDao.findOne(id);
        }

		return viewModelProperty;
	}

	@Transactional(readOnly=true)
	public ViewModelProperty findOne(ViewModelPropertyCondition viewModelPropertyCondition){

		List<ViewModelProperty> viewModelPropertyList = viewModelPropertyDao.findAll(viewModelPropertyCondition);

		ViewModelProperty result = null;
		if(CollectionUtils.isNotEmpty(viewModelPropertyList)){
			result = viewModelPropertyList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<ViewModelProperty> findAll(ViewModelPropertyCondition viewModelPropertyCondition){

		List<ViewModelProperty> viewModelPropertyList = viewModelPropertyDao.findAll(viewModelPropertyCondition, getDefaultSort());

		return viewModelPropertyList;
	}

	@Transactional(readOnly=true)
	public Page<ViewModelProperty> find(Pageable pageable){
		Page<ViewModelProperty> viewModelPropertyList = viewModelPropertyDao.findAll(pageable, null, getDefaultSort());
		return viewModelPropertyList;
	}

	@Transactional(readOnly=true)
	public Page<ViewModelProperty> find(Pageable pageable, ViewModelPropertyCondition viewModelPropertyCondition){
		Page<ViewModelProperty> viewModelPropertyList = viewModelPropertyDao.findAll(pageable, viewModelPropertyCondition, getDefaultSort());
		return viewModelPropertyList;
	}

	@Transactional(readOnly=true)
	public long count(ViewModelPropertyCondition viewModelPropertyCondition){
		long count = viewModelPropertyDao.count(viewModelPropertyCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC , ViewModelProperty.PROPERTY_VIEW_INDEX);
		return sort;
	}

	public void deleteByViewModelId(String viewModelId){

		if(StringUtils.isEmpty(viewModelId)){
			return ;
		}

		ViewModelPropertyCondition condition = new ViewModelPropertyCondition();
		condition.setViewModelId(viewModelId);
		List<ViewModelProperty> viewModelPropertyList = this.findAll(condition);
		this.delete(viewModelPropertyList);
	}




}