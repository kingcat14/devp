package net.aicoder.speedcloud.business.app.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.app.domain.CodeRepertory;
import net.aicoder.speedcloud.business.app.dto.CodeRepertoryCondition;
import net.aicoder.speedcloud.business.app.dto.CodeRepertoryAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeRepertoryEditDto;
import net.aicoder.speedcloud.business.app.service.CodeRepertoryService;
import net.aicoder.speedcloud.business.app.valid.CodeRepertoryValidator;
import net.aicoder.speedcloud.business.app.vo.CodeRepertoryVO;
import net.aicoder.speedcloud.business.config.domain.DevelopType;
import net.aicoder.speedcloud.business.config.service.DevelopTypeService;
import net.aicoder.speedcloud.business.config.vo.DevelopTypeVO;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理代码库
 * @author icode
 */
@Api(description = "代码库", tags = "CodeRepertory")
@RestController
@RequestMapping(value = "/speedcloud/app/coderepertory")
public class CodeRepertoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeRepertoryController.class);


	@Autowired
	private CodeRepertoryService codeRepertoryService;

	@Autowired
	private DevelopTypeService developTypeService;
	

	@Autowired
	private CodeRepertoryValidator codeRepertoryValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(codeRepertoryValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增代码库
	 * @param codeRepertoryAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增代码库", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public CodeRepertoryVO add(@RequestBody @Valid CodeRepertoryAddDto codeRepertoryAddDto){
		CodeRepertory codeRepertory = new CodeRepertory();
		BeanUtils.copyProperties(codeRepertoryAddDto, codeRepertory);

		codeRepertoryService.add(codeRepertory);

		return  initViewProperty(codeRepertory);
	}

	/**
	 * 删除代码库,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除代码库", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete codeRepertory :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			codeRepertoryService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新代码库
	 * @param codeRepertoryEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产代码库(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	CodeRepertoryVO update(@RequestBody @Valid CodeRepertoryEditDto codeRepertoryEditDto, @PathVariable Long id){
		CodeRepertory codeRepertory = new CodeRepertory();
		BeanUtils.copyProperties(codeRepertoryEditDto, codeRepertory);
		codeRepertory.setId(id);
		codeRepertoryService.merge(codeRepertory);

		CodeRepertoryVO vo = initViewProperty(codeRepertory);
		return  vo;
	}

	/**
	 * 根据ID查询代码库
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询代码库", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  CodeRepertoryVO get(@PathVariable Long id) {

		CodeRepertory codeRepertory = codeRepertoryService.find(id);

		CodeRepertoryVO vo = initViewProperty(codeRepertory);
		return vo;
	}

	/**
	 * 查询代码库列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询代码库列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<CodeRepertoryVO> list(@RequestBody PageSearchRequest<CodeRepertoryCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<CodeRepertory> page = codeRepertoryService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<CodeRepertoryVO> voList = new ArrayList<>();
		for(CodeRepertory codeRepertory : page.getContent()){
			voList.add(initViewProperty(codeRepertory));
		}

		PageContent<CodeRepertoryVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(CodeRepertoryCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<CodeRepertoryCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<CodeRepertoryVO> content = this.list(pageSearchRequest);

        List<CodeRepertoryVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(CodeRepertoryVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("type" ,"类型");
            headMap.put("url" ,"url");
            headMap.put("developType" ,"开发模式");
            headMap.put("username" ,"用户名");
            headMap.put("password" ,"密码");

        String title = new String("代码库");
        String fileName = new String(("代码库_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private CodeRepertoryVO initViewProperty(CodeRepertory codeRepertory){

	    CodeRepertoryVO vo = new CodeRepertoryVO();
        BeanUtils.copyProperties(codeRepertory, vo);

	    //初始化其他对象
	    initDevelopTypePropertyGroup(vo, codeRepertory);
        return vo;

	}


	private void initDevelopTypePropertyGroup(CodeRepertoryVO codeRepertoryVO, CodeRepertory codeRepertory){
	
		DevelopType developType = developTypeService.find(codeRepertory.getDevelopType());
		if(developType == null){
			return;
		}
		DevelopTypeVO developTypeVO = new DevelopTypeVO();
		BeanUtils.copyProperties(developType, developTypeVO);

		codeRepertoryVO.setDevelopTypeVO(developTypeVO);

	}


}
