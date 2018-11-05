package net.aicoder.devp.business.deploy.controller;

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
import net.aicoder.devp.business.deploy.domain.DevpSysDpyHost;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostEditDto;
import net.aicoder.devp.business.deploy.service.DevpSysDpyHostService;
import net.aicoder.devp.business.deploy.valid.DevpSysDpyHostValidator;
import net.aicoder.devp.business.deploy.vo.DevpSysDpyHostVO;

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
 * 管理部署主机节点
 * @author icode
 */
@Api(description = "部署主机节点", tags = "DevpSysDpyHost")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyHost")
public class DevpSysDpyHostController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyHostController.class);


	@Autowired
	private DevpSysDpyHostService devpSysDpyHostService;


	@Autowired
	private DevpSysDpyHostValidator devpSysDpyHostValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyHostValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署主机节点
	 * @param devpSysDpyHostAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署主机节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyHostVO add(@RequestBody @Valid DevpSysDpyHostAddDto devpSysDpyHostAddDto){
		DevpSysDpyHost devpSysDpyHost = new DevpSysDpyHost();
		BeanUtils.copyProperties(devpSysDpyHostAddDto, devpSysDpyHost);

		devpSysDpyHostService.add(devpSysDpyHost);

		return  initViewProperty(devpSysDpyHost);
	}

	/**
	 * 删除部署主机节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署主机节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyHost :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyHostService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署主机节点
	 * @param devpSysDpyHostEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署主机节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyHostVO update(@RequestBody @Valid DevpSysDpyHostEditDto devpSysDpyHostEditDto, @PathVariable Long id){
		DevpSysDpyHost devpSysDpyHost = new DevpSysDpyHost();
		BeanUtils.copyProperties(devpSysDpyHostEditDto, devpSysDpyHost);
		devpSysDpyHost.setId(id);
		devpSysDpyHostService.merge(devpSysDpyHost);

		DevpSysDpyHostVO vo = initViewProperty(devpSysDpyHost);
		return  vo;
	}

	/**
	 * 根据ID查询部署主机节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署主机节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyHostVO get(@PathVariable Long id) {

		DevpSysDpyHost devpSysDpyHost = devpSysDpyHostService.find(id);

		DevpSysDpyHostVO vo = initViewProperty(devpSysDpyHost);
		return vo;
	}

	/**
	 * 查询部署主机节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署主机节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyHostVO> list(@RequestBody PageSearchRequest<DevpSysDpyHostCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<DevpSysDpyHost> page = devpSysDpyHostService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyHostVO> voList = new ArrayList<>();
		for(DevpSysDpyHost devpSysDpyHost : page.getContent()){
			voList.add(initViewProperty(devpSysDpyHost));
		}

		PageContent<DevpSysDpyHostVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出部署主机节点列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署主机节点列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysDpyHostCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysDpyHostCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyHostVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyHostVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyHostVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("tid" ,"租户编号");
            headMap.put("etype" ,"元素类型");
            headMap.put("name" ,"系统元素名称");
            headMap.put("code" ,"系统元素代码");
            headMap.put("alias" ,"系统元素别名");
            headMap.put("description" ,"系统元素描述");
            headMap.put("recordState" ,"记录状态");
            headMap.put("flag" ,"主机标识");
            headMap.put("type" ,"类型");
            headMap.put("subType" ,"子类型");
            headMap.put("status" ,"状态");
            headMap.put("intAccessAddr" ,"内部访问地址");
            headMap.put("extAccessAddr" ,"外部访问地址");
            headMap.put("notes" ,"备注");
            headMap.put("prdRid" ,"产品编号");
            headMap.put("schemeRid" ,"部署方案编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("assetRid" ,"关联IT资产编号");
            headMap.put("assetEtype" ,"关联IT资产元素类型");
            headMap.put("assetTypeCode" ,"关联IT资产类型代码");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("modifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("部署主机节点");
        String fileName = new String(("部署主机节点_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysDpyHostVO initViewProperty(DevpSysDpyHost devpSysDpyHost){

	    DevpSysDpyHostVO vo = new DevpSysDpyHostVO();
        BeanUtils.copyProperties(devpSysDpyHost, vo);


	    //初始化其他对象
        return vo;


	}


}
