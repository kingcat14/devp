package net.aicoder.speedcloud.business.config.controller;

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
import net.aicoder.speedcloud.business.config.domain.CodeRepositoryType;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeAddDto;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeCondition;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeEditDto;
import net.aicoder.speedcloud.business.config.service.CodeRepositoryTypeService;
import net.aicoder.speedcloud.business.config.valid.CodeRepositoryTypeValidator;
import net.aicoder.speedcloud.business.config.vo.CodeRepositoryTypeVO;
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
 * 管理代码库类型
 * @author icode
 */
@Api(description = "代码库类型", tags = "CodeRepositoryType")
@RestController
@RequestMapping(value = "/speedcloud/config/coderepositorytype")
public class CodeRepositoryTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeRepositoryTypeController.class);


	@Autowired
	private CodeRepositoryTypeService codeRepositoryTypeService;



	@Autowired
	private CodeRepositoryTypeValidator codeRepositoryTypeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(codeRepositoryTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增代码库类型
	 * @param codeRepositoryTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增代码库类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "speedcloud.config.coderepositorytype.add", count = true)
	public CodeRepositoryTypeVO add(@RequestBody @Valid CodeRepositoryTypeAddDto codeRepositoryTypeAddDto){
		CodeRepositoryType codeRepositoryType = new CodeRepositoryType();
		BeanUtils.copyProperties(codeRepositoryTypeAddDto, codeRepositoryType);

		codeRepositoryTypeService.add(codeRepositoryType);

		return  initViewProperty(codeRepositoryType);
	}

	/**
	 * 删除代码库类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除代码库类型", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "speedcloud.config.coderepositorytype.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete codeRepositoryType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			codeRepositoryTypeService.delete(id);
		}

	}

	/**
	 * 更新代码库类型
	 * @param codeRepositoryTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改代码库类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.config.coderepositorytype.update", count = true)
	public	CodeRepositoryTypeVO update(@RequestBody @Valid CodeRepositoryTypeEditDto codeRepositoryTypeEditDto, @PathVariable String id){
		CodeRepositoryType codeRepositoryType = codeRepositoryTypeService.find(id);
		BeanUtils.copyProperties(codeRepositoryTypeEditDto, codeRepositoryType);
		codeRepositoryType.setId(id);
		codeRepositoryTypeService.merge(codeRepositoryType);

		CodeRepositoryTypeVO vo = initViewProperty(codeRepositoryType);
		return  vo;
	}

	/**
	 * 根据ID查询代码库类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询", notes = "根据ID查询代码库类型", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.config.coderepositorytype.get")
	public  CodeRepositoryTypeVO get(@PathVariable String id) {

		CodeRepositoryType codeRepositoryType = codeRepositoryTypeService.find(id);
		if(codeRepositoryType == null){
			throw new ResourceNotFoundException("找不到指定的代码库类型，请检查ID");
		}
		CodeRepositoryTypeVO vo = initViewProperty(codeRepositoryType);
		return vo;
	}

	/**
	 * 查询代码库类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询代码库类型列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "speedcloud.config.coderepositorytype.list")
	public PageContent<CodeRepositoryTypeVO> list(@RequestBody PageSearchRequest<CodeRepositoryTypeCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<CodeRepositoryType> page = codeRepositoryTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<CodeRepositoryTypeVO> voList = new ArrayList<>();
		for(CodeRepositoryType codeRepositoryType : page.getContent()){
			voList.add(initViewProperty(codeRepositoryType));
		}

		PageContent<CodeRepositoryTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出代码库类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出代码库类型列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(CodeRepositoryTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<CodeRepositoryTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<CodeRepositoryTypeVO> content = this.list(pageSearchRequest);

        List<CodeRepositoryTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(CodeRepositoryTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("code" ,"代码");
        headMap.put("name" ,"名称");

        String title = new String("代码库类型");
        String fileName = new String(("代码库类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private CodeRepositoryTypeVO initViewProperty(CodeRepositoryType codeRepositoryType){

	    CodeRepositoryTypeVO vo = new CodeRepositoryTypeVO();
        BeanUtils.copyProperties(codeRepositoryType, vo);


	    //初始化其他对象
        return vo;

	}


}

