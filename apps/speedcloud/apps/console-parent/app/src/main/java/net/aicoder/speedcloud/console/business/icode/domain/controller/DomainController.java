package net.aicoder.speedcloud.console.business.icode.domain.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.console.business.icode.domain.service.DomainRibbonService;
import net.aicoder.speedcloud.console.business.icode.domain.valid.DomainValidator;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.DomainVO;
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
 * 管理领域
 * @author icode
 */
@Api(description = "领域", tags = "Domain")
@RestController
@RequestMapping(value = "/icode/domain/domain")
public class DomainController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainController.class);

    @Autowired
	private SaaSUtil saaSUtil;
	
    @Autowired
	private DomainRibbonService domainRibbonService;

	@Autowired
	private DomainValidator domainValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(domainValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增领域
	 * @param domainAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增领域", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@SaaSAnnotation()
	public DomainVO add(@RequestBody @Valid DomainAddDto domainAddDto){
	
		return  domainRibbonService.add(domainAddDto);
	}

	/**
	 * 删除领域,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除领域", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete domain :{}", idArray);

		String[] ids = idArray.split(",");
      	for (String id : ids ){
			domainRibbonService.delete(id);
		}

	}

	/**
	 * 更新领域
	 * @param domainEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产领域(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
	public DomainVO update(@RequestBody @Valid DomainEditDto domainEditDto, @ApiParam(value = "要查询的领域id") @PathVariable String id){

		DomainVO vo = domainRibbonService.merge(id, domainEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询领域
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询领域", httpMethod = "GET")
	@GetMapping(path="/{id}")
	public DomainVO get(@ApiParam(value = "要查询的领域id") @PathVariable String id) {

		DomainVO vo = domainRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询领域列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询领域列表", httpMethod = "POST")
	@PostMapping(path="/list")
  	@SaaSAnnotation(conditionClass = DomainCondition.class)
	public PageContent<DomainVO> list(@RequestBody @Valid PageSearchRequest<DomainCondition> pageSearchRequest){

		PageContent<DomainVO> pageContent = domainRibbonService.list(pageSearchRequest);
		for(DomainVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出领域列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出领域列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(DomainCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<DomainCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DomainVO> content = this.list(pageSearchRequest);

        List<DomainVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DomainVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

    
            headMap.put("name" ,"领域名称");
            headMap.put("code" ,"领域代码");
            headMap.put("parent" ,"父领域");
            headMap.put("prefix" ,"领域代码前缀");

        String title = new String("领域");
        String fileName = new String(("领域_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private DomainVO initViewProperty( DomainVO vo){


	   
        return vo;

	}
}
