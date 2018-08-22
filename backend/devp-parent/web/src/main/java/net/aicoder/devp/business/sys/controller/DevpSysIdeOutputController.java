package net.aicoder.devp.business.sys.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.sys.domain.DevpSysIdeOutput;
import net.aicoder.devp.business.sys.dto.DevpSysIdeOutputCondition;
import net.aicoder.devp.business.sys.dto.DevpSysIdeOutputAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysIdeOutputEditDto;
import net.aicoder.devp.business.sys.service.DevpSysIdeOutputService;
import net.aicoder.devp.business.sys.valid.DevpSysIdeOutputValidator;
import net.aicoder.devp.business.sys.vo.DevpSysIdeOutputVO;

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
 * 管理开发工程产出组件
 * @author icode
 */
@Api(description = "开发工程产出组件", tags = "DevpSysIdeOutput")
@RestController
@RequestMapping(value = "/sys/devpSysIdeOutput")
public class DevpSysIdeOutputController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysIdeOutputController.class);


	@Autowired
	private DevpSysIdeOutputService devpSysIdeOutputService;


	@Autowired
	private DevpSysIdeOutputValidator devpSysIdeOutputValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysIdeOutputValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增开发工程产出组件
	 * @param devpSysIdeOutputAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增开发工程产出组件", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysIdeOutputVO add(@RequestBody @Valid DevpSysIdeOutputAddDto devpSysIdeOutputAddDto){
		DevpSysIdeOutput devpSysIdeOutput = new DevpSysIdeOutput();
		BeanUtils.copyProperties(devpSysIdeOutputAddDto, devpSysIdeOutput);

		devpSysIdeOutputService.add(devpSysIdeOutput);

		return  initViewProperty(devpSysIdeOutput);
	}

	/**
	 * 删除开发工程产出组件,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除开发工程产出组件", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysIdeOutput :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysIdeOutputService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新开发工程产出组件
	 * @param devpSysIdeOutputEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产开发工程产出组件(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysIdeOutputVO update(@RequestBody @Valid DevpSysIdeOutputEditDto devpSysIdeOutputEditDto, @PathVariable Long id){
		DevpSysIdeOutput devpSysIdeOutput = new DevpSysIdeOutput();
		BeanUtils.copyProperties(devpSysIdeOutputEditDto, devpSysIdeOutput);
		devpSysIdeOutput.setId(id);
		devpSysIdeOutputService.merge(devpSysIdeOutput);

		DevpSysIdeOutputVO vo = initViewProperty(devpSysIdeOutput);
		return  vo;
	}

	/**
	 * 根据ID查询开发工程产出组件
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询开发工程产出组件", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysIdeOutputVO get(@PathVariable Long id) {

		DevpSysIdeOutput devpSysIdeOutput = devpSysIdeOutputService.find(id);

		DevpSysIdeOutputVO vo = initViewProperty(devpSysIdeOutput);
		return vo;
	}

	/**
	 * 查询开发工程产出组件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询开发工程产出组件列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysIdeOutputVO> list(@RequestBody PageSearchRequest<DevpSysIdeOutputCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysIdeOutput> page = devpSysIdeOutputService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysIdeOutputVO> voList = new ArrayList<>();
		for(DevpSysIdeOutput devpSysIdeOutput : page.getContent()){
			voList.add(initViewProperty(devpSysIdeOutput));
		}

		PageContent<DevpSysIdeOutputVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出开发工程产出组件列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出开发工程产出组件列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysIdeOutputCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysIdeOutputCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysIdeOutputVO> content = this.list(pageSearchRequest);

        List<DevpSysIdeOutputVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysIdeOutputVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("开发工程产出组件");
        String fileName = new String(("开发工程产出组件_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysIdeOutputVO initViewProperty(DevpSysIdeOutput devpSysIdeOutput){

	    DevpSysIdeOutputVO vo = new DevpSysIdeOutputVO();
        BeanUtils.copyProperties(devpSysIdeOutput, vo);


	    //初始化其他对象
        return vo;


	}


}
