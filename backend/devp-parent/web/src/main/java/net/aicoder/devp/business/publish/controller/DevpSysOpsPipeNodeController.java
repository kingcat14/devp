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
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeNode;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeCondition;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeEditDto;
import net.aicoder.devp.business.publish.service.DevpSysOpsPipeNodeService;
import net.aicoder.devp.business.publish.valid.DevpSysOpsPipeNodeValidator;
import net.aicoder.devp.business.publish.vo.DevpSysOpsPipeNodeVO;

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
 * 管理流水线执行节点
 * @author icode
 */
@Api(description = "流水线执行节点", tags = "DevpSysOpsPipeNode")
@RestController
@RequestMapping(value = "/publish/devpSysOpsPipeNode")
public class DevpSysOpsPipeNodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysOpsPipeNodeController.class);


	@Autowired
	private DevpSysOpsPipeNodeService devpSysOpsPipeNodeService;


	@Autowired
	private DevpSysOpsPipeNodeValidator devpSysOpsPipeNodeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysOpsPipeNodeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增流水线执行节点
	 * @param devpSysOpsPipeNodeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增流水线执行节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysOpsPipeNodeVO add(@RequestBody @Valid DevpSysOpsPipeNodeAddDto devpSysOpsPipeNodeAddDto){
		DevpSysOpsPipeNode devpSysOpsPipeNode = new DevpSysOpsPipeNode();
		BeanUtils.copyProperties(devpSysOpsPipeNodeAddDto, devpSysOpsPipeNode);

		devpSysOpsPipeNodeService.add(devpSysOpsPipeNode);

		return  initViewProperty(devpSysOpsPipeNode);
	}

	/**
	 * 删除流水线执行节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除流水线执行节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysOpsPipeNode :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysOpsPipeNodeService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新流水线执行节点
	 * @param devpSysOpsPipeNodeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产流水线执行节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysOpsPipeNodeVO update(@RequestBody @Valid DevpSysOpsPipeNodeEditDto devpSysOpsPipeNodeEditDto, @PathVariable Long id){
		DevpSysOpsPipeNode devpSysOpsPipeNode = new DevpSysOpsPipeNode();
		BeanUtils.copyProperties(devpSysOpsPipeNodeEditDto, devpSysOpsPipeNode);
		devpSysOpsPipeNode.setId(id);
		devpSysOpsPipeNodeService.merge(devpSysOpsPipeNode);

		DevpSysOpsPipeNodeVO vo = initViewProperty(devpSysOpsPipeNode);
		return  vo;
	}

	/**
	 * 根据ID查询流水线执行节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询流水线执行节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysOpsPipeNodeVO get(@PathVariable Long id) {

		DevpSysOpsPipeNode devpSysOpsPipeNode = devpSysOpsPipeNodeService.find(id);

		DevpSysOpsPipeNodeVO vo = initViewProperty(devpSysOpsPipeNode);
		return vo;
	}

	/**
	 * 查询流水线执行节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询流水线执行节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysOpsPipeNodeVO> list(@RequestBody PageSearchRequest<DevpSysOpsPipeNodeCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysOpsPipeNode> page = devpSysOpsPipeNodeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysOpsPipeNodeVO> voList = new ArrayList<>();
		for(DevpSysOpsPipeNode devpSysOpsPipeNode : page.getContent()){
			voList.add(initViewProperty(devpSysOpsPipeNode));
		}

		PageContent<DevpSysOpsPipeNodeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出流水线执行节点列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出流水线执行节点列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpSysOpsPipeNodeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpSysOpsPipeNodeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpSysOpsPipeNodeVO> content = this.list(pageSearchRequest);

        List<DevpSysOpsPipeNodeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpSysOpsPipeNodeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();


        String title = new String("流水线执行节点");
        String fileName = new String(("流水线执行节点_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpSysOpsPipeNodeVO initViewProperty(DevpSysOpsPipeNode devpSysOpsPipeNode){

	    DevpSysOpsPipeNodeVO vo = new DevpSysOpsPipeNodeVO();
        BeanUtils.copyProperties(devpSysOpsPipeNode, vo);


	    //初始化其他对象
        return vo;


	}


}
