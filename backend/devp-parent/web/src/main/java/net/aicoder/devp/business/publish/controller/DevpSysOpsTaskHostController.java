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
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskHost;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskHostCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskHostAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskHostEditDto;
import net.aicoder.devp.business.publish.service.DevpSysOpsTaskHostService;
import net.aicoder.devp.business.publish.valid.DevpSysOpsTaskHostValidator;
import net.aicoder.devp.business.publish.vo.DevpSysOpsTaskHostVO;

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
@Api(description = "部署主机节点", tags = "DevpSysOpsTaskHost")
@RestController
@RequestMapping(value = "/publish/devpSysOpsTaskHost")
public class DevpSysOpsTaskHostController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsTaskHostController.class);


	@Autowired
	private DevpSysOpsTaskHostService devpSysOpsTaskHostService;


	@Autowired
	private DevpSysOpsTaskHostValidator devpSysOpsTaskHostValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsTaskHostValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署主机节点
	 * @param devpSysOpsTaskHostAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署主机节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsTaskHostVO add(@RequestBody @Valid DevpSysOpsTaskHostAddDto devpSysOpsTaskHostAddDto){
		DevpSysOpsTaskHost devpSysOpsTaskHost = new DevpSysOpsTaskHost();
		BeanUtils.copyProperties(devpSysOpsTaskHostAddDto, devpSysOpsTaskHost);

		devpSysOpsTaskHostService.add(devpSysOpsTaskHost);

		return  initViewProperty(devpSysOpsTaskHost);
	}

	/**
	 * 删除部署主机节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署主机节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsTaskHost :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsTaskHostService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署主机节点
	 * @param devpSysOpsTaskHostEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署主机节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsTaskHostVO update(@RequestBody @Valid DevpSysOpsTaskHostEditDto devpSysOpsTaskHostEditDto, @PathVariable Long id){
		DevpSysOpsTaskHost devpSysOpsTaskHost = new DevpSysOpsTaskHost();
		BeanUtils.copyProperties(devpSysOpsTaskHostEditDto, devpSysOpsTaskHost);
		devpSysOpsTaskHost.setId(id);
		devpSysOpsTaskHostService.merge(devpSysOpsTaskHost);

		DevpSysOpsTaskHostVO vo = initViewProperty(devpSysOpsTaskHost);
		return  vo;
	}

	/**
	 * 根据ID查询部署主机节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署主机节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsTaskHostVO get(@PathVariable Long id) {

		DevpSysOpsTaskHost devpSysOpsTaskHost = devpSysOpsTaskHostService.find(id);

		DevpSysOpsTaskHostVO vo = initViewProperty(devpSysOpsTaskHost);
		return vo;
	}

	/**
	 * 查询部署主机节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署主机节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsTaskHostVO> list(@RequestBody PageSearchRequest<DevpSysOpsTaskHostCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysOpsTaskHost> page = devpSysOpsTaskHostService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsTaskHostVO> voList = new ArrayList<>();
		for(DevpSysOpsTaskHost devpSysOpsTaskHost : page.getContent()){
			voList.add(initViewProperty(devpSysOpsTaskHost));
		}

		PageContent<DevpSysOpsTaskHostVO> pageContent = new PageContent<>(voList, page.getTotalElements());
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
    public void export(DevpSysOpsTaskHostCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsTaskHostCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsTaskHostVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsTaskHostVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsTaskHostVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("部署主机节点");
        String fileName = new String(("部署主机节点_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsTaskHostVO initViewProperty(DevpSysOpsTaskHost devpSysOpsTaskHost){

	    DevpSysOpsTaskHostVO vo = new DevpSysOpsTaskHostVO();
        BeanUtils.copyProperties(devpSysOpsTaskHost, vo);


	    //初始化其他对象
        return vo;


	}


}
