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
import net.aicoder.devp.business.publish.domain.DevpSysOpsDockerPath;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathEditDto;
import net.aicoder.devp.business.publish.service.DevpSysOpsDockerPathService;
import net.aicoder.devp.business.publish.valid.DevpSysOpsDockerPathValidator;
import net.aicoder.devp.business.publish.vo.DevpSysOpsDockerPathVO;

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
 * 管理存储路径定义
 * @author icode
 */
@Api(description = "存储路径定义", tags = "DevpSysOpsDockerPath")
@RestController
@RequestMapping(value = "/publish/devpSysOpsDockerPath")
public class DevpSysOpsDockerPathController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsDockerPathController.class);


	@Autowired
	private DevpSysOpsDockerPathService devpSysOpsDockerPathService;


	@Autowired
	private DevpSysOpsDockerPathValidator devpSysOpsDockerPathValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsDockerPathValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增存储路径定义
	 * @param devpSysOpsDockerPathAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增存储路径定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsDockerPathVO add(@RequestBody @Valid DevpSysOpsDockerPathAddDto devpSysOpsDockerPathAddDto){
		DevpSysOpsDockerPath devpSysOpsDockerPath = new DevpSysOpsDockerPath();
		BeanUtils.copyProperties(devpSysOpsDockerPathAddDto, devpSysOpsDockerPath);

		devpSysOpsDockerPathService.add(devpSysOpsDockerPath);

		return  initViewProperty(devpSysOpsDockerPath);
	}

	/**
	 * 删除存储路径定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除存储路径定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsDockerPath :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsDockerPathService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新存储路径定义
	 * @param devpSysOpsDockerPathEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产存储路径定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsDockerPathVO update(@RequestBody @Valid DevpSysOpsDockerPathEditDto devpSysOpsDockerPathEditDto, @PathVariable Long id){
		DevpSysOpsDockerPath devpSysOpsDockerPath = new DevpSysOpsDockerPath();
		BeanUtils.copyProperties(devpSysOpsDockerPathEditDto, devpSysOpsDockerPath);
		devpSysOpsDockerPath.setId(id);
		devpSysOpsDockerPathService.merge(devpSysOpsDockerPath);

		DevpSysOpsDockerPathVO vo = initViewProperty(devpSysOpsDockerPath);
		return  vo;
	}

	/**
	 * 根据ID查询存储路径定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询存储路径定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsDockerPathVO get(@PathVariable Long id) {

		DevpSysOpsDockerPath devpSysOpsDockerPath = devpSysOpsDockerPathService.find(id);

		DevpSysOpsDockerPathVO vo = initViewProperty(devpSysOpsDockerPath);
		return vo;
	}

	/**
	 * 查询存储路径定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询存储路径定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsDockerPathVO> list(@RequestBody PageSearchRequest<DevpSysOpsDockerPathCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysOpsDockerPath> page = devpSysOpsDockerPathService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsDockerPathVO> voList = new ArrayList<>();
		for(DevpSysOpsDockerPath devpSysOpsDockerPath : page.getContent()){
			voList.add(initViewProperty(devpSysOpsDockerPath));
		}

		PageContent<DevpSysOpsDockerPathVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出存储路径定义列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出存储路径定义列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsDockerPathCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsDockerPathCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsDockerPathVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsDockerPathVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsDockerPathVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("存储路径定义");
        String fileName = new String(("存储路径定义_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsDockerPathVO initViewProperty(DevpSysOpsDockerPath devpSysOpsDockerPath){

	    DevpSysOpsDockerPathVO vo = new DevpSysOpsDockerPathVO();
        BeanUtils.copyProperties(devpSysOpsDockerPath, vo);


	    //初始化其他对象
        return vo;


	}


}
