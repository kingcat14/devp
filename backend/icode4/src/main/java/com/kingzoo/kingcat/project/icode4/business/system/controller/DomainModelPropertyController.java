package com.kingzoo.kingcat.project.icode4.business.system.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.DomainModelPropertyAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelProperty;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelPropertyService;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
import com.kingzoo.kingcat.project.icode4.business.system.valid.DomainModelPropertyValidator;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelPropertyCondition;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelPropertyVO;
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
 * 管理领域对象属性
 * @author icode
 */
@RestController
@RequestMapping(value = "/system/domainModelProperty")
public class DomainModelPropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainModelPropertyController.class);


	@Autowired
	private DomainModelService domainModelService;
	@Autowired
	private DomainModelPropertyService domainModelPropertyService;

	@Autowired
	private DomainModelPropertyValidator domainModelPropertyValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(domainModelPropertyValidator);
	}

	/**
	 * 新增领域对象属性
	 * @param domainModelPropertyAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DomainModelPropertyVO add(@RequestBody @Valid DomainModelPropertyAddRequest domainModelPropertyAddRequest){
		DomainModelProperty domainModelProperty = new DomainModelProperty();
		BeanUtils.copyProperties(domainModelPropertyAddRequest, domainModelProperty);

		domainModelPropertyService.add(domainModelProperty);

		return  initViewProperty(domainModelProperty);
	}

	/**
	 * 删除领域对象属性,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete domainModelProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			domainModelPropertyService.delete(id);
		}

	}

	/**
	 * 更新领域对象属性
	 * @param domainModelProperty
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public DomainModelPropertyVO update(@RequestBody @Valid DomainModelProperty domainModelProperty, @PathVariable String id){
		domainModelProperty.setId(id);
		domainModelPropertyService.merge(domainModelProperty);

		DomainModelPropertyVO vo = initViewProperty(domainModelProperty);
		return  vo;
	}

	/**
	 * 根据ID查询领域对象属性
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public DomainModelPropertyVO get(@PathVariable String id) {

		DomainModelProperty domainModelProperty = domainModelPropertyService.find(id);

		DomainModelPropertyVO vo = initViewProperty(domainModelProperty);
		return vo;
	}

	/**
	 * 查询领域对象属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<DomainModelPropertyVO> list(@RequestBody PageSearchRequest<DomainModelPropertyCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<DomainModelProperty> page = domainModelPropertyService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<DomainModelPropertyVO> voList = new ArrayList<>();
		for(DomainModelProperty domainModelProperty : page.getContent()){
			voList.add(initViewProperty(domainModelProperty));
		}

		PageContent<DomainModelPropertyVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DomainModelPropertyVO initViewProperty(DomainModelProperty domainModelProperty){
	    DomainModelPropertyVO vo = new DomainModelPropertyVO();

        BeanUtils.copyProperties(domainModelProperty, vo);

	    //初始化其他对象
		initDomainModelProperty(vo, domainModelProperty);

		initRelatedDomainModelProperty(vo, domainModelProperty);

		initRelatedDomainModelPropertyProperty(vo, domainModelProperty);

        return vo;
	}



	private void initDomainModelProperty(DomainModelPropertyVO domainModelPropertyVO, DomainModelProperty domainModelProperty){
		if(StringUtils.isEmpty(domainModelProperty.getDomainModelId())){
			return ;
		}
		//初始化其他对象
		DomainModel domainModel = domainModelService.find(domainModelProperty.getDomainModelId());
        if(domainModel!=null) {
            domainModelPropertyVO.setDomainModelName(domainModel.getName());
        }
	}
	
	private void initRelatedDomainModelProperty(DomainModelPropertyVO domainModelPropertyVO, DomainModelProperty domainModelProperty){
		if(StringUtils.isEmpty(domainModelProperty.getRelatedDomainModelId())){
			return ;
		}
		//初始化其他对象
		DomainModel relatedDomainModel = domainModelService.find(domainModelProperty.getRelatedDomainModelId());
        if(relatedDomainModel!=null) {
            domainModelPropertyVO.setRelatedDomainModelName(relatedDomainModel.getName());
        }
	}
	
	private void initRelatedDomainModelPropertyProperty(DomainModelPropertyVO domainModelPropertyVO, DomainModelProperty domainModelProperty){
		if(StringUtils.isEmpty(domainModelProperty.getRelatedDomainModelPropertyId())){
			return ;
		}
		//初始化其他对象
		DomainModelProperty relatedDomainModelProperty = domainModelPropertyService.find(domainModelProperty.getRelatedDomainModelPropertyId());
        if(relatedDomainModelProperty!=null) {
            domainModelPropertyVO.setRelatedDomainModelPropertyName(relatedDomainModelProperty.getName());
        }
	}
	

}
