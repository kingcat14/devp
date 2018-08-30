package com.kingzoo.kingcat.project.icode4.business.microservice.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo.TransModelPropertyAddRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModel;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModelProperty;
import com.kingzoo.kingcat.project.icode4.business.microservice.service.TransModelPropertyService;
import com.kingzoo.kingcat.project.icode4.business.microservice.service.TransModelService;
import com.kingzoo.kingcat.project.icode4.business.microservice.valid.TransModelPropertyValidator;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelPropertyCondition;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelPropertyVO;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelProperty;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelPropertyService;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
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
 * 管理传输对象属性
 * @author icode
 */
@RestController
@RequestMapping(value = "/microservice/transModelProperty")
public class TransModelPropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransModelPropertyController.class);


	@Autowired
	private TransModelPropertyService transModelPropertyService;

	@Autowired
	private TransModelService transModelService;
	@Autowired
	private DomainModelService domainModelService;
	@Autowired
	private DomainModelPropertyService domainModelPropertyService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new TransModelPropertyValidator());
	}

	/**
	 * 新增传输对象属性
	 * @param transModelPropertyAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public TransModelPropertyVO add(@RequestBody @Valid TransModelPropertyAddRequest transModelPropertyAddRequest){
		TransModelProperty transModelProperty = new TransModelProperty();
		BeanUtils.copyProperties(transModelPropertyAddRequest, transModelProperty);

		transModelPropertyService.add(transModelProperty);

		return  initViewProperty(transModelProperty);
	}

	/**
	 * 删除传输对象属性,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete transModelProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			transModelPropertyService.delete(id);
		}

	}

	/**
	 * 更新传输对象属性
	 * @param transModelProperty
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public TransModelPropertyVO update(@RequestBody @Valid TransModelProperty transModelProperty, @PathVariable String id){
		transModelProperty.setId(id);
		transModelPropertyService.merge(transModelProperty);

		TransModelPropertyVO vo = initViewProperty(transModelProperty);
		return  vo;
	}

	/**
	 * 根据ID查询传输对象属性
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public TransModelPropertyVO get(@PathVariable String id) {

		TransModelProperty transModelProperty = transModelPropertyService.find(id);

		TransModelPropertyVO vo = initViewProperty(transModelProperty);
		return vo;
	}

	/**
	 * 查询传输对象属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<TransModelPropertyVO> list(@RequestBody PageSearchRequest<TransModelPropertyCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<TransModelProperty> page = transModelPropertyService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<TransModelPropertyVO> voList = new ArrayList<>();
		for(TransModelProperty transModelProperty : page.getContent()){
			voList.add(initViewProperty(transModelProperty));
		}

		PageContent<TransModelPropertyVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private TransModelPropertyVO initViewProperty(TransModelProperty transModelProperty){
	    TransModelPropertyVO vo = new TransModelPropertyVO();

        BeanUtils.copyProperties(transModelProperty, vo);

	    //初始化其他对象
		initTransModelProperty(vo, transModelProperty);

		initDomainModelProperty(vo, transModelProperty);

		initDomainModelPropertyProperty(vo, transModelProperty);

        return vo;
	}



	private void initTransModelProperty(TransModelPropertyVO transModelPropertyVO, TransModelProperty transModelProperty){
		if(StringUtils.isEmpty(transModelProperty.getTransModelId())){
			return ;
		}
		//初始化其他对象
		TransModel transModel = transModelService.find(transModelProperty.getTransModelId());
        if(transModel!=null) {
            transModelPropertyVO.setTransModelName(transModel.getName());
        }
	}
	
	private void initDomainModelProperty(TransModelPropertyVO transModelPropertyVO, TransModelProperty transModelProperty){
		if(StringUtils.isEmpty(transModelProperty.getDomainModelId())){
			return ;
		}
		//初始化其他对象
		DomainModel domainModel = domainModelService.find(transModelProperty.getDomainModelId());
        if(domainModel!=null) {
            transModelPropertyVO.setDomainModelName(domainModel.getName());
        }
	}
	
	private void initDomainModelPropertyProperty(TransModelPropertyVO transModelPropertyVO, TransModelProperty transModelProperty){
		if(StringUtils.isEmpty(transModelProperty.getDomainModelPropertyId())){
			return ;
		}
		//初始化其他对象
		DomainModelProperty domainModelProperty = domainModelPropertyService.find(transModelProperty.getDomainModelPropertyId());
        if(domainModelProperty!=null) {
            transModelPropertyVO.setDomainModelPropertyName(domainModelProperty.getName());
        }
	}
	

}
