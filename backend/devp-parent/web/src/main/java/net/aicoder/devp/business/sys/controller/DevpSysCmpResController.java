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
import net.aicoder.devp.business.sys.domain.DevpSysCmpRes;
import net.aicoder.devp.business.sys.dto.DevpSysCmpResCondition;
import net.aicoder.devp.business.sys.dto.DevpSysCmpResAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysCmpResEditDto;
import net.aicoder.devp.business.sys.service.DevpSysCmpResService;
import net.aicoder.devp.business.sys.valid.DevpSysCmpResValidator;
import net.aicoder.devp.business.sys.vo.DevpSysCmpResVO;

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
 * 管理组件关联资源
 * @author icode
 */
@Api(description = "组件关联资源", tags = "DevpSysCmpRes")
@RestController
@RequestMapping(value = "/sys/devpSysCmpRes")
public class DevpSysCmpResController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysCmpResController.class);


	@Autowired
	private DevpSysCmpResService devpSysCmpResService;


	@Autowired
	private DevpSysCmpResValidator devpSysCmpResValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysCmpResValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增组件关联资源
	 * @param devpSysCmpResAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增组件关联资源", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysCmpResVO add(@RequestBody @Valid DevpSysCmpResAddDto devpSysCmpResAddDto){
		DevpSysCmpRes devpSysCmpRes = new DevpSysCmpRes();
		BeanUtils.copyProperties(devpSysCmpResAddDto, devpSysCmpRes);

		devpSysCmpResService.add(devpSysCmpRes);

		return  initViewProperty(devpSysCmpRes);
	}

	/**
	 * 删除组件关联资源,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除组件关联资源", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysCmpRes :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysCmpResService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新组件关联资源
	 * @param devpSysCmpResEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产组件关联资源(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysCmpResVO update(@RequestBody @Valid DevpSysCmpResEditDto devpSysCmpResEditDto, @PathVariable Long id){
		DevpSysCmpRes devpSysCmpRes = new DevpSysCmpRes();
		BeanUtils.copyProperties(devpSysCmpResEditDto, devpSysCmpRes);
		devpSysCmpRes.setId(id);
		devpSysCmpResService.merge(devpSysCmpRes);

		DevpSysCmpResVO vo = initViewProperty(devpSysCmpRes);
		return  vo;
	}

	/**
	 * 根据ID查询组件关联资源
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询组件关联资源", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysCmpResVO get(@PathVariable Long id) {

		DevpSysCmpRes devpSysCmpRes = devpSysCmpResService.find(id);

		DevpSysCmpResVO vo = initViewProperty(devpSysCmpRes);
		return vo;
	}

	/**
	 * 查询组件关联资源列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询组件关联资源列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysCmpResVO> list(@RequestBody PageSearchRequest<DevpSysCmpResCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysCmpRes> page = devpSysCmpResService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysCmpResVO> voList = new ArrayList<>();
		for(DevpSysCmpRes devpSysCmpRes : page.getContent()){
			voList.add(initViewProperty(devpSysCmpRes));
		}

		PageContent<DevpSysCmpResVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出组件关联资源列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出组件关联资源列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysCmpResCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysCmpResCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysCmpResVO> content = this.list(pageSearchRequest);

        List<DevpSysCmpResVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysCmpResVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("组件关联资源");
        String fileName = new String(("组件关联资源_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysCmpResVO initViewProperty(DevpSysCmpRes devpSysCmpRes){

	    DevpSysCmpResVO vo = new DevpSysCmpResVO();
        BeanUtils.copyProperties(devpSysCmpRes, vo);


	    //初始化其他对象
        return vo;


	}


}
