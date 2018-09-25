package net.aicoder.speedcloud.business.pipeline.exec.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeCustomAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.service.ExecAction;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecInstanceService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.valid.PipelineExecNodeValidator;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceVO;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecNodeVO;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskService;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理运行计划
 * @author icode
 */
@Api(description = "管理运行计划", tags = "PipelineExecNode")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/pipelineexecinstance")
public class PipelineExecNodeCustomController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeCustomController.class);


	@Autowired
	private PipelineExecNodeService pipelineExecNodeService;


	@Autowired
	private PipelineExecInstanceController pipelineExecNodeController;

	@Autowired
	private	ExecAction execAction;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){

		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增运行实例节点
	 * @param customAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运行实例节点", httpMethod = "POST")
	@PostMapping("/custom")
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineExecInstanceVO add(@RequestBody @Valid PipelineExecNodeCustomAddDto customAddDto){

		PipelineExecInstance instance = execAction.createExec(customAddDto);

		return  pipelineExecNodeController.initViewProperty(instance);

	}



}

