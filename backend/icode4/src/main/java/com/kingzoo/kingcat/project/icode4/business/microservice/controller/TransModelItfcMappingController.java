package com.kingzoo.kingcat.project.icode4.business.microservice.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo.TransModelItfcMappingAddRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModelItfcMapping;
import com.kingzoo.kingcat.project.icode4.business.microservice.service.TransModelItfcMappingService;
import com.kingzoo.kingcat.project.icode4.business.microservice.valid.TransModelItfcMappingValidator;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelItfcMappingCondition;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelItfcMappingVO;
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
 * 管理传输对象接口映射
 * @author icode
 */
@RestController
@RequestMapping(value = "/microservice/transModelItfcMapping")
public class TransModelItfcMappingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransModelItfcMappingController.class);


	@Autowired
	private TransModelItfcMappingService transModelItfcMappingService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new TransModelItfcMappingValidator());
	}

	/**
	 * 新增传输对象接口映射
	 * @param transModelItfcMappingAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public TransModelItfcMappingVO add(@RequestBody @Valid TransModelItfcMappingAddRequest transModelItfcMappingAddRequest){
		TransModelItfcMapping transModelItfcMapping = new TransModelItfcMapping();
		BeanUtils.copyProperties(transModelItfcMappingAddRequest, transModelItfcMapping);

		transModelItfcMappingService.add(transModelItfcMapping);

		return  initViewProperty(transModelItfcMapping);
	}

	/**
	 * 删除传输对象接口映射,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete transModelItfcMapping :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			transModelItfcMappingService.delete(id);
		}

	}

	/**
	 * 更新传输对象接口映射
	 * @param transModelItfcMapping
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public TransModelItfcMapping update(@RequestBody @Valid TransModelItfcMapping transModelItfcMapping, @PathVariable String id){
		transModelItfcMapping.setId(id);
		transModelItfcMappingService.merge(transModelItfcMapping);

		initViewProperty(transModelItfcMapping);
		return  transModelItfcMapping;
	}

	/**
	 * 根据ID查询传输对象接口映射
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public TransModelItfcMapping get(@PathVariable String id) {

		TransModelItfcMapping transModelItfcMapping = transModelItfcMappingService.find(id);

		initViewProperty(transModelItfcMapping);
		return transModelItfcMapping;
	}

	/**
	 * 查询传输对象接口映射列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<TransModelItfcMappingVO> list(@RequestBody PageSearchRequest<TransModelItfcMappingCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<TransModelItfcMapping> page = transModelItfcMappingService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<TransModelItfcMappingVO> voList = new ArrayList<>();
		for(TransModelItfcMapping transModelItfcMapping : page.getContent()){
			voList.add(initViewProperty(transModelItfcMapping));
		}

		PageContent<TransModelItfcMappingVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private TransModelItfcMappingVO initViewProperty(TransModelItfcMapping transModelItfcMapping){
	    TransModelItfcMappingVO vo = new TransModelItfcMappingVO();

        BeanUtils.copyProperties(transModelItfcMapping, vo);

	    //初始化其他对象
        return vo;
	}




}
