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
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskDocker;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerEditDto;
import net.aicoder.devp.business.publish.service.DevpSysOpsTaskDockerService;
import net.aicoder.devp.business.publish.valid.DevpSysOpsTaskDockerValidator;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskDockerVO;

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
 * 管理部署容器
 * @author icode
 */
@Api(description = "部署容器", tags = "DevpSysOpsTaskDocker")
@RestController
@RequestMapping(value = "/publish/devpSysOpsTaskDocker")
public class DevpSysOpsTaskDockerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskDockerController.class);


	@Autowired
	private DevpSysOpsTaskDockerService devpSysOpsTaskDockerService;


	@Autowired
	private DevpSysOpsTaskDockerValidator devpSysOpsTaskDockerValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsTaskDockerValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署容器
	 * @param devpSysOpsTaskDockerAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署容器", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsTaskDockerVO add(@RequestBody @Valid DevpSysOpsTaskDockerAddDto devpSysOpsTaskDockerAddDto){
		DevpSysOpsTaskDocker devpSysOpsTaskDocker = new DevpSysOpsTaskDocker();
		BeanUtils.copyProperties(devpSysOpsTaskDockerAddDto, devpSysOpsTaskDocker);

		devpSysOpsTaskDockerService.add(devpSysOpsTaskDocker);

		return  initViewProperty(devpSysOpsTaskDocker);
	}

	/**
	 * 删除部署容器,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署容器", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsTaskDocker :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsTaskDockerService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署容器
	 * @param devpSysOpsTaskDockerEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署容器(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsTaskDockerVO update(@RequestBody @Valid DevpSysOpsTaskDockerEditDto devpSysOpsTaskDockerEditDto, @PathVariable Long id){
		DevpSysOpsTaskDocker devpSysOpsTaskDocker = new DevpSysOpsTaskDocker();
		BeanUtils.copyProperties(devpSysOpsTaskDockerEditDto, devpSysOpsTaskDocker);
		devpSysOpsTaskDocker.setId(id);
		devpSysOpsTaskDockerService.merge(devpSysOpsTaskDocker);

		DevpSysOpsTaskDockerVO vo = initViewProperty(devpSysOpsTaskDocker);
		return  vo;
	}

	/**
	 * 根据ID查询部署容器
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署容器", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsTaskDockerVO get(@PathVariable Long id) {

		DevpSysOpsTaskDocker devpSysOpsTaskDocker = devpSysOpsTaskDockerService.find(id);

		DevpSysOpsTaskDockerVO vo = initViewProperty(devpSysOpsTaskDocker);
		return vo;
	}

	/**
	 * 查询部署容器列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署容器列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsTaskDockerVO> list(@RequestBody PageSearchRequest<DevpSysOpsTaskDockerCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysOpsTaskDocker> page = devpSysOpsTaskDockerService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsTaskDockerVO> voList = new ArrayList<>();
		for(DevpSysOpsTaskDocker devpSysOpsTaskDocker : page.getContent()){
			voList.add(initViewProperty(devpSysOpsTaskDocker));
		}

		PageContent<DevpSysOpsTaskDockerVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出部署容器列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署容器列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsTaskDockerCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsTaskDockerCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsTaskDockerVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsTaskDockerVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsTaskDockerVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("部署容器");
        String fileName = new String(("部署容器_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsTaskDockerVO initViewProperty(DevpSysOpsTaskDocker devpSysOpsTaskDocker){

	    DevpSysOpsTaskDockerVO vo = new DevpSysOpsTaskDockerVO();
        BeanUtils.copyProperties(devpSysOpsTaskDocker, vo);


	    //初始化其他对象
        return vo;


	}


}
