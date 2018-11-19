package net.aicoder.speedcloud.console.business.speedcloud.pipeline.exec.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecNodeLogVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.exec.service.PipelineExecNodeLogRibbonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	private PipelineExecNodeLogRibbonService pipelineExecNodeLogRibbonService;


	/**
	 * 根据ID查询运行节点日志
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行节点日志", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineExecNodeLogVO get(@ApiParam(value = "要查询的运行节点日志id") @PathVariable Long id) {

		PipelineExecNodeLogVO vo = pipelineExecNodeLogRibbonService.find(id);
		return vo;
	}


}
