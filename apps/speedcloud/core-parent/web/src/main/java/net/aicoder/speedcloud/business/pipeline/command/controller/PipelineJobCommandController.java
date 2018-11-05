package net.aicoder.speedcloud.business.pipeline.command.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.command.domain.PipelineJobCommand;
import net.aicoder.speedcloud.business.pipeline.command.dto.PipelineJobCommandCondition;
import net.aicoder.speedcloud.business.pipeline.command.dto.PipelineJobCommandAddDto;
import net.aicoder.speedcloud.business.pipeline.command.dto.PipelineJobCommandEditDto;
import net.aicoder.speedcloud.business.pipeline.command.service.PipelineJobCommandService;
import net.aicoder.speedcloud.business.pipeline.command.valid.PipelineJobCommandValidator;
import net.aicoder.speedcloud.business.pipeline.command.vo.PipelineJobCommandVO;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskService;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskVO;


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
 * 管理创建Job指令
 * @author icode
 */
@Api(description = "创建Job指令", tags = "PipelineJobCommand")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/command/pipelinejobcommand")
public class PipelineJobCommandController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineJobCommandController.class);


	@Autowired
	private PipelineJobCommandService pipelineJobCommandService;

	@Autowired
	private PipelineTaskService pipelineTaskService;


	@Autowired
	private PipelineJobCommandValidator pipelineJobCommandValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineJobCommandValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增创建Job指令
	 * @param pipelineJobCommandAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增创建Job指令", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineJobCommandVO add(@RequestBody @Valid PipelineJobCommandAddDto pipelineJobCommandAddDto){
		PipelineJobCommand pipelineJobCommand = new PipelineJobCommand();
		BeanUtils.copyProperties(pipelineJobCommandAddDto, pipelineJobCommand);

		pipelineJobCommandService.add(pipelineJobCommand);

		return  initViewProperty(pipelineJobCommand);
	}

	/**
	 * 删除创建Job指令,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除创建Job指令", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineJobCommand :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineJobCommandService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新创建Job指令
	 * @param pipelineJobCommandEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产创建Job指令(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	PipelineJobCommandVO update(@RequestBody @Valid PipelineJobCommandEditDto pipelineJobCommandEditDto, @PathVariable Long id){
		PipelineJobCommand pipelineJobCommand = new PipelineJobCommand();
		BeanUtils.copyProperties(pipelineJobCommandEditDto, pipelineJobCommand);
		pipelineJobCommand.setId(id);
		pipelineJobCommandService.merge(pipelineJobCommand);

		PipelineJobCommandVO vo = initViewProperty(pipelineJobCommand);
		return  vo;
	}

	/**
	 * 根据ID查询创建Job指令
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询创建Job指令", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineJobCommandVO get(@PathVariable Long id) {

		PipelineJobCommand pipelineJobCommand = pipelineJobCommandService.find(id);

		PipelineJobCommandVO vo = initViewProperty(pipelineJobCommand);
		return vo;
	}

	/**
	 * 查询创建Job指令列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询创建Job指令列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineJobCommandVO> list(@RequestBody PageSearchRequest<PipelineJobCommandCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<PipelineJobCommand> page = pipelineJobCommandService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineJobCommandVO> voList = new ArrayList<>();
		for(PipelineJobCommand pipelineJobCommand : page.getContent()){
			voList.add(initViewProperty(pipelineJobCommand));
		}

		PageContent<PipelineJobCommandVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出创建Job指令列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出创建Job指令列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineJobCommandCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineJobCommandCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineJobCommandVO> content = this.list(pageSearchRequest);

        List<PipelineJobCommandVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineJobCommandVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("task" ,"关联任务");
            headMap.put("createTime" ,"创建时间");
            headMap.put("status" ,"状态");
            headMap.put("type" ,"之类类型");
            headMap.put("result" ,"执行结果");

        String title = new String("创建Job指令");
        String fileName = new String(("创建Job指令_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineJobCommandVO initViewProperty(PipelineJobCommand pipelineJobCommand){

	    PipelineJobCommandVO vo = new PipelineJobCommandVO();
        BeanUtils.copyProperties(pipelineJobCommand, vo);


	    //初始化其他对象
	    initTaskPropertyGroup(vo, pipelineJobCommand);
        return vo;

	}


	private void initTaskPropertyGroup(PipelineJobCommandVO pipelineJobCommandVO, PipelineJobCommand pipelineJobCommand){
	
		PipelineTask task = pipelineTaskService.find(pipelineJobCommand.getTask());
		if(task == null){
			return;
		}
		PipelineTaskVO taskVO = new PipelineTaskVO();
		BeanUtils.copyProperties(task, taskVO);

		pipelineJobCommandVO.setTaskVO(taskVO);

	}


}

