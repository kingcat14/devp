package com.kingzoo.kingcat.project.icode4.business.microservice.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo.TransModelAddRequest;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModel;
import com.kingzoo.kingcat.project.icode4.business.microservice.service.TransModelService;
import com.kingzoo.kingcat.project.icode4.business.microservice.valid.TransModelValidator;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelCondition;
import com.kingzoo.kingcat.project.icode4.business.microservice.vo.TransModelVO;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
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
 * 管理传输对象
 * @author icode
 */
@RestController
@RequestMapping(value = "/microservice/transModel")
public class TransModelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransModelController.class);


	@Autowired
	private TransModelService transModelService;

	@Autowired
	private ModuleService moduleService;
	@Autowired
	private DomainModelService domainModelService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new TransModelValidator());
	}

	/**
	 * 新增传输对象
	 * @param transModelAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public TransModelVO add(@RequestBody @Valid TransModelAddRequest transModelAddRequest){
		TransModel transModel = new TransModel();
		BeanUtils.copyProperties(transModelAddRequest, transModel);

		transModelService.add(transModel);

		return  initViewProperty(transModel);
	}

	/**
	 * 删除传输对象,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete transModel :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			transModelService.delete(id);
		}

	}

	@PutMapping(value="/createFromDomainModel/{id}")
	public ResponseEntity<String> createFromDomainModel(@PathVariable String id){

		transModelService.createFromDomainModel(id);

		ResponseEntity<String> responseResult = new ResponseEntity<>(
				"{success:true,message:'data  copy finish'}", HttpStatus.OK);

		return responseResult;
	}

	/**
	 * 更新传输对象
	 * @param transModel
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public TransModel update(@RequestBody @Valid TransModel transModel, @PathVariable String id){
		transModel.setId(id);
		transModelService.merge(transModel);

		initViewProperty(transModel);
		return  transModel;
	}

	/**
	 * 根据ID查询传输对象
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public TransModel get(@PathVariable String id) {

		TransModel transModel = transModelService.find(id);

		initViewProperty(transModel);
		return transModel;
	}

	/**
	 * 查询传输对象列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<TransModelVO> list(@RequestBody PageSearchRequest<TransModelCondition> pageSearchRequest){

		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<TransModel> page = transModelService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<TransModelVO> voList = new ArrayList<>();
		for(TransModel transModel : page.getContent()){
			voList.add(initViewProperty(transModel));
		}

		PageContent<TransModelVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private TransModelVO initViewProperty(TransModel transModel){
	    TransModelVO vo = new TransModelVO();

        BeanUtils.copyProperties(transModel, vo);

	    //初始化其他对象
		initModuleProperty(vo, transModel);

		initDomainModelProperty(vo, transModel);

        return vo;
	}



	private void initModuleProperty(TransModelVO transModelVO, TransModel transModel){
		if(StringUtils.isEmpty(transModel.getModuleId())){
			return ;
		}
		//初始化其他对象
		Module module = moduleService.find(transModel.getModuleId());
        if(module!=null) {
            transModelVO.setModuleName(module.getName());
        }
	}
	
	private void initDomainModelProperty(TransModelVO transModelVO, TransModel transModel){
		if(StringUtils.isEmpty(transModel.getDomainModelId())){
			return ;
		}
		//初始化其他对象
		DomainModel domainModel = domainModelService.find(transModel.getDomainModelId());
        if(domainModel!=null) {
            transModelVO.setDomainModelName(domainModel.getName());
        }
	}
	

}
