package com.kingzoo.kingcat.project.icode4.business.view.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelProperty;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelPropertyService;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelPropertyCondition;
import com.kingzoo.kingcat.project.icode4.business.view.dao.ViewModelDao;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModel;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModelProperty;
import com.kingzoo.kingcat.project.icode4.business.view.vo.ViewModelCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("viewModelService")
public class ViewModelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ViewModel.class);

	@Autowired
	@Qualifier("viewModelDao")
	private ViewModelDao viewModelDao;

	@Autowired
	private DomainModelService domainModelService;

	@Autowired
	private ViewModelPropertyService viewModelPropertyService;

	@Autowired
	private DomainModelPropertyService domainModelPropertyService;

	@Transactional
	public void add(ViewModel viewModel){
		viewModel.setId(IDGenerator.get());
		viewModelDao.insert(viewModel);
	}

	@Transactional
	public void add(List<ViewModel> viewModelList){
		for(ViewModel viewModel:viewModelList){
			this.add(viewModel);
		}
	}

	@Transactional
	public void delete(ViewModel viewModel){
	    LOGGER.debug("delete viewModel:{}", viewModel);
		viewModelDao.delete(viewModel.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete ViewModel by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete viewModel:{}", id);
		viewModelDao.delete(id);
		viewModelPropertyService.deleteByViewModelId(id);
	}

	@Transactional
	public void delete(List<ViewModel> viewModels){
		for(ViewModel viewModel: viewModels){
			delete(viewModel.getId());
		}
	}

	@Transactional
	public void merge(ViewModel viewModel){
		viewModelDao.save(viewModel);
	}

	@Transactional
	public void merge(List<ViewModel> viewModelList){
		for(ViewModel viewModel:viewModelList){
			this.merge(viewModel);
		}
	}

	@Transactional(readOnly=true)
	public ViewModel find(String id){
	    ViewModel viewModel = null;
		if(StringUtils.isNotEmpty(id)) {
            viewModel = viewModelDao.findOne(id);
        }

		return viewModel;
	}

	@Transactional(readOnly=true)
	public ViewModel findOne(ViewModelCondition viewModelCondition){

		List<ViewModel> viewModelList = viewModelDao.findAll(viewModelCondition);

		ViewModel result = null;
		if(CollectionUtils.isNotEmpty(viewModelList)){
			result = viewModelList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<ViewModel> findAll(ViewModelCondition viewModelCondition){

		List<ViewModel> viewModelList = viewModelDao.findAll(viewModelCondition, getDefaultSort());

		return viewModelList;
	}

	@Transactional(readOnly=true)
	public Page<ViewModel> find(Pageable pageable){
		Page<ViewModel> viewModelList = viewModelDao.findAll(pageable, null, getDefaultSort());
		return viewModelList;
	}

	@Transactional(readOnly=true)
	public Page<ViewModel> find(Pageable pageable, ViewModelCondition viewModelCondition){
		Page<ViewModel> viewModelList = viewModelDao.findAll(pageable, viewModelCondition, getDefaultSort());
		return viewModelList;
	}

	@Transactional(readOnly=true)
	public long count(ViewModelCondition viewModelCondition){
		long count = viewModelDao.count(viewModelCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC , ViewModel.PROPERTY_VIEW_INDEX);
		return sort;
	}

	@Transactional
	public void createFromDomainModel(String domainModelId){
		DomainModel domainModel = domainModelService.find(domainModelId);

		ViewModel viewModel = new ViewModel();
		BeanUtils.copyProperties(domainModel, viewModel);
		viewModel.setName(domainModel.getName());
		viewModel.setCode(domainModel.getCode()+"VO");
		viewModel.setDomainModelId(domainModelId);
		viewModel.setDomainModelName(domainModel.getName());
		viewModel.setDescription(domainModel.getDescription());
		viewModel.setViewIndex(domainModel.getViewIndex());
		viewModel.setMemo(domainModel.getDescription());

		viewModel.setId(null);

		this.add(viewModel);

		DomainModelPropertyCondition propertyCondition = new DomainModelPropertyCondition();
		propertyCondition.setDomainModelId(domainModelId+"");
		List<DomainModelProperty> propertyList = domainModelPropertyService.findAll(propertyCondition);

		for(DomainModelProperty property : propertyList){
			ViewModelProperty viewModelProperty = new ViewModelProperty();
			BeanUtils.copyProperties(property, viewModelProperty);
			viewModelProperty.setViewModelId(viewModel.getId());
			viewModelProperty.setDomainModelId(property.getDomainModelId());
			viewModelProperty.setDomainModelPropertyId(property.getId());
			viewModelProperty.setViewIndex(property.getViewIndex());
			viewModelProperty.setGridIndex(viewModel.getViewIndex());
			viewModelProperty.setGridHidden(false);
			viewModelProperty.setFormIndex(viewModel.getViewIndex());
			viewModelProperty.setFormHidden(false);
			viewModelProperty.setCoreRelation(false);

			viewModelProperty.setId(null);
			viewModelPropertyService.add(viewModelProperty);
		}


	}





}