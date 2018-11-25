package net.aicoder.speedcloud.console.business.icode.tplfile.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.console.business.icode.tplfile.service.TplSetRibbonService;
import net.aicoder.speedcloud.console.business.icode.tplfile.valid.TplSetValidator;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetAddDto;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetCondition;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetEditDto;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplSetVO;
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
 * 管理公共代码模板集合
 * @author icode
 */
@Api(description = "公共代码模板集合", tags = "TplSet")
@RestController
@RequestMapping(value = "/icode/tplfile/tplset")
public class TplSetController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TplSetController.class);
   
    @Autowired
	private TplSetRibbonService tplSetRibbonService;

	@Autowired
	private TplSetValidator tplSetValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(tplSetValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增公共代码模板集合
	 * @param tplSetAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增公共代码模板集合", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public TplSetVO add(@RequestBody @Valid TplSetAddDto tplSetAddDto){
	
		return  tplSetRibbonService.add(tplSetAddDto);
	}

	/**
	 * 删除公共代码模板集合,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除公共代码模板集合", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete tplSet :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			tplSetRibbonService.delete(id);
		}

	}

	/**
	 * 更新公共代码模板集合
	 * @param tplSetEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产公共代码模板集合(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public TplSetVO update(@RequestBody @Valid TplSetEditDto tplSetEditDto, @ApiParam(value = "要查询的公共代码模板集合id") @PathVariable String id){

		TplSetVO vo = tplSetRibbonService.merge(id, tplSetEditDto);

		return  vo;
	}

	/**
	 * 复制模板集合
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产公共代码模板集合(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}/copy")
	public TplSetVO copy(@ApiParam(value = "要查询的公共代码模板集合id") @PathVariable String id){

		TplSetVO vo = tplSetRibbonService.copy(id);

		return  vo;
	}

	/**
	 * 根据ID查询公共代码模板集合
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询公共代码模板集合", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public TplSetVO get(@ApiParam(value = "要查询的公共代码模板集合id") @PathVariable String id) {

		TplSetVO vo = tplSetRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询公共代码模板集合列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询公共代码模板集合列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = TplSetCondition.class)
	public PageContent<TplSetVO> list(@RequestBody @Valid PageSearchRequest<TplSetCondition> pageSearchRequest){

		PageContent<TplSetVO> pageContent = tplSetRibbonService.list(pageSearchRequest);
		for(TplSetVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出公共代码模板集合列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出公共代码模板集合列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(TplSetCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<TplSetCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<TplSetVO> content = this.list(pageSearchRequest);

        List<TplSetVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(TplSetVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("code" ,"集合代码");
            headMap.put("name" ,"集合名称");
            headMap.put("parentId" ,"parent_id");
            headMap.put("type" ,"集合类型");
            headMap.put("description" ,"集合描述");

        String title = new String("公共代码模板集合");
        String fileName = new String(("公共代码模板集合_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private TplSetVO initViewProperty( TplSetVO vo){


	   
        return vo;

	}
}
