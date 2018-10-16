package net.aicoder.speedcloud.console.business.speedcloud.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryCondition;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryEditDto;
import net.aicoder.speedcloud.business.app.vo.CodeRepositoryVO;
import net.aicoder.speedcloud.console.business.speedcloud.app.service.CodeRepositoryRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.app.valid.CodeRepositoryValidator;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理代码库
 * @author icode
 */
@Api(description = "代码库", tags = "CodeRepository")
@RestController
@RequestMapping(value = "/speedcloud/app/coderepository")
public class CodeRepositoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeRepositoryController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private CodeRepositoryRibbonService codeRepositoryRibbonService;

	@Autowired
	CodeRepositoryValidator codeRepositoryValidator;

    @Autowired
    private SimpleConfigService simpleConfigService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(codeRepositoryValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增代码库
	 * @param codeRepositoryAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增代码库", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public CodeRepositoryVO add(@RequestBody CodeRepositoryAddDto codeRepositoryAddDto){
    	codeRepositoryAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  codeRepositoryRibbonService.add(codeRepositoryAddDto);
	}

	/**
	 * 删除代码库,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除代码库", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete codeRepository :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			codeRepositoryRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新代码库
	 * @param codeRepositoryEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产代码库(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public CodeRepositoryVO update(@RequestBody CodeRepositoryEditDto codeRepositoryEditDto, @ApiParam(value = "要查询的代码库id") @PathVariable Long id){

		CodeRepositoryVO vo = codeRepositoryRibbonService.merge(id, codeRepositoryEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询代码库
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询代码库", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public CodeRepositoryVO get(@ApiParam(value = "要查询的代码库id") @PathVariable Long id) {

		CodeRepositoryVO vo = codeRepositoryRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询代码库列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询代码库列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<CodeRepositoryVO> list(@RequestBody PageSearchRequest<CodeRepositoryCondition> pageSearchRequest){

		CodeRepositoryCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new CodeRepositoryCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<CodeRepositoryVO> pageContent = codeRepositoryRibbonService.list(pageSearchRequest);
		for(CodeRepositoryVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出代码库列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出代码库列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(CodeRepositoryCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<CodeRepositoryCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<CodeRepositoryVO> content = this.list(pageSearchRequest);

        List<CodeRepositoryVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(CodeRepositoryVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"名称");
            headMap.put("type" ,"类型");
            headMap.put("url" ,"url");
            headMap.put("developType" ,"开发模式");
            headMap.put("username" ,"用户名");
            headMap.put("description" ,"描述");

        String title = new String("代码库");
        String fileName = new String(("代码库_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private CodeRepositoryVO initViewProperty( CodeRepositoryVO vo){

	   

		SimpleConfig typeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("CODEREPOSITORY-TYPE", vo.getType());

		if(typeSimpleConfig!=null) {

		    SimpleConfigVO typeSimpleConfigVO = new SimpleConfigVO();
		    BeanUtils.copyProperties(typeSimpleConfig, typeSimpleConfigVO);
		    vo.setTypeVO(typeSimpleConfigVO);
		}

	   
        return vo;

	}


}
