package net.aicoder.maintenance.business.devp.publish.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.platform.business.application.security.SaaSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeEditDto;
import net.aicoder.devp.business.publish.vo.DevpSysOpsPipeNodeVO;
import net.aicoder.maintenance.business.devp.publish.service.DevpSysOpsPipeNodeRibbonService;
import net.aicoder.maintenance.business.devp.publish.valid.DevpSysOpsPipeNodeValidator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理流水线执行节点
 * @author icode
 */
@Api(description = "流水线执行节点", tags = "DevpSysOpsPipeNode")
@RestController
@RequestMapping(value = "/publish/devpSysOpsPipeNode")
public class DevpSysOpsPipeNodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipeNodeController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private DevpSysOpsPipeNodeRibbonService devpSysOpsPipeNodeRibbonService;

	@Autowired
	DevpSysOpsPipeNodeValidator devpSysOpsPipeNodeValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsPipeNodeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增流水线执行节点
	 * @param devpSysOpsPipeNodeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增流水线执行节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsPipeNodeVO add(@RequestBody DevpSysOpsPipeNodeAddDto devpSysOpsPipeNodeAddDto){
		devpSysOpsPipeNodeAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return  devpSysOpsPipeNodeRibbonService.add(devpSysOpsPipeNodeAddDto);
	}

	/**
	 * 删除流水线执行节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除流水线执行节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsPipeNode :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsPipeNodeRibbonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新流水线执行节点
	 * @param devpSysOpsPipeNodeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产流水线执行节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public DevpSysOpsPipeNodeVO update(@RequestBody DevpSysOpsPipeNodeEditDto devpSysOpsPipeNodeEditDto, @ApiParam(value = "要查询的流水线执行节点id") @PathVariable Long id){

		DevpSysOpsPipeNodeVO vo = devpSysOpsPipeNodeRibbonService.merge(id, devpSysOpsPipeNodeEditDto);

		return  vo;
	}

	/**
	 * 根据ID查询流水线执行节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询流水线执行节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public DevpSysOpsPipeNodeVO get(@ApiParam(value = "要查询的流水线执行节点id") @PathVariable Long id) {

		DevpSysOpsPipeNodeVO vo = devpSysOpsPipeNodeRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询流水线执行节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询流水线执行节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsPipeNodeVO> list(@RequestBody PageSearchRequest<DevpSysOpsPipeNodeCondition> pageSearchRequest){

		DevpSysOpsPipeNodeCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new DevpSysOpsPipeNodeCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
		pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<DevpSysOpsPipeNodeVO> pageContent = devpSysOpsPipeNodeRibbonService.list(pageSearchRequest);
		for(DevpSysOpsPipeNodeVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出流水线执行节点列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出流水线执行节点列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsPipeNodeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException  {

        PageSearchRequest<DevpSysOpsPipeNodeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsPipeNodeVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsPipeNodeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsPipeNodeVO vo : voList){
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
            headMap.put("type" ,"类型");
            headMap.put("subType" ,"子类型");
            headMap.put("execModel" ,"执行方式");
            headMap.put("execSeq" ,"执行顺序");
            headMap.put("returnCode" ,"返回码");
            headMap.put("execStatus" ,"执行状态");
            headMap.put("notes" ,"备注");
            headMap.put("prdRid" ,"产品编号");
            headMap.put("schemeRid" ,"部署方案编号");
            headMap.put("pipelineRid" ,"流水线编号");
            headMap.put("cmpRid" ,"组件编号");
            headMap.put("taskRid" ,"任务编号");
            headMap.put("parentRid" ,"父节点编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("defaultPipeline" ,"缺省选中流水线");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("modifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("流水线执行节点");
        String fileName = new String(("流水线执行节点_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);

    }


	private DevpSysOpsPipeNodeVO initViewProperty( DevpSysOpsPipeNodeVO vo){

	   


	   
        return vo;

	}


}
