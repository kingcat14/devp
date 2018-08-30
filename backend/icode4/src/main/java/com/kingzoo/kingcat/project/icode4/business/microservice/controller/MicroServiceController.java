package com.kingzoo.kingcat.project.icode4.business.microservice.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo.MicroServiceAddRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroService;
import com.kingzoo.kingcat.project.icode4.business.microservice.service.MicroServiceService;
import com.kingzoo.kingcat.project.icode4.business.microservice.valid.MicroServiceValidator;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceCondition;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.MicroServiceVO;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.service.ModuleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理微服务
 * @author icode
 */
@RestController
@RequestMapping(value = "/microservice/microService")
public class MicroServiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MicroServiceController.class);


	@Autowired
	private MicroServiceService microServiceService;

	@Autowired
	private ModuleService moduleService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new MicroServiceValidator());
	}

	/**
	 * 新增微服务
	 * @param microServiceAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public MicroServiceVO add(@RequestBody @Valid MicroServiceAddRequest microServiceAddRequest){
		MicroService microService = new MicroService();
		BeanUtils.copyProperties(microServiceAddRequest, microService);

		microServiceService.add(microService);

		return  initViewProperty(microService);
	}

	/**
	 * 删除微服务,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete microService :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			microServiceService.delete(id);
		}

	}

	/**
	 * 从一个领域对象创建一组微服务
	 * @param id
	 * @return
	 */
	@PutMapping(value="/createFromDomainModel/{id}")
	public ResponseEntity<String> createFromDomainModel(@PathVariable String id){

		microServiceService.createFromDomainModel(id);

		ResponseEntity<String> responseResult = new ResponseEntity<>(
				"{success:true,message:'data  copy finish'}", HttpStatus.OK);

		return responseResult;
	}

	/**
	 * 更新微服务
	 * @param microService
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public MicroService update(@RequestBody @Valid MicroService microService, @PathVariable String id){
		microService.setId(id);
		microServiceService.merge(microService);

		initViewProperty(microService);
		return  microService;
	}

	/**
	 * 根据ID查询微服务
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public MicroServiceVO get(@PathVariable String id) {

		MicroService microService = microServiceService.find(id);

		MicroServiceVO vo = initViewProperty(microService);
		return vo;
	}

	/**
	 * 查询微服务列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<MicroServiceVO> list(@RequestBody PageSearchRequest<MicroServiceCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<MicroService> page = microServiceService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<MicroServiceVO> voList = new ArrayList<>();
		for(MicroService microService : page.getContent()){
			voList.add(initViewProperty(microService));
		}

		PageContent<MicroServiceVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private MicroServiceVO initViewProperty(MicroService microService){
	    MicroServiceVO vo = new MicroServiceVO();

        BeanUtils.copyProperties(microService, vo);

	    //初始化其他对象
		initModuleProperty(vo, microService);

        return vo;
	}



	private void initModuleProperty(MicroServiceVO microServiceVO, MicroService microService){
		if(StringUtils.isEmpty(microService.getModuleId())){
			return ;
		}
		//初始化其他对象
		Module module = moduleService.find(microService.getModuleId());
        if(module!=null) {
            microServiceVO.setModuleName(module.getName());
        }
	}

}
