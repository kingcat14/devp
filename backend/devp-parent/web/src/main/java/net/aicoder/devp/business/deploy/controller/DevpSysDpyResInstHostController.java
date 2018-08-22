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
import net.aicoder.devp.business.deploy.domain.DevpSysDpyResInstHost;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostEditDto;
import net.aicoder.devp.business.deploy.service.DevpSysDpyResInstHostService;
import net.aicoder.devp.business.deploy.valid.DevpSysDpyResInstHostValidator;
import net.aicoder.devp.business.deploy.vo.DevpSysDpyResInstHostVO;

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
 * 管理资源实例部署主机节点
 * @author icode
 */
@Api(description = "资源实例部署主机节点", tags = "DevpSysDpyResInstHost")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyResInstHost")
public class DevpSysDpyResInstHostController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResInstHostController.class);


	@Autowired
	private DevpSysDpyResInstHostService devpSysDpyResInstHostService;


	@Autowired
	private DevpSysDpyResInstHostValidator devpSysDpyResInstHostValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResInstHostValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增资源实例部署主机节点
	 * @param devpSysDpyResInstHostAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资源实例部署主机节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResInstHostVO add(@RequestBody @Valid DevpSysDpyResInstHostAddDto devpSysDpyResInstHostAddDto){
		DevpSysDpyResInstHost devpSysDpyResInstHost = new DevpSysDpyResInstHost();
		BeanUtils.copyProperties(devpSysDpyResInstHostAddDto, devpSysDpyResInstHost);

		devpSysDpyResInstHostService.add(devpSysDpyResInstHost);

		return  initViewProperty(devpSysDpyResInstHost);
	}

	/**
	 * 删除资源实例部署主机节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资源实例部署主机节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResInstHost :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResInstHostService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资源实例部署主机节点
	 * @param devpSysDpyResInstHostEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资源实例部署主机节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyResInstHostVO update(@RequestBody @Valid DevpSysDpyResInstHostEditDto devpSysDpyResInstHostEditDto, @PathVariable Long id){
		DevpSysDpyResInstHost devpSysDpyResInstHost = new DevpSysDpyResInstHost();
		BeanUtils.copyProperties(devpSysDpyResInstHostEditDto, devpSysDpyResInstHost);
		devpSysDpyResInstHost.setId(id);
		devpSysDpyResInstHostService.merge(devpSysDpyResInstHost);

		DevpSysDpyResInstHostVO vo = initViewProperty(devpSysDpyResInstHost);
		return  vo;
	}

	/**
	 * 根据ID查询资源实例部署主机节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资源实例部署主机节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyResInstHostVO get(@PathVariable Long id) {

		DevpSysDpyResInstHost devpSysDpyResInstHost = devpSysDpyResInstHostService.find(id);

		DevpSysDpyResInstHostVO vo = initViewProperty(devpSysDpyResInstHost);
		return vo;
	}

	/**
	 * 查询资源实例部署主机节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资源实例部署主机节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResInstHostVO> list(@RequestBody PageSearchRequest<DevpSysDpyResInstHostCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyResInstHost> page = devpSysDpyResInstHostService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyResInstHostVO> voList = new ArrayList<>();
		for(DevpSysDpyResInstHost devpSysDpyResInstHost : page.getContent()){
			voList.add(initViewProperty(devpSysDpyResInstHost));
		}

		PageContent<DevpSysDpyResInstHostVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出资源实例部署主机节点列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出资源实例部署主机节点列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysDpyResInstHostCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysDpyResInstHostCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyResInstHostVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyResInstHostVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyResInstHostVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("资源实例部署主机节点");
        String fileName = new String(("资源实例部署主机节点_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysDpyResInstHostVO initViewProperty(DevpSysDpyResInstHost devpSysDpyResInstHost){

	    DevpSysDpyResInstHostVO vo = new DevpSysDpyResInstHostVO();
        BeanUtils.copyProperties(devpSysDpyResInstHost, vo);


	    //初始化其他对象
        return vo;


	}


}
