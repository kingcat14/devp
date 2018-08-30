package com.kingzoo.kingcat.project.icode4.business.system.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.ModelTypeAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.ModelType;
import com.kingzoo.kingcat.project.icode4.business.system.service.ModelTypeService;
import com.kingzoo.kingcat.project.icode4.business.system.valid.ModelTypeValidator;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ModelTypeCondition;
import com.kingzoo.kingcat.project.icode4.business.system.vo.ModelTypeVO;
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
 * 管理对象类型
 * @author icode
 */
@RestController
@RequestMapping(value = "/system/modelType")
public class ModelTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelTypeController.class);


	@Autowired
	private ModelTypeService modelTypeService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new ModelTypeValidator());
	}

	/**
	 * 新增对象类型
	 * @param modelTypeAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ModelTypeVO add(@RequestBody @Valid ModelTypeAddRequest modelTypeAddRequest){
		ModelType modelType = new ModelType();
		BeanUtils.copyProperties(modelTypeAddRequest, modelType);

		modelTypeService.add(modelType);

		return  initViewProperty(modelType);
	}

	/**
	 * 删除对象类型,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete modelType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			modelTypeService.delete(id);
		}

	}

	/**
	 * 更新对象类型
	 * @param modelType
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public ModelType update(@RequestBody @Valid ModelType modelType, @PathVariable String id){
		modelType.setId(id);
		modelTypeService.merge(modelType);

		initViewProperty(modelType);
		return  modelType;
	}

	/**
	 * 根据ID查询对象类型
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public ModelType get(@PathVariable String id) {

		ModelType modelType = modelTypeService.find(id);

		initViewProperty(modelType);
		return modelType;
	}

	/**
	 * 查询对象类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<ModelTypeVO> list(@RequestBody PageSearchRequest<ModelTypeCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<ModelType> page = modelTypeService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<ModelTypeVO> voList = new ArrayList<>();
		for(ModelType modelType : page.getContent()){
			voList.add(initViewProperty(modelType));
		}

		PageContent<ModelTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private ModelTypeVO initViewProperty(ModelType modelType){
	    ModelTypeVO vo = new ModelTypeVO();

        BeanUtils.copyProperties(modelType, vo);

	    //初始化其他对象
        return vo;
	}




}
