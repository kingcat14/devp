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
import net.aicoder.devp.business.sys.domain.DevpSysCmp;
import net.aicoder.devp.business.sys.dto.DevpSysCmpCondition;
import net.aicoder.devp.business.sys.dto.DevpSysCmpAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysCmpEditDto;
import net.aicoder.devp.business.sys.service.DevpSysCmpService;
import net.aicoder.devp.business.sys.valid.DevpSysCmpValidator;
import net.aicoder.devp.business.sys.vo.DevpSysCmpVO;

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
 * 管理系统组件
 * @author icode
 */
@Api(description = "系统组件", tags = "DevpSysCmp")
@RestController
@RequestMapping(value = "/sys/devpSysCmp")
public class DevpSysCmpController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysCmpController.class);


	@Autowired
	private DevpSysCmpService devpSysCmpService;


	@Autowired
	private DevpSysCmpValidator devpSysCmpValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysCmpValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增系统组件
	 * @param devpSysCmpAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统组件", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysCmpVO add(@RequestBody @Valid DevpSysCmpAddDto devpSysCmpAddDto){
		DevpSysCmp devpSysCmp = new DevpSysCmp();
		BeanUtils.copyProperties(devpSysCmpAddDto, devpSysCmp);

		devpSysCmpService.add(devpSysCmp);

		return  initViewProperty(devpSysCmp);
	}

	/**
	 * 删除系统组件,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统组件", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysCmp :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysCmpService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统组件
	 * @param devpSysCmpEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统组件(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysCmpVO update(@RequestBody @Valid DevpSysCmpEditDto devpSysCmpEditDto, @PathVariable Long id){
		DevpSysCmp devpSysCmp = new DevpSysCmp();
		BeanUtils.copyProperties(devpSysCmpEditDto, devpSysCmp);
		devpSysCmp.setId(id);
		devpSysCmpService.merge(devpSysCmp);

		DevpSysCmpVO vo = initViewProperty(devpSysCmp);
		return  vo;
	}

	/**
	 * 根据ID查询系统组件
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统组件", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysCmpVO get(@PathVariable Long id) {

		DevpSysCmp devpSysCmp = devpSysCmpService.find(id);

		DevpSysCmpVO vo = initViewProperty(devpSysCmp);
		return vo;
	}

	/**
	 * 查询系统组件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统组件列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysCmpVO> list(@RequestBody PageSearchRequest<DevpSysCmpCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysCmp> page = devpSysCmpService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysCmpVO> voList = new ArrayList<>();
		for(DevpSysCmp devpSysCmp : page.getContent()){
			voList.add(initViewProperty(devpSysCmp));
		}

		PageContent<DevpSysCmpVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出系统组件列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出系统组件列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysCmpCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysCmpCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysCmpVO> content = this.list(pageSearchRequest);

        List<DevpSysCmpVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysCmpVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("系统组件");
        String fileName = new String(("系统组件_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysCmpVO initViewProperty(DevpSysCmp devpSysCmp){

	    DevpSysCmpVO vo = new DevpSysCmpVO();
        BeanUtils.copyProperties(devpSysCmp, vo);


	    //初始化其他对象
        return vo;


	}


}
