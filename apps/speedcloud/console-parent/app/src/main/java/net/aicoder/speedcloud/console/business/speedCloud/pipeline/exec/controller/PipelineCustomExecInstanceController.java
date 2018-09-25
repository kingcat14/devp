package net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.controller;

import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineCustomExecInstanceAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.service.PipelineExecInstanceRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.valid.PipelineExecInstanceValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 管理自定义运行计划
 * @author icode
 */
@Api(description = "自定义运行计划", tags = "PipelineCustomExecInstance")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/pipelineexecinstance")
public class PipelineCustomExecInstanceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineCustomExecInstanceController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineExecInstanceRibbonService pipelineExecInstanceRibbonService;

	@Autowired
	private PipelineExecInstanceValidator pipelineExecInstanceValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineExecInstanceValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增自定义运行计划
	 * @param pipelineCustomExecInstanceAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增自定义运行计划", httpMethod = "POST")
	@PostMapping("/custom")
	@ResponseStatus(HttpStatus.CREATED)
	public PipelineExecInstanceVO add(@RequestBody PipelineCustomExecInstanceAddDto pipelineCustomExecInstanceAddDto){
		pipelineCustomExecInstanceAddDto.setTid(saaSUtil.getAccount().getTenantId());
		return null;
	}

	private void fillTid(PipelineCustomExecInstanceAddDto addDto){

		addDto.setTid(saaSUtil.getAccount().getTenantId());

		if(CollectionUtils.isEmpty(addDto.getSubNodeList())){
			return ;
		}

		for(PipelineCustomExecInstanceAddDto subDto : addDto.getSubNodeList()){
			fillTid(subDto);
		}

	}


}
