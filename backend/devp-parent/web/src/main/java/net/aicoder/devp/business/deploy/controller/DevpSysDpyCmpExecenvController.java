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
import net.aicoder.devp.business.deploy.domain.DevpSysDpyCmpExecenv;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpExecenvCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpExecenvAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpExecenvEditDto;
import net.aicoder.devp.business.deploy.service.DevpSysDpyCmpExecenvService;
import net.aicoder.devp.business.deploy.valid.DevpSysDpyCmpExecenvValidator;
import net.aicoder.devp.business.deploy.vo.DevpSysDpyCmpExecenvVO;

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
 * 管理组件部署环境节点
 * @author icode
 */
@Api(description = "组件部署环境节点", tags = "DevpSysDpyCmpExecenv")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyCmpExecenv")
public class DevpSysDpyCmpExecenvController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyCmpExecenvController.class);


	@Autowired
	private DevpSysDpyCmpExecenvService devpSysDpyCmpExecenvService;


	@Autowired
	private DevpSysDpyCmpExecenvValidator devpSysDpyCmpExecenvValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyCmpExecenvValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增组件部署环境节点
	 * @param devpSysDpyCmpExecenvAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增组件部署环境节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyCmpExecenvVO add(@RequestBody @Valid DevpSysDpyCmpExecenvAddDto devpSysDpyCmpExecenvAddDto){
		DevpSysDpyCmpExecenv devpSysDpyCmpExecenv = new DevpSysDpyCmpExecenv();
		BeanUtils.copyProperties(devpSysDpyCmpExecenvAddDto, devpSysDpyCmpExecenv);

		devpSysDpyCmpExecenvService.add(devpSysDpyCmpExecenv);

		return  initViewProperty(devpSysDpyCmpExecenv);
	}

	/**
	 * 删除组件部署环境节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除组件部署环境节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyCmpExecenv :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyCmpExecenvService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新组件部署环境节点
	 * @param devpSysDpyCmpExecenvEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产组件部署环境节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyCmpExecenvVO update(@RequestBody @Valid DevpSysDpyCmpExecenvEditDto devpSysDpyCmpExecenvEditDto, @PathVariable Long id){
		DevpSysDpyCmpExecenv devpSysDpyCmpExecenv = new DevpSysDpyCmpExecenv();
		BeanUtils.copyProperties(devpSysDpyCmpExecenvEditDto, devpSysDpyCmpExecenv);
		devpSysDpyCmpExecenv.setId(id);
		devpSysDpyCmpExecenvService.merge(devpSysDpyCmpExecenv);

		DevpSysDpyCmpExecenvVO vo = initViewProperty(devpSysDpyCmpExecenv);
		return  vo;
	}

	/**
	 * 根据ID查询组件部署环境节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询组件部署环境节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyCmpExecenvVO get(@PathVariable Long id) {

		DevpSysDpyCmpExecenv devpSysDpyCmpExecenv = devpSysDpyCmpExecenvService.find(id);

		DevpSysDpyCmpExecenvVO vo = initViewProperty(devpSysDpyCmpExecenv);
		return vo;
	}

	/**
	 * 查询组件部署环境节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询组件部署环境节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyCmpExecenvVO> list(@RequestBody PageSearchRequest<DevpSysDpyCmpExecenvCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyCmpExecenv> page = devpSysDpyCmpExecenvService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyCmpExecenvVO> voList = new ArrayList<>();
		for(DevpSysDpyCmpExecenv devpSysDpyCmpExecenv : page.getContent()){
			voList.add(initViewProperty(devpSysDpyCmpExecenv));
		}

		PageContent<DevpSysDpyCmpExecenvVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出组件部署环境节点列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出组件部署环境节点列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysDpyCmpExecenvCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysDpyCmpExecenvCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysDpyCmpExecenvVO> content = this.list(pageSearchRequest);

        List<DevpSysDpyCmpExecenvVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysDpyCmpExecenvVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("组件部署环境节点");
        String fileName = new String(("组件部署环境节点_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysDpyCmpExecenvVO initViewProperty(DevpSysDpyCmpExecenv devpSysDpyCmpExecenv){

	    DevpSysDpyCmpExecenvVO vo = new DevpSysDpyCmpExecenvVO();
        BeanUtils.copyProperties(devpSysDpyCmpExecenv, vo);


	    //初始化其他对象
        return vo;


	}


}
