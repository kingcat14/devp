package net.aicoder.devp.business.publish.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskDeploy;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployEditDto;
import net.aicoder.devp.business.publish.service.DevpSysOpsTaskDeployService;
import net.aicoder.devp.business.publish.valid.DevpSysOpsTaskDeployValidator;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskDeployVO;

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
 * 管理组件部署设置
 * @author icode
 */
@Api(description = "组件部署设置", tags = "DevpSysOpsTaskDeploy")
@RestController
@RequestMapping(value = "/publish/devpSysOpsTaskDeploy")
public class DevpSysOpsTaskDeployController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskDeployController.class);


	@Autowired
	private DevpSysOpsTaskDeployService devpSysOpsTaskDeployService;


	@Autowired
	private DevpSysOpsTaskDeployValidator devpSysOpsTaskDeployValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsTaskDeployValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增组件部署设置
	 * @param devpSysOpsTaskDeployAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增组件部署设置", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsTaskDeployVO add(@RequestBody @Valid DevpSysOpsTaskDeployAddDto devpSysOpsTaskDeployAddDto){
		DevpSysOpsTaskDeploy devpSysOpsTaskDeploy = new DevpSysOpsTaskDeploy();
		BeanUtils.copyProperties(devpSysOpsTaskDeployAddDto, devpSysOpsTaskDeploy);

		devpSysOpsTaskDeployService.add(devpSysOpsTaskDeploy);

		return  initViewProperty(devpSysOpsTaskDeploy);
	}

	/**
	 * 删除组件部署设置,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除组件部署设置", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsTaskDeploy :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsTaskDeployService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新组件部署设置
	 * @param devpSysOpsTaskDeployEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产组件部署设置(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsTaskDeployVO update(@RequestBody @Valid DevpSysOpsTaskDeployEditDto devpSysOpsTaskDeployEditDto, @PathVariable Long id){
		DevpSysOpsTaskDeploy devpSysOpsTaskDeploy = new DevpSysOpsTaskDeploy();
		BeanUtils.copyProperties(devpSysOpsTaskDeployEditDto, devpSysOpsTaskDeploy);
		devpSysOpsTaskDeploy.setId(id);
		devpSysOpsTaskDeployService.merge(devpSysOpsTaskDeploy);

		DevpSysOpsTaskDeployVO vo = initViewProperty(devpSysOpsTaskDeploy);
		return  vo;
	}

	/**
	 * 根据ID查询组件部署设置
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询组件部署设置", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsTaskDeployVO get(@PathVariable Long id) {

		DevpSysOpsTaskDeploy devpSysOpsTaskDeploy = devpSysOpsTaskDeployService.find(id);

		DevpSysOpsTaskDeployVO vo = initViewProperty(devpSysOpsTaskDeploy);
		return vo;
	}

	/**
	 * 查询组件部署设置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询组件部署设置列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsTaskDeployVO> list(@RequestBody PageSearchRequest<DevpSysOpsTaskDeployCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysOpsTaskDeploy> page = devpSysOpsTaskDeployService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsTaskDeployVO> voList = new ArrayList<>();
		for(DevpSysOpsTaskDeploy devpSysOpsTaskDeploy : page.getContent()){
			voList.add(initViewProperty(devpSysOpsTaskDeploy));
		}

		PageContent<DevpSysOpsTaskDeployVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出组件部署设置列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出组件部署设置列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsTaskDeployCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsTaskDeployCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsTaskDeployVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsTaskDeployVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsTaskDeployVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("组件部署设置");
        String fileName = new String(("组件部署设置_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsTaskDeployVO initViewProperty(DevpSysOpsTaskDeploy devpSysOpsTaskDeploy){

	    DevpSysOpsTaskDeployVO vo = new DevpSysOpsTaskDeployVO();
        BeanUtils.copyProperties(devpSysOpsTaskDeploy, vo);


	    //初始化其他对象
        return vo;


	}


}
