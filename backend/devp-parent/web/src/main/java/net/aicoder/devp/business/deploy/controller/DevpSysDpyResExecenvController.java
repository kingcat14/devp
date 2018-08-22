package net.aicoder.devp.business.deploy.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyResExecenv;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResExecenvCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResExecenvAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResExecenvEditDto;
import net.aicoder.devp.business.deploy.service.DevpSysDpyResExecenvService;
import net.aicoder.devp.business.deploy.valid.DevpSysDpyResExecenvValidator;
import net.aicoder.devp.business.deploy.vo.DevpSysDpyResExecenvVO;

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
 * 管理资源部署环境节点
 * @author icode
 */
@Api(description = "资源部署环境节点", tags = "DevpSysDpyResExecenv")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyResExecenv")
public class DevpSysDpyResExecenvController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResExecenvController.class);


	@Autowired
	private DevpSysDpyResExecenvService devpSysDpyResExecenvService;


	@Autowired
	private DevpSysDpyResExecenvValidator devpSysDpyResExecenvValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResExecenvValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增资源部署环境节点
	 * @param devpSysDpyResExecenvAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资源部署环境节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResExecenvVO add(@RequestBody @Valid DevpSysDpyResExecenvAddDto devpSysDpyResExecenvAddDto){
		DevpSysDpyResExecenv devpSysDpyResExecenv = new DevpSysDpyResExecenv();
		BeanUtils.copyProperties(devpSysDpyResExecenvAddDto, devpSysDpyResExecenv);

		devpSysDpyResExecenvService.add(devpSysDpyResExecenv);

		return  initViewProperty(devpSysDpyResExecenv);
	}

	/**
	 * 删除资源部署环境节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资源部署环境节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResExecenv :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResExecenvService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资源部署环境节点
	 * @param devpSysDpyResExecenvEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资源部署环境节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyResExecenvVO update(@RequestBody @Valid DevpSysDpyResExecenvEditDto devpSysDpyResExecenvEditDto, @PathVariable Long id){
		DevpSysDpyResExecenv devpSysDpyResExecenv = new DevpSysDpyResExecenv();
		BeanUtils.copyProperties(devpSysDpyResExecenvEditDto, devpSysDpyResExecenv);
		devpSysDpyResExecenv.setId(id);
		devpSysDpyResExecenvService.merge(devpSysDpyResExecenv);

		DevpSysDpyResExecenvVO vo = initViewProperty(devpSysDpyResExecenv);
		return  vo;
	}

	/**
	 * 根据ID查询资源部署环境节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资源部署环境节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyResExecenvVO get(@PathVariable Long id) {

		DevpSysDpyResExecenv devpSysDpyResExecenv = devpSysDpyResExecenvService.find(id);

		DevpSysDpyResExecenvVO vo = initViewProperty(devpSysDpyResExecenv);
		return vo;
	}

	/**
	 * 查询资源部署环境节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资源部署环境节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResExecenvVO> list(@RequestBody PageSearchRequest<DevpSysDpyResExecenvCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyResExecenv> page = devpSysDpyResExecenvService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyResExecenvVO> voList = new ArrayList<>();
		for(DevpSysDpyResExecenv devpSysDpyResExecenv : page.getContent()){
			voList.add(initViewProperty(devpSysDpyResExecenv));
		}

		PageContent<DevpSysDpyResExecenvVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出资源部署环境节点列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出资源部署环境节点列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysDpyResExecenvCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysDpyResExecenvCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyResExecenvVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyResExecenvVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyResExecenvVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("资源部署环境节点");
        String fileName = new String(("资源部署环境节点_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysDpyResExecenvVO initViewProperty(DevpSysDpyResExecenv devpSysDpyResExecenv){

	    DevpSysDpyResExecenvVO vo = new DevpSysDpyResExecenvVO();
        BeanUtils.copyProperties(devpSysDpyResExecenv, vo);


	    //初始化其他对象
        return vo;


	}


}
