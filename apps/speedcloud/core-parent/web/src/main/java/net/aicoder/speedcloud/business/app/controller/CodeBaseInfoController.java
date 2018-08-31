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
import net.aicoder.speedcloud.business.app.domain.CodeBaseInfo;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoCondition;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoEditDto;
import net.aicoder.speedcloud.business.app.service.CodeBaseInfoService;
import net.aicoder.speedcloud.business.app.valid.CodeBaseInfoValidator;
import net.aicoder.speedcloud.business.app.vo.CodeBaseInfoVO;
import net.aicoder.speedcloud.business.app.domain.CodeRepository;
import net.aicoder.speedcloud.business.app.service.CodeRepositoryService;
import net.aicoder.speedcloud.business.app.vo.CodeRepositoryVO;


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
 * 管理代码库详细信息
 * @author icode
 */
@Api(description = "代码库详细信息", tags = "CodeBaseInfo")
@RestController
@RequestMapping(value = "/speedcloud/app/codebaseinfo")
public class CodeBaseInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeBaseInfoController.class);


	@Autowired
	private CodeBaseInfoService codeBaseInfoService;

	@Autowired
	private CodeRepositoryService codeRepositoryService;


	@Autowired
	private CodeBaseInfoValidator codeBaseInfoValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(codeBaseInfoValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增代码库详细信息
	 * @param codeBaseInfoAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增代码库详细信息", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public CodeBaseInfoVO add(@RequestBody @Valid CodeBaseInfoAddDto codeBaseInfoAddDto){
		CodeBaseInfo codeBaseInfo = new CodeBaseInfo();
		BeanUtils.copyProperties(codeBaseInfoAddDto, codeBaseInfo);

		codeBaseInfoService.add(codeBaseInfo);

		return  initViewProperty(codeBaseInfo);
	}

	/**
	 * 删除代码库详细信息,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除代码库详细信息", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete codeBaseInfo :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			codeBaseInfoService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新代码库详细信息
	 * @param codeBaseInfoEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产代码库详细信息(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	CodeBaseInfoVO update(@RequestBody @Valid CodeBaseInfoEditDto codeBaseInfoEditDto, @PathVariable Long id){
		CodeBaseInfo codeBaseInfo = new CodeBaseInfo();
		BeanUtils.copyProperties(codeBaseInfoEditDto, codeBaseInfo);
		codeBaseInfo.setId(id);
		codeBaseInfoService.merge(codeBaseInfo);

		CodeBaseInfoVO vo = initViewProperty(codeBaseInfo);
		return  vo;
	}

	/**
	 * 根据ID查询代码库详细信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询代码库详细信息", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  CodeBaseInfoVO get(@PathVariable Long id) {

		CodeBaseInfo codeBaseInfo = codeBaseInfoService.find(id);

		CodeBaseInfoVO vo = initViewProperty(codeBaseInfo);
		return vo;
	}

	/**
	 * 查询代码库详细信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询代码库详细信息列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<CodeBaseInfoVO> list(@RequestBody PageSearchRequest<CodeBaseInfoCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<CodeBaseInfo> page = codeBaseInfoService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<CodeBaseInfoVO> voList = new ArrayList<>();
		for(CodeBaseInfo codeBaseInfo : page.getContent()){
			voList.add(initViewProperty(codeBaseInfo));
		}

		PageContent<CodeBaseInfoVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出代码库详细信息列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出代码库详细信息列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(CodeBaseInfoCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<CodeBaseInfoCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<CodeBaseInfoVO> content = this.list(pageSearchRequest);

        List<CodeBaseInfoVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(CodeBaseInfoVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("codeRepertory" ,"代码库");
            headMap.put("language" ,"开发语言");
            headMap.put("languageLevel" ,"语言级别");

        String title = new String("代码库详细信息");
        String fileName = new String(("代码库详细信息_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private CodeBaseInfoVO initViewProperty(CodeBaseInfo codeBaseInfo){

	    CodeBaseInfoVO vo = new CodeBaseInfoVO();
        BeanUtils.copyProperties(codeBaseInfo, vo);


	    //初始化其他对象
	    initCodeRepositoryPropertyGroup(vo, codeBaseInfo);
        return vo;

	}


	private void initCodeRepositoryPropertyGroup(CodeBaseInfoVO codeBaseInfoVO, CodeBaseInfo codeBaseInfo){
	
		CodeRepository codeRepertory = codeRepositoryService.find(codeBaseInfo.getCodeRepository());
		if(codeRepertory == null){
			return;
		}
		CodeRepositoryVO codeRepertoryVO = new CodeRepositoryVO();
		BeanUtils.copyProperties(codeRepertory, codeRepertoryVO);

		codeBaseInfoVO.setCodeRepositoryVO(codeRepertoryVO);

	}


}

