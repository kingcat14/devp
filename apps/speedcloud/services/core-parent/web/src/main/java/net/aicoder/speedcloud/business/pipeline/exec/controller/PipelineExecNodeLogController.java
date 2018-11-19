package net.aicoder.speedcloud.business.pipeline.exec.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNodeLog;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeLogService;
import net.aicoder.speedcloud.business.pipeline.exec.valid.PipelineExecNodeLogValidator;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecNodeLogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 管理运行节点日志
 * @author icode
 */
@Api(description = "运行节点日志", tags = "PipelineExecNodeLog")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/pipelineexecnodelog")
public class PipelineExecNodeLogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeLogController.class);

	@Autowired
	private PipelineExecNodeLogService pipelineExecNodeLogService;



	@Autowired
	private PipelineExecNodeLogValidator pipelineExecNodeLogValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineExecNodeLogValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}


	/**
	 * 根据ID查询运行节点日志
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行节点日志", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineExecNodeLogVO get(@PathVariable Long id) {

		PipelineExecNodeLog pipelineExecNodeLog = pipelineExecNodeLogService.find(id);

		PipelineExecNodeLogVO vo = initViewProperty(pipelineExecNodeLog);
		return vo;
	}


	private PipelineExecNodeLogVO initViewProperty(PipelineExecNodeLog pipelineExecNodeLog){

	    PipelineExecNodeLogVO vo = new PipelineExecNodeLogVO();
        BeanUtils.copyProperties(pipelineExecNodeLog, vo);

	    //初始化其他对象
        return vo;

	}


}

