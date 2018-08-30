package com.kingzoo.kingcat.project.icode4.business.microservice.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo.MicroServiceItfcAddRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroService;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroServiceItfc;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModel;
import com.kingzoo.kingcat.project.icode4.business.microservice.service.MicroServiceItfcService;
import com.kingzoo.kingcat.project.icode4.business.microservice.service.MicroServiceService;
import com.kingzoo.kingcat.project.icode4.business.microservice.service.TransModelService;
import com.kingzoo.kingcat.project.icode4.business.microservice.valid.MicroServiceItfcValidator;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceItfcCondition;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceItfcVO;
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
 * 管理微服务接口
 * @author icode
 */
@RestController
@RequestMapping(value = "/microservice/microServiceItfc")
public class MicroServiceItfcController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MicroServiceItfcController.class);


	@Autowired
	private MicroServiceItfcService microServiceItfcService;

	@Autowired
	private MicroServiceService microServiceService;
	@Autowired
	private TransModelService transModelService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new MicroServiceItfcValidator());
	}

	/**
	 * 新增微服务接口
	 * @param microServiceItfcAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public MicroServiceItfcVO add(@RequestBody @Valid MicroServiceItfcAddRequest microServiceItfcAddRequest){
		MicroServiceItfc microServiceItfc = new MicroServiceItfc();
		BeanUtils.copyProperties(microServiceItfcAddRequest, microServiceItfc);

		microServiceItfcService.add(microServiceItfc);

		return  initViewProperty(microServiceItfc);
	}

	/**
	 * 删除微服务接口,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete microServiceItfc :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			microServiceItfcService.delete(id);
		}

	}

	/**
	 * 更新微服务接口
	 * @param microServiceItfc
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public MicroServiceItfc update(@RequestBody @Valid MicroServiceItfc microServiceItfc, @PathVariable String id){
		microServiceItfc.setId(id);
		microServiceItfcService.merge(microServiceItfc);

		initViewProperty(microServiceItfc);
		return  microServiceItfc;
	}

	/**
	 * 根据ID查询微服务接口
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public MicroServiceItfc get(@PathVariable String id) {

		MicroServiceItfc microServiceItfc = microServiceItfcService.find(id);

		initViewProperty(microServiceItfc);
		return microServiceItfc;
	}

	/**
	 * 查询微服务接口列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<MicroServiceItfcVO> list(@RequestBody PageSearchRequest<MicroServiceItfcCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<MicroServiceItfc> page = microServiceItfcService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<MicroServiceItfcVO> voList = new ArrayList<>();
		for(MicroServiceItfc microServiceItfc : page.getContent()){
			voList.add(initViewProperty(microServiceItfc));
		}

		PageContent<MicroServiceItfcVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private MicroServiceItfcVO initViewProperty(MicroServiceItfc microServiceItfc){
	    MicroServiceItfcVO vo = new MicroServiceItfcVO();

        BeanUtils.copyProperties(microServiceItfc, vo);

	    //初始化其他对象
		initMicroServiceProperty(vo, microServiceItfc);

		initResponseProperty(vo, microServiceItfc);

        return vo;
	}



	private void initMicroServiceProperty(MicroServiceItfcVO microServiceItfcVO, MicroServiceItfc microServiceItfc){
		if(StringUtils.isEmpty(microServiceItfc.getMicroServiceId())){
			return ;
		}
		//初始化其他对象
		MicroService microService = microServiceService.find(microServiceItfc.getMicroServiceId());
        if(microService!=null) {
            microServiceItfcVO.setMicroServiceName(microService.getName());
        }
	}
	
	private void initResponseProperty(MicroServiceItfcVO microServiceItfcVO, MicroServiceItfc microServiceItfc){
		if(StringUtils.isEmpty(microServiceItfc.getResponseId())){
			return ;
		}
		//初始化其他对象
		TransModel response = transModelService.find(microServiceItfc.getResponseId());
        if(response!=null) {
            microServiceItfcVO.setResponseName(response.getName());
        }
	}
	

}
