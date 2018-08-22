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
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeCmp;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeCmpCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeCmpAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeCmpEditDto;
import net.aicoder.devp.business.publish.service.DevpSysOpsPipeCmpService;
import net.aicoder.devp.business.publish.valid.DevpSysOpsPipeCmpValidator;
import net.aicoder.devp.business.publish.vo.DevpSysOpsPipeCmpVO;

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
 * 管理产品运维流水线对应的组件
 * @author icode
 */
@Api(description = "产品运维流水线对应的组件", tags = "DevpSysOpsPipeCmp")
@RestController
@RequestMapping(value = "/publish/devpSysOpsPipeCmp")
public class DevpSysOpsPipeCmpController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipeCmpController.class);


	@Autowired
	private DevpSysOpsPipeCmpService devpSysOpsPipeCmpService;


	@Autowired
	private DevpSysOpsPipeCmpValidator devpSysOpsPipeCmpValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsPipeCmpValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增产品运维流水线对应的组件
	 * @param devpSysOpsPipeCmpAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品运维流水线对应的组件", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsPipeCmpVO add(@RequestBody @Valid DevpSysOpsPipeCmpAddDto devpSysOpsPipeCmpAddDto){
		DevpSysOpsPipeCmp devpSysOpsPipeCmp = new DevpSysOpsPipeCmp();
		BeanUtils.copyProperties(devpSysOpsPipeCmpAddDto, devpSysOpsPipeCmp);

		devpSysOpsPipeCmpService.add(devpSysOpsPipeCmp);

		return  initViewProperty(devpSysOpsPipeCmp);
	}

	/**
	 * 删除产品运维流水线对应的组件,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品运维流水线对应的组件", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsPipeCmp :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsPipeCmpService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品运维流水线对应的组件
	 * @param devpSysOpsPipeCmpEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品运维流水线对应的组件(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsPipeCmpVO update(@RequestBody @Valid DevpSysOpsPipeCmpEditDto devpSysOpsPipeCmpEditDto, @PathVariable Long id){
		DevpSysOpsPipeCmp devpSysOpsPipeCmp = new DevpSysOpsPipeCmp();
		BeanUtils.copyProperties(devpSysOpsPipeCmpEditDto, devpSysOpsPipeCmp);
		devpSysOpsPipeCmp.setId(id);
		devpSysOpsPipeCmpService.merge(devpSysOpsPipeCmp);

		DevpSysOpsPipeCmpVO vo = initViewProperty(devpSysOpsPipeCmp);
		return  vo;
	}

	/**
	 * 根据ID查询产品运维流水线对应的组件
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品运维流水线对应的组件", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsPipeCmpVO get(@PathVariable Long id) {

		DevpSysOpsPipeCmp devpSysOpsPipeCmp = devpSysOpsPipeCmpService.find(id);

		DevpSysOpsPipeCmpVO vo = initViewProperty(devpSysOpsPipeCmp);
		return vo;
	}

	/**
	 * 查询产品运维流水线对应的组件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品运维流水线对应的组件列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsPipeCmpVO> list(@RequestBody PageSearchRequest<DevpSysOpsPipeCmpCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysOpsPipeCmp> page = devpSysOpsPipeCmpService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsPipeCmpVO> voList = new ArrayList<>();
		for(DevpSysOpsPipeCmp devpSysOpsPipeCmp : page.getContent()){
			voList.add(initViewProperty(devpSysOpsPipeCmp));
		}

		PageContent<DevpSysOpsPipeCmpVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出产品运维流水线对应的组件列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出产品运维流水线对应的组件列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsPipeCmpCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsPipeCmpCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsPipeCmpVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsPipeCmpVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsPipeCmpVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("产品运维流水线对应的组件");
        String fileName = new String(("产品运维流水线对应的组件_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsPipeCmpVO initViewProperty(DevpSysOpsPipeCmp devpSysOpsPipeCmp){

	    DevpSysOpsPipeCmpVO vo = new DevpSysOpsPipeCmpVO();
        BeanUtils.copyProperties(devpSysOpsPipeCmp, vo);


	    //初始化其他对象
        return vo;


	}


}
