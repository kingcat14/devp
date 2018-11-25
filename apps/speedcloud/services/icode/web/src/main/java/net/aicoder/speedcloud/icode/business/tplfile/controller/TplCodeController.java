package net.aicoder.speedcloud.icode.business.tplfile.controller;

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
import net.aicoder.speedcloud.icode.business.tplfile.domain.TplCode;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeAddDto;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeCondition;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeEditDto;
import net.aicoder.speedcloud.icode.business.tplfile.service.TplCodeService;
import net.aicoder.speedcloud.icode.business.tplfile.valid.TplCodeValidator;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplCodeVO;
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
 * 管理公共代码模板
 * @author icode
 */
@Api(description = "公共代码模板", tags = "TplCode")
@RestController
@RequestMapping(value = "/icode/tplfile/tplcode")
public class TplCodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TplCodeController.class);


	@Autowired
	private TplCodeService tplCodeService;



	@Autowired
	private TplCodeValidator tplCodeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(tplCodeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增公共代码模板
	 * @param tplCodeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增公共代码模板", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "icode.tplfile.tplcode.add")
	public TplCodeVO add(@RequestBody @Valid TplCodeAddDto tplCodeAddDto){
		TplCode tplCode = new TplCode();
		BeanUtils.copyProperties(tplCodeAddDto, tplCode);

		tplCodeService.add(tplCode);

		return  initViewProperty(tplCode);
	}

	/**
	 * 删除公共代码模板,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除公共代码模板", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "icode.tplfile.tplcode.delete")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete tplCode :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			tplCodeService.delete(id);
		}

	}

	/**
	 * 更新公共代码模板
	 * @param tplCodeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产公共代码模板(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.tplfile.tplcode.update")
	public	TplCodeVO update(@RequestBody @Valid TplCodeEditDto tplCodeEditDto, @PathVariable String id){
		TplCode tplCode = new TplCode();
		BeanUtils.copyProperties(tplCodeEditDto, tplCode);
		tplCode.setId(id);
		tplCodeService.merge(tplCode);

		TplCodeVO vo = initViewProperty(tplCode);
		return  vo;
	}

	/**
	 * 根据ID复制公共代码模板
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询公共代码模板", httpMethod = "GET")
	@PutMapping(path="/{id}/copy")
	@BusinessFuncMonitor(value = "icode.tplfile.tplcode.copy")
	public  TplCodeVO copy(@PathVariable String id) {

		TplCode tplCode = tplCodeService.copy(id);
		if(tplCode == null){
			throw new ResourceNotFoundException("无法复制指定的公共代码模板，请检查ID");
		}
		TplCodeVO vo = initViewProperty(tplCode);
		return vo;
	}

	/**
	 * 根据ID查询公共代码模板
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询公共代码模板", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "icode.tplfile.tplcode.get")
	public  TplCodeVO get(@PathVariable String id) {

		TplCode tplCode = tplCodeService.find(id);
		if(tplCode == null){
			throw new ResourceNotFoundException("找不到指定的公共代码模板，请检查ID");
		}
		TplCodeVO vo = initViewProperty(tplCode);
		return vo;
	}

	/**
	 * 查询公共代码模板列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询公共代码模板列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "icode.tplfile.tplcode.list")
	public PageContent<TplCodeVO> list(@RequestBody PageSearchRequest<TplCodeCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<TplCode> page = tplCodeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<TplCodeVO> voList = new ArrayList<>();
		for(TplCode tplCode : page.getContent()){
			voList.add(initViewProperty(tplCode));
		}

		PageContent<TplCodeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出公共代码模板列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出公共代码模板列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(TplCodeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<TplCodeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<TplCodeVO> content = this.list(pageSearchRequest);

        List<TplCodeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(TplCodeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("code" ,"模板代码");
        headMap.put("name" ,"模板名称");
        headMap.put("type" ,"模板类型");
        headMap.put("content" ,"模板内容");
        headMap.put("fileName" ,"文件名称");
        headMap.put("filePath" ,"文件路径");
        headMap.put("tplSet" ,"模板集合");
        headMap.put("acceptModelType" ,"接受的模型类型");
        headMap.put("overridable" ,"是否可覆盖");

        String title = new String("公共代码模板");
        String fileName = new String(("公共代码模板_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private TplCodeVO initViewProperty(TplCode tplCode){

	    TplCodeVO vo = new TplCodeVO();
        BeanUtils.copyProperties(tplCode, vo);


	    //初始化其他对象
        return vo;

	}


}

