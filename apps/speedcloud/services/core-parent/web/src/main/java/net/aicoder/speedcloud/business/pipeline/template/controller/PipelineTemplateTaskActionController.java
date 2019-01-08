package net.aicoder.speedcloud.business.pipeline.template.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionType;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskActionTypeService;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskService;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionTypeVO;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTaskAction;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionCondition;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionEditDto;
import net.aicoder.speedcloud.business.pipeline.template.service.PipelineTemplateTaskActionService;
import net.aicoder.speedcloud.business.pipeline.template.valid.PipelineTemplateTaskActionValidator;
import net.aicoder.speedcloud.business.pipeline.template.vo.PipelineTemplateTaskActionVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理操作模板
 * @author icode
 */
@Api(description = "操作模板", tags = "PipelineTemplateTaskAction")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/template/pipelinetemplatetaskaction")
public class PipelineTemplateTaskActionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTemplateTaskActionController.class);


	@Autowired
	private PipelineTemplateTaskActionService pipelineTemplateTaskActionService;

	@Autowired
	private PipelineTaskService pipelineTaskService;
	@Autowired
	private PipelineTaskActionTypeService pipelineTaskActionTypeService;


	@Autowired
	private PipelineTemplateTaskActionValidator pipelineTemplateTaskActionValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTemplateTaskActionValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增操作模板
	 * @param pipelineTemplateTaskActionAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增操作模板", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.template.pipelinetemplatetaskaction.add", count = true)
	public PipelineTemplateTaskActionVO add(@RequestBody @Valid PipelineTemplateTaskActionAddDto pipelineTemplateTaskActionAddDto){
		PipelineTemplateTaskAction pipelineTemplateTaskAction = new PipelineTemplateTaskAction();
		BeanUtils.copyProperties(pipelineTemplateTaskActionAddDto, pipelineTemplateTaskAction);

		pipelineTemplateTaskActionService.add(pipelineTemplateTaskAction);

		return  initViewProperty(pipelineTemplateTaskAction);
	}

	/**
	 * 删除操作模板,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除操作模板", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.template.pipelinetemplatetaskaction.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTemplateTaskAction :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineTemplateTaskActionService.delete(id);
		}

	}

	/**
	 * 更新操作模板
	 * @param pipelineTemplateTaskActionEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改操作模板(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.template.pipelinetemplatetaskaction.update", count = true)
	public	PipelineTemplateTaskActionVO update(@RequestBody @Valid PipelineTemplateTaskActionEditDto pipelineTemplateTaskActionEditDto, @PathVariable String id){
		PipelineTemplateTaskAction pipelineTemplateTaskAction = pipelineTemplateTaskActionService.find(id);
		BeanUtils.copyProperties(pipelineTemplateTaskActionEditDto, pipelineTemplateTaskAction);
		pipelineTemplateTaskAction.setId(id);
		pipelineTemplateTaskActionService.merge(pipelineTemplateTaskAction);

		PipelineTemplateTaskActionVO vo = initViewProperty(pipelineTemplateTaskAction);
		return  vo;
	}

	/**
	 * 根据ID查询操作模板
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询", notes = "根据ID查询操作模板", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.pipeline.template.pipelinetemplatetaskaction.get")
	public  PipelineTemplateTaskActionVO get(@PathVariable String id) {

		PipelineTemplateTaskAction pipelineTemplateTaskAction = pipelineTemplateTaskActionService.find(id);
		if(pipelineTemplateTaskAction == null){
			throw new ResourceNotFoundException("找不到指定的操作模板，请检查ID");
		}
		PipelineTemplateTaskActionVO vo = initViewProperty(pipelineTemplateTaskAction);
		return vo;
	}

	/**
	 * 查询操作模板列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询操作模板列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "speedcloud.pipeline.template.pipelinetemplatetaskaction.list")
	public PageContent<PipelineTemplateTaskActionVO> list(@RequestBody PageSearchRequest<PipelineTemplateTaskActionCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<PipelineTemplateTaskAction> page = pipelineTemplateTaskActionService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineTemplateTaskActionVO> voList = new ArrayList<>();
		for(PipelineTemplateTaskAction pipelineTemplateTaskAction : page.getContent()){
			voList.add(initViewProperty(pipelineTemplateTaskAction));
		}

		PageContent<PipelineTemplateTaskActionVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出操作模板列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出操作模板列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(PipelineTemplateTaskActionCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineTemplateTaskActionCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineTemplateTaskActionVO> content = this.list(pageSearchRequest);

        List<PipelineTemplateTaskActionVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineTemplateTaskActionVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("task" ,"所属任务");
        headMap.put("memo" ,"操作说明");
        headMap.put("name" ,"操作名称");
        headMap.put("execIndex" ,"执行顺序");
        headMap.put("type" ,"操作类型");
        headMap.put("content" ,"脚本内容");

        String title = new String("操作模板");
        String fileName = new String(("操作模板_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineTemplateTaskActionVO initViewProperty(PipelineTemplateTaskAction pipelineTemplateTaskAction){

	    PipelineTemplateTaskActionVO vo = new PipelineTemplateTaskActionVO();
        BeanUtils.copyProperties(pipelineTemplateTaskAction, vo);


	    //初始化其他对象
	    initTypePropertyGroup(vo, pipelineTemplateTaskAction);
        return vo;

	}


	private void initTypePropertyGroup(PipelineTemplateTaskActionVO pipelineTemplateTaskActionVO, PipelineTemplateTaskAction pipelineTemplateTaskAction){
	
		PipelineTaskActionType type = pipelineTaskActionTypeService.find(pipelineTemplateTaskAction.getType());
		if(type == null){
			return;
		}
		PipelineTaskActionTypeVO typeVO = new PipelineTaskActionTypeVO();
		BeanUtils.copyProperties(type, typeVO);

		pipelineTemplateTaskActionVO.setTypeVO(typeVO);

	}

}

