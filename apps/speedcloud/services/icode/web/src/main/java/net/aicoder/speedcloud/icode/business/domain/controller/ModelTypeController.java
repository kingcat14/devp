package net.aicoder.speedcloud.icode.business.domain.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.icode.business.domain.domain.ModelType;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeEditDto;
import net.aicoder.speedcloud.icode.business.domain.service.ModelTypeService;
import net.aicoder.speedcloud.icode.business.domain.valid.ModelTypeValidator;
import net.aicoder.speedcloud.icode.business.domain.vo.ModelTypeVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理模型类型
 * @author icode
 */
@Api(description = "模型类型", tags = "ModelType")
@RestController
@RequestMapping(value = "/icode/domain/modeltype")
public class ModelTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelTypeController.class);


	@Autowired
	private ModelTypeService modelTypeService;



	@Autowired
	private ModelTypeValidator modelTypeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(modelTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增模型类型
	 * @param modelTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增模型类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "icode.domain.modeltype.add")
	public ModelTypeVO add(@RequestBody @Valid ModelTypeAddDto modelTypeAddDto){
		ModelType modelType = new ModelType();
		BeanUtils.copyProperties(modelTypeAddDto, modelType);

		modelTypeService.add(modelType);

		return  initViewProperty(modelType);
	}

	/**
	 * 删除模型类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除模型类型", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "icode.domain.modeltype.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete modelType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			modelTypeService.delete(id);
		}

	}

	/**
	 * 更新模型类型
	 * @param modelTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产模型类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.domain.modeltype.update")
	public	ModelTypeVO update(@RequestBody @Valid ModelTypeEditDto modelTypeEditDto, @PathVariable String id){
		ModelType modelType = new ModelType();
		BeanUtils.copyProperties(modelTypeEditDto, modelType);
		modelType.setId(id);
		modelTypeService.merge(modelType);

		ModelTypeVO vo = initViewProperty(modelType);
		return  vo;
	}

	/**
	 * 根据ID查询模型类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询模型类型", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.domain.modeltype.get")
	public  ModelTypeVO get(@PathVariable String id) {

		ModelType modelType = modelTypeService.find(id);
		if(modelType == null){
			throw new ResourceNotFoundException("找不到指定的模型类型，请检查ID");
		}
		ModelTypeVO vo = initViewProperty(modelType);
		return vo;
	}

	/**
	 * 查询模型类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询模型类型列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "icode.domain.modeltype.list")
	public PageContent<ModelTypeVO> list(@RequestBody PageSearchRequest<ModelTypeCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<ModelType> page = modelTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ModelTypeVO> voList = new ArrayList<>();
		for(ModelType modelType : page.getContent()){
			voList.add(initViewProperty(modelType));
		}

		PageContent<ModelTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出模型类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出模型类型列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(ModelTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ModelTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ModelTypeVO> content = this.list(pageSearchRequest);

        List<ModelTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ModelTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("code" ,"code");
        headMap.put("name" ,"name");
        headMap.put("idx" ,"idx");

        String title = new String("模型类型");
        String fileName = new String(("模型类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ModelTypeVO initViewProperty(ModelType modelType){

	    ModelTypeVO vo = new ModelTypeVO();
        BeanUtils.copyProperties(modelType, vo);


	    //初始化其他对象
        return vo;

	}


}

