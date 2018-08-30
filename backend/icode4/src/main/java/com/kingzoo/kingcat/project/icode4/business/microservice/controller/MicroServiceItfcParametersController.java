package com.kingzoo.kingcat.project.icode4.business.microservice.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo.MicroServiceItfcParametersAddRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroServiceItfc;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroServiceItfcParameters;
import com.kingzoo.kingcat.project.icode4.business.microservice.service.MicroServiceItfcParametersService;
import com.kingzoo.kingcat.project.icode4.business.microservice.service.MicroServiceItfcService;
import com.kingzoo.kingcat.project.icode4.business.microservice.valid.MicroServiceItfcParametersValidator;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceItfcParametersCondition;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceItfcParametersVO;
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
 * 管理微服务接口参数
 * @author icode
 */
@RestController
@RequestMapping(value = "/microservice/microServiceItfcParameters")
public class MicroServiceItfcParametersController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MicroServiceItfcParametersController.class);


	@Autowired
	private MicroServiceItfcParametersService microServiceItfcParametersService;

	@Autowired
	private MicroServiceItfcService microServiceItfcService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new MicroServiceItfcParametersValidator());
	}

	/**
	 * 新增微服务接口参数
	 * @param microServiceItfcParametersAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public MicroServiceItfcParametersVO add(@RequestBody @Valid MicroServiceItfcParametersAddRequest microServiceItfcParametersAddRequest){
		MicroServiceItfcParameters microServiceItfcParameters = new MicroServiceItfcParameters();
		BeanUtils.copyProperties(microServiceItfcParametersAddRequest, microServiceItfcParameters);

		microServiceItfcParametersService.add(microServiceItfcParameters);

		return  initViewProperty(microServiceItfcParameters);
	}

	/**
	 * 删除微服务接口参数,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete microServiceItfcParameters :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			microServiceItfcParametersService.delete(id);
		}

	}

	/**
	 * 更新微服务接口参数
	 * @param microServiceItfcParameters
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public MicroServiceItfcParameters update(@RequestBody @Valid MicroServiceItfcParameters microServiceItfcParameters, @PathVariable String id){
		microServiceItfcParameters.setId(id);
		microServiceItfcParametersService.merge(microServiceItfcParameters);

		initViewProperty(microServiceItfcParameters);
		return  microServiceItfcParameters;
	}

	/**
	 * 根据ID查询微服务接口参数
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public MicroServiceItfcParameters get(@PathVariable String id) {

		MicroServiceItfcParameters microServiceItfcParameters = microServiceItfcParametersService.find(id);

		initViewProperty(microServiceItfcParameters);
		return microServiceItfcParameters;
	}

	/**
	 * 查询微服务接口参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<MicroServiceItfcParametersVO> list(@RequestBody PageSearchRequest<MicroServiceItfcParametersCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<MicroServiceItfcParameters> page = microServiceItfcParametersService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<MicroServiceItfcParametersVO> voList = new ArrayList<>();
		for(MicroServiceItfcParameters microServiceItfcParameters : page.getContent()){
			voList.add(initViewProperty(microServiceItfcParameters));
		}

		PageContent<MicroServiceItfcParametersVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private MicroServiceItfcParametersVO initViewProperty(MicroServiceItfcParameters microServiceItfcParameters){
	    MicroServiceItfcParametersVO vo = new MicroServiceItfcParametersVO();

        BeanUtils.copyProperties(microServiceItfcParameters, vo);

	    //初始化其他对象
		initMicroServiceItfcProperty(vo, microServiceItfcParameters);

        return vo;
	}



	private void initMicroServiceItfcProperty(MicroServiceItfcParametersVO microServiceItfcParametersVO, MicroServiceItfcParameters microServiceItfcParameters){
		if(StringUtils.isEmpty(microServiceItfcParameters.getMicroServiceItfcId())){
			return ;
		}
		//初始化其他对象
		MicroServiceItfc microServiceItfc = microServiceItfcService.find(microServiceItfcParameters.getMicroServiceItfcId());
        if(microServiceItfc!=null) {
            microServiceItfcParametersVO.setMicroServiceItfcName(microServiceItfc.getName());
        }
	}
	

}
