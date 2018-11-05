package net.aicoder.devp.business.product.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.product.domain.DevpPrdPrdline;
import net.aicoder.devp.business.product.dto.DevpPrdPrdlineCondition;
import net.aicoder.devp.business.product.dto.DevpPrdPrdlineAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdPrdlineEditDto;
import net.aicoder.devp.business.product.service.DevpPrdPrdlineService;
import net.aicoder.devp.business.product.valid.DevpPrdPrdlineValidator;
import net.aicoder.devp.business.product.vo.DevpPrdPrdlineVO;

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
 * 管理产品线
 * @author icode
 */
@Api(description = "产品线", tags = "DevpPrdPrdline")
@RestController
@RequestMapping(value = "/product/devpPrdPrdline")
public class DevpPrdPrdlineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdPrdlineController.class);


	@Autowired
	private DevpPrdPrdlineService devpPrdPrdlineService;


	@Autowired
	private DevpPrdPrdlineValidator devpPrdPrdlineValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpPrdPrdlineValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增产品线
	 * @param devpPrdPrdlineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品线", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpPrdPrdlineVO add(@RequestBody @Valid DevpPrdPrdlineAddDto devpPrdPrdlineAddDto){
		DevpPrdPrdline devpPrdPrdline = new DevpPrdPrdline();
		BeanUtils.copyProperties(devpPrdPrdlineAddDto, devpPrdPrdline);

		devpPrdPrdlineService.add(devpPrdPrdline);

		return  initViewProperty(devpPrdPrdline);
	}

	/**
	 * 删除产品线,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品线", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpPrdPrdline :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpPrdPrdlineService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品线
	 * @param devpPrdPrdlineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品线(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpPrdPrdlineVO update(@RequestBody @Valid DevpPrdPrdlineEditDto devpPrdPrdlineEditDto, @PathVariable Long id){
		DevpPrdPrdline devpPrdPrdline = new DevpPrdPrdline();
		BeanUtils.copyProperties(devpPrdPrdlineEditDto, devpPrdPrdline);
		devpPrdPrdline.setId(id);
		devpPrdPrdlineService.merge(devpPrdPrdline);

		DevpPrdPrdlineVO vo = initViewProperty(devpPrdPrdline);
		return  vo;
	}

	/**
	 * 根据ID查询产品线
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品线", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpPrdPrdlineVO get(@PathVariable Long id) {

		DevpPrdPrdline devpPrdPrdline = devpPrdPrdlineService.find(id);

		DevpPrdPrdlineVO vo = initViewProperty(devpPrdPrdline);
		return vo;
	}

	/**
	 * 查询产品线列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品线列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpPrdPrdlineVO> list(@RequestBody PageSearchRequest<DevpPrdPrdlineCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<DevpPrdPrdline> page = devpPrdPrdlineService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpPrdPrdlineVO> voList = new ArrayList<>();
		for(DevpPrdPrdline devpPrdPrdline : page.getContent()){
			voList.add(initViewProperty(devpPrdPrdline));
		}

		PageContent<DevpPrdPrdlineVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出产品线列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出产品线列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpPrdPrdlineCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpPrdPrdlineCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpPrdPrdlineVO> content = this.list(pageSearchRequest);

        List<DevpPrdPrdlineVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpPrdPrdlineVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("tid" ,"租户编号");
            headMap.put("etype" ,"etype");
            headMap.put("name" ,"产品线名称");
            headMap.put("code" ,"产品线代码");
            headMap.put("alias" ,"产品线别名");
            headMap.put("description" ,"产品线描述");
            headMap.put("type" ,"产品线类型");
            headMap.put("domain" ,"领域");
            headMap.put("stereotype" ,"构造型");
            headMap.put("scope" ,"访问控制范围");
            headMap.put("version" ,"版本");
            headMap.put("phase" ,"阶段");
            headMap.put("status" ,"状态");
            headMap.put("parentRid" ,"父产品线编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("recordState" ,"记录状态");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("modifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("产品线");
        String fileName = new String(("产品线_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpPrdPrdlineVO initViewProperty(DevpPrdPrdline devpPrdPrdline){

	    DevpPrdPrdlineVO vo = new DevpPrdPrdlineVO();
        BeanUtils.copyProperties(devpPrdPrdline, vo);


	    //初始化其他对象
        return vo;


	}


}
