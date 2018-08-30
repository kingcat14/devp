package com.kingzoo.kingcat.project.icode4.business.view.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelProperty;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelPropertyService;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
import com.kingzoo.kingcat.project.icode4.business.view.controller.vo.ViewModelPropertyAddRequest;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModel;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModelProperty;
import com.kingzoo.kingcat.project.icode4.business.view.service.ViewModelPropertyService;
import com.kingzoo.kingcat.project.icode4.business.view.service.ViewModelService;
import com.kingzoo.kingcat.project.icode4.business.view.valid.ViewModelPropertyValidator;
import com.kingzoo.kingcat.project.icode4.business.view.vo.ViewModelPropertyCondition;
import com.kingzoo.kingcat.project.icode4.business.view.vo.ViewModelPropertyVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理展现对象属性
 * @author icode
 */
@RestController
@RequestMapping(value = "/view/viewModelProperty")
public class ViewModelPropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ViewModelPropertyController.class);


	@Autowired
	private ViewModelPropertyService viewModelPropertyService;

	@Autowired
	private ViewModelService viewModelService;
	@Autowired
	private DomainModelService domainModelService;
	@Autowired
	private DomainModelPropertyService domainModelPropertyService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new ViewModelPropertyValidator());
	}

	/**
	 * 新增展现对象属性
	 * @param viewModelPropertyAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ViewModelPropertyVO add(@RequestBody @Valid ViewModelPropertyAddRequest viewModelPropertyAddRequest){
		ViewModelProperty viewModelProperty = new ViewModelProperty();
		BeanUtils.copyProperties(viewModelPropertyAddRequest, viewModelProperty);

		viewModelPropertyService.add(viewModelProperty);

		return  initViewProperty(viewModelProperty);
	}

	/**
	 * 删除展现对象属性,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete viewModelProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			viewModelPropertyService.delete(id);
		}

	}

	/**
	 * 更新展现对象属性
	 * @param viewModelProperty
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public ViewModelPropertyVO update(@RequestBody @Valid ViewModelProperty viewModelProperty, @PathVariable String id){
		viewModelProperty.setId(id);
		viewModelPropertyService.merge(viewModelProperty);

		ViewModelPropertyVO vo = initViewProperty(viewModelProperty);
		return  vo;
	}

	/**
	 * 根据ID查询展现对象属性
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public ViewModelPropertyVO get(@PathVariable String id) {

		ViewModelProperty viewModelProperty = viewModelPropertyService.find(id);

		ViewModelPropertyVO vo = initViewProperty(viewModelProperty);
		return vo;
	}

	/**
	 * 查询展现对象属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<ViewModelPropertyVO> list(@RequestBody PageSearchRequest<ViewModelPropertyCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<ViewModelProperty> page = viewModelPropertyService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<ViewModelPropertyVO> voList = new ArrayList<>();
		for(ViewModelProperty viewModelProperty : page.getContent()){
			voList.add(initViewProperty(viewModelProperty));
		}

		PageContent<ViewModelPropertyVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private ViewModelPropertyVO initViewProperty(ViewModelProperty viewModelProperty){
	    ViewModelPropertyVO vo = new ViewModelPropertyVO();

        BeanUtils.copyProperties(viewModelProperty, vo);

	    //初始化其他对象
		initViewModelProperty(vo, viewModelProperty);

		initDomainModelProperty(vo, viewModelProperty);

		initDomainModelPropertyProperty(vo, viewModelProperty);

        return vo;
	}



	private void initViewModelProperty(ViewModelPropertyVO viewModelPropertyVO, ViewModelProperty viewModelProperty){
		if(StringUtils.isEmpty(viewModelProperty.getViewModelId())){
			return ;
		}
		//初始化其他对象
		ViewModel viewModel = viewModelService.find(viewModelProperty.getViewModelId());
        if(viewModel!=null) {
            viewModelPropertyVO.setViewModelName(viewModel.getName());
        }
	}
	
	private void initDomainModelProperty(ViewModelPropertyVO viewModelPropertyVO, ViewModelProperty viewModelProperty){
		if(StringUtils.isEmpty(viewModelProperty.getDomainModelId())){
			return ;
		}
		//初始化其他对象
		DomainModel domainModel = domainModelService.find(viewModelProperty.getDomainModelId());
        if(domainModel!=null) {
            viewModelPropertyVO.setDomainModelName(domainModel.getName());
        }
	}
	
	private void initDomainModelPropertyProperty(ViewModelPropertyVO viewModelPropertyVO, ViewModelProperty viewModelProperty){
		if(StringUtils.isEmpty(viewModelProperty.getDomainModelPropertyId())){
			return ;
		}
		//初始化其他对象
		DomainModelProperty domainModelProperty = domainModelPropertyService.find(viewModelProperty.getDomainModelPropertyId());
        if(domainModelProperty!=null) {
            viewModelPropertyVO.setDomainModelPropertyName(domainModelProperty.getName());
        }
	}
	

}
