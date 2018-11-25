package net.aicoder.speedcloud.console.business.icode.domain.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.console.business.icode.domain.service.ModelTypeRibbonService;
import net.aicoder.speedcloud.console.business.icode.domain.valid.ModelTypeValidator;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.ModelTypeVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	private ModelTypeRibbonService modelTypeRibbonService;

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
  	@SaaSAnnotation()
	public ModelTypeVO add(@RequestBody @Valid ModelTypeAddDto modelTypeAddDto){
	
		return  modelTypeRibbonService.add(modelTypeAddDto);
	}

	/**
	 * 删除模型类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除模型类型", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete modelType :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			modelTypeRibbonService.delete(id);
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
	public ModelTypeVO update(@RequestBody @Valid ModelTypeEditDto modelTypeEditDto, @ApiParam(value = "要查询的模型类型id") @PathVariable String id){

		ModelTypeVO vo = modelTypeRibbonService.merge(id, modelTypeEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询模型类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询模型类型", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public ModelTypeVO get(@ApiParam(value = "要查询的模型类型id") @PathVariable String id) {

		ModelTypeVO vo = modelTypeRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询模型类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询模型类型列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = ModelTypeCondition.class)
	public PageContent<ModelTypeVO> list(@RequestBody @Valid PageSearchRequest<ModelTypeCondition> pageSearchRequest){

		PageContent<ModelTypeVO> pageContent = modelTypeRibbonService.list(pageSearchRequest);
		for(ModelTypeVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

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
    public void export(ModelTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

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

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("code" ,"code");
            headMap.put("name" ,"name");
            headMap.put("idx" ,"idx");

        String title = new String("模型类型");
        String fileName = new String(("模型类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private ModelTypeVO initViewProperty( ModelTypeVO vo){


	   
        return vo;

	}
}
