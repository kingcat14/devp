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
import net.aicoder.devp.business.publish.domain.DevpSysOpsDockerParam;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamEditDto;
import net.aicoder.devp.business.publish.service.DevpSysOpsDockerParamService;
import net.aicoder.devp.business.publish.valid.DevpSysOpsDockerParamValidator;
import net.aicoder.devp.business.publish.vo.DevpSysOpsDockerParamVO;

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
 * 管理部署容器参数定义
 * @author icode
 */
@Api(description = "部署容器参数定义", tags = "DevpSysOpsDockerParam")
@RestController
@RequestMapping(value = "/publish/devpSysOpsDockerParam")
public class DevpSysOpsDockerParamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsDockerParamController.class);


	@Autowired
	private DevpSysOpsDockerParamService devpSysOpsDockerParamService;


	@Autowired
	private DevpSysOpsDockerParamValidator devpSysOpsDockerParamValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsDockerParamValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署容器参数定义
	 * @param devpSysOpsDockerParamAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署容器参数定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsDockerParamVO add(@RequestBody @Valid DevpSysOpsDockerParamAddDto devpSysOpsDockerParamAddDto){
		DevpSysOpsDockerParam devpSysOpsDockerParam = new DevpSysOpsDockerParam();
		BeanUtils.copyProperties(devpSysOpsDockerParamAddDto, devpSysOpsDockerParam);

		devpSysOpsDockerParamService.add(devpSysOpsDockerParam);

		return  initViewProperty(devpSysOpsDockerParam);
	}

	/**
	 * 删除部署容器参数定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署容器参数定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsDockerParam :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsDockerParamService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署容器参数定义
	 * @param devpSysOpsDockerParamEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署容器参数定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsDockerParamVO update(@RequestBody @Valid DevpSysOpsDockerParamEditDto devpSysOpsDockerParamEditDto, @PathVariable Long id){
		DevpSysOpsDockerParam devpSysOpsDockerParam = new DevpSysOpsDockerParam();
		BeanUtils.copyProperties(devpSysOpsDockerParamEditDto, devpSysOpsDockerParam);
		devpSysOpsDockerParam.setId(id);
		devpSysOpsDockerParamService.merge(devpSysOpsDockerParam);

		DevpSysOpsDockerParamVO vo = initViewProperty(devpSysOpsDockerParam);
		return  vo;
	}

	/**
	 * 根据ID查询部署容器参数定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署容器参数定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsDockerParamVO get(@PathVariable Long id) {

		DevpSysOpsDockerParam devpSysOpsDockerParam = devpSysOpsDockerParamService.find(id);

		DevpSysOpsDockerParamVO vo = initViewProperty(devpSysOpsDockerParam);
		return vo;
	}

	/**
	 * 查询部署容器参数定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署容器参数定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsDockerParamVO> list(@RequestBody PageSearchRequest<DevpSysOpsDockerParamCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysOpsDockerParam> page = devpSysOpsDockerParamService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsDockerParamVO> voList = new ArrayList<>();
		for(DevpSysOpsDockerParam devpSysOpsDockerParam : page.getContent()){
			voList.add(initViewProperty(devpSysOpsDockerParam));
		}

		PageContent<DevpSysOpsDockerParamVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出部署容器参数定义列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署容器参数定义列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsDockerParamCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsDockerParamCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsDockerParamVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsDockerParamVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsDockerParamVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("部署容器参数定义");
        String fileName = new String(("部署容器参数定义_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsDockerParamVO initViewProperty(DevpSysOpsDockerParam devpSysOpsDockerParam){

	    DevpSysOpsDockerParamVO vo = new DevpSysOpsDockerParamVO();
        BeanUtils.copyProperties(devpSysOpsDockerParam, vo);


	    //初始化其他对象
        return vo;


	}


}
