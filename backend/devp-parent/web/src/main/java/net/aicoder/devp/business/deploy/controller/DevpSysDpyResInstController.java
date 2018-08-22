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
import net.aicoder.devp.business.deploy.domain.DevpSysDpyResInst;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstEditDto;
import net.aicoder.devp.business.deploy.service.DevpSysDpyResInstService;
import net.aicoder.devp.business.deploy.valid.DevpSysDpyResInstValidator;
import net.aicoder.devp.business.deploy.vo.DevpSysDpyResInstVO;

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
 * 管理部署关联资源实例
 * @author icode
 */
@Api(description = "部署关联资源实例", tags = "DevpSysDpyResInst")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyResInst")
public class DevpSysDpyResInstController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResInstController.class);


	@Autowired
	private DevpSysDpyResInstService devpSysDpyResInstService;


	@Autowired
	private DevpSysDpyResInstValidator devpSysDpyResInstValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResInstValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增部署关联资源实例
	 * @param devpSysDpyResInstAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署关联资源实例", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResInstVO add(@RequestBody @Valid DevpSysDpyResInstAddDto devpSysDpyResInstAddDto){
		DevpSysDpyResInst devpSysDpyResInst = new DevpSysDpyResInst();
		BeanUtils.copyProperties(devpSysDpyResInstAddDto, devpSysDpyResInst);

		devpSysDpyResInstService.add(devpSysDpyResInst);

		return  initViewProperty(devpSysDpyResInst);
	}

	/**
	 * 删除部署关联资源实例,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署关联资源实例", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResInst :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResInstService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新部署关联资源实例
	 * @param devpSysDpyResInstEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署关联资源实例(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyResInstVO update(@RequestBody @Valid DevpSysDpyResInstEditDto devpSysDpyResInstEditDto, @PathVariable Long id){
		DevpSysDpyResInst devpSysDpyResInst = new DevpSysDpyResInst();
		BeanUtils.copyProperties(devpSysDpyResInstEditDto, devpSysDpyResInst);
		devpSysDpyResInst.setId(id);
		devpSysDpyResInstService.merge(devpSysDpyResInst);

		DevpSysDpyResInstVO vo = initViewProperty(devpSysDpyResInst);
		return  vo;
	}

	/**
	 * 根据ID查询部署关联资源实例
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署关联资源实例", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyResInstVO get(@PathVariable Long id) {

		DevpSysDpyResInst devpSysDpyResInst = devpSysDpyResInstService.find(id);

		DevpSysDpyResInstVO vo = initViewProperty(devpSysDpyResInst);
		return vo;
	}

	/**
	 * 查询部署关联资源实例列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署关联资源实例列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResInstVO> list(@RequestBody PageSearchRequest<DevpSysDpyResInstCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyResInst> page = devpSysDpyResInstService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyResInstVO> voList = new ArrayList<>();
		for(DevpSysDpyResInst devpSysDpyResInst : page.getContent()){
			voList.add(initViewProperty(devpSysDpyResInst));
		}

		PageContent<DevpSysDpyResInstVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出部署关联资源实例列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出部署关联资源实例列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysDpyResInstCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysDpyResInstCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyResInstVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyResInstVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyResInstVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("部署关联资源实例");
        String fileName = new String(("部署关联资源实例_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysDpyResInstVO initViewProperty(DevpSysDpyResInst devpSysDpyResInst){

	    DevpSysDpyResInstVO vo = new DevpSysDpyResInstVO();
        BeanUtils.copyProperties(devpSysDpyResInst, vo);


	    //初始化其他对象
        return vo;


	}


}
