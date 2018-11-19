package net.aicoder.speedcloud.business.pipeline.exec.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeCustomAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.service.ExecAction;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * 定制运行计划管理运行计划
 * @author icode
 */
@Api(description = "管理运行计划", tags = "PipelineExecNode")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/pipelineexecinstance")
public class PipelineExecInstanceCustomController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceCustomController.class);

	@Autowired
	private PipelineExecInstanceController pipelineExecNodeController;

	@Autowired
	private	ExecAction execAction;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){

		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 直接执行一个task
	 * @param customAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运行实例节点", httpMethod = "POST")
	@PostMapping(value = "/task")
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineExecInstanceVO task(@RequestBody @Valid PipelineExecNodeCustomAddDto customAddDto){

		PipelineExecInstance instance = execAction.createExec(customAddDto);

		return  pipelineExecNodeController.initViewProperty(instance);

	}

	/**
	 * 个性化运行流水线
	 * @param customAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运行实例节点", httpMethod = "POST")
	@PostMapping(value = "/custom")
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineExecInstanceVO custom(@RequestBody @Valid PipelineExecNodeCustomAddDto customAddDto){

		PipelineExecInstance instance = execAction.createExec(customAddDto);

		return  pipelineExecNodeController.initViewProperty(instance);

	}


	/**
	 * 直接执行完整的流水线
	 * @param customAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运行实例节点", httpMethod = "POST")
	@PostMapping("/pipeline")
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineExecInstanceVO pipeline(@RequestBody @Valid PipelineExecNodeCustomAddDto customAddDto){

		PipelineExecInstance instance = execAction.createExec(customAddDto, true);

		return  pipelineExecNodeController.initViewProperty(instance);

	}





}

