package com.kingzoo.kingcat.project.icode4.business.system.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelViewProperty;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelViewPropertyService;
import com.kingzoo.kingcat.project.icode4.business.system.valid.DomainModelViewPropertyValidator;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelViewPropertyCondition;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelViewPropertyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理属性展现配置
 * @author icode
 */
@RestController
@RequestMapping(value = "/system/domainModelViewProperty")
public class DomainModelViewPropertyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainModelViewPropertyController.class);


	@Autowired
	private DomainModelViewPropertyService domainModelViewPropertyService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new DomainModelViewPropertyValidator());
	}


	/**
	 * 删除属性展现配置,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete domainModelViewProperty :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			domainModelViewPropertyService.delete(id);
		}

	}

	/**
	 * 更新属性展现配置
	 * @param domainModelViewProperty
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public DomainModelViewPropertyVO update(@RequestBody @Valid DomainModelViewProperty domainModelViewProperty, @PathVariable String id){
		domainModelViewProperty.setId(id);
		domainModelViewPropertyService.merge(domainModelViewProperty);

		DomainModelViewPropertyVO vo = initViewProperty(domainModelViewProperty);
		return  vo;
	}

	/**
	 * 根据ID查询属性展现配置
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public DomainModelViewPropertyVO get(@PathVariable String id) {

		DomainModelViewProperty domainModelViewProperty = domainModelViewPropertyService.find(id);

		DomainModelViewPropertyVO vo = initViewProperty(domainModelViewProperty);
		return vo;
	}

	/**
	 * 查询属性展现配置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<DomainModelViewPropertyVO> list(@RequestBody PageSearchRequest<DomainModelViewPropertyCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<DomainModelViewProperty> page = domainModelViewPropertyService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<DomainModelViewPropertyVO> voList = new ArrayList<>();
		for(DomainModelViewProperty domainModelViewProperty : page.getContent()){
			voList.add(initViewProperty(domainModelViewProperty));
		}

		PageContent<DomainModelViewPropertyVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DomainModelViewPropertyVO initViewProperty(DomainModelViewProperty domainModelViewProperty){
	    DomainModelViewPropertyVO vo = new DomainModelViewPropertyVO();

        BeanUtils.copyProperties(domainModelViewProperty, vo);

	    //初始化其他对象
        return vo;
	}




}
