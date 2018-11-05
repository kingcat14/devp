package net.aicoder.devp.business.publish.controller;

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
import net.aicoder.devp.business.publish.domain.DevpSysOpsDockerAccess;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerAccessCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerAccessAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerAccessEditDto;
import net.aicoder.devp.business.publish.service.DevpSysOpsDockerAccessService;
import net.aicoder.devp.business.publish.valid.DevpSysOpsDockerAccessValidator;
import net.aicoder.devp.business.publish.vo.DevpSysOpsDockerAccessVO;

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
 * 管理部署容器访问参数
 * @author icode
 */
@Api(description = "部署容器访问参数", tags = "DevpSysOpsDockerAccess")
@RestController
@RequestMapping(value = "/publish/devpSysOpsDockerAccess")
public class DevpSysOpsDockerAccessController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsDockerAccessController.class);


	@Autowired
	private DevpSysOpsDockerAccessService devpSysOpsDockerAccessService;


	@Autowired
	private DevpSysOpsDockerAccessValidator devpSysOpsDockerAccessValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsDockerAccessValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署容器访问参数
	 * @param devpSysOpsDockerAccessAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署容器访问参数", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsDockerAccessVO add(@RequestBody @Valid DevpSysOpsDockerAccessAddDto devpSysOpsDockerAccessAddDto){
		DevpSysOpsDockerAccess devpSysOpsDockerAccess = new DevpSysOpsDockerAccess();
		BeanUtils.copyProperties(devpSysOpsDockerAccessAddDto, devpSysOpsDockerAccess);

		devpSysOpsDockerAccessService.add(devpSysOpsDockerAccess);

		return  initViewProperty(devpSysOpsDockerAccess);
	}

	/**
	 * 删除部署容器访问参数,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署容器访问参数", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsDockerAccess :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsDockerAccessService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署容器访问参数
	 * @param devpSysOpsDockerAccessEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署容器访问参数(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsDockerAccessVO update(@RequestBody @Valid DevpSysOpsDockerAccessEditDto devpSysOpsDockerAccessEditDto, @PathVariable Long id){
		DevpSysOpsDockerAccess devpSysOpsDockerAccess = new DevpSysOpsDockerAccess();
		BeanUtils.copyProperties(devpSysOpsDockerAccessEditDto, devpSysOpsDockerAccess);
		devpSysOpsDockerAccess.setId(id);
		devpSysOpsDockerAccessService.merge(devpSysOpsDockerAccess);

		DevpSysOpsDockerAccessVO vo = initViewProperty(devpSysOpsDockerAccess);
		return  vo;
	}

	/**
	 * 根据ID查询部署容器访问参数
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署容器访问参数", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsDockerAccessVO get(@PathVariable Long id) {

		DevpSysOpsDockerAccess devpSysOpsDockerAccess = devpSysOpsDockerAccessService.find(id);

		DevpSysOpsDockerAccessVO vo = initViewProperty(devpSysOpsDockerAccess);
		return vo;
	}

	/**
	 * 查询部署容器访问参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署容器访问参数列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsDockerAccessVO> list(@RequestBody PageSearchRequest<DevpSysOpsDockerAccessCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<DevpSysOpsDockerAccess> page = devpSysOpsDockerAccessService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsDockerAccessVO> voList = new ArrayList<>();
		for(DevpSysOpsDockerAccess devpSysOpsDockerAccess : page.getContent()){
			voList.add(initViewProperty(devpSysOpsDockerAccess));
		}

		PageContent<DevpSysOpsDockerAccessVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出部署容器访问参数列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署容器访问参数列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsDockerAccessCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsDockerAccessCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsDockerAccessVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsDockerAccessVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsDockerAccessVO vo : voList){
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
            headMap.put("notes" ,"备注");
            headMap.put("servicesPort" ,"服务端口");
            headMap.put("dockerPort" ,"服务端口");
            headMap.put("protocol" ,"协议");
            headMap.put("autoAssign" ,"自动分配");
            headMap.put("hostPort" ,"自动分配");
            headMap.put("prdRid" ,"产品编号");
            headMap.put("schemeRid" ,"部署方案编号");
            headMap.put("cmpRid" ,"组件编号");
            headMap.put("taskRid" ,"任务编号");
            headMap.put("dockerRid" ,"容器编号");
            headMap.put("seq" ,"顺序号");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("modifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("部署容器访问参数");
        String fileName = new String(("部署容器访问参数_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsDockerAccessVO initViewProperty(DevpSysOpsDockerAccess devpSysOpsDockerAccess){

	    DevpSysOpsDockerAccessVO vo = new DevpSysOpsDockerAccessVO();
        BeanUtils.copyProperties(devpSysOpsDockerAccess, vo);


	    //初始化其他对象
        return vo;


	}


}
