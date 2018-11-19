package net.aicoder.speedcloud.console.business.speedcloud.pipeline.controller;

import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageNodeParamVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.service.PipelineStageNodeParamRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.valid.PipelineStageNodeParamValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 管理阶段执行节点参数
 * @author icode
 */
@Api(description = "阶段执行节点参数", tags = "PipelineStageNodeParam")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinestagenodeparam")
public class PipelineStageNodeParamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageNodeParamController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineStageNodeParamRibbonService pipelineStageNodeParamRibbonService;

	@Autowired
	PipelineStageNodeParamValidator pipelineStageNodeParamValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineStageNodeParamValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 查询阶段执行节点参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询阶段执行节点参数列表", httpMethod = "POST")
	@PostMapping("/list") @SaaSAnnotation(conditionClass = PipelineStageNodeParamCondition.class)
	public PageContent<PipelineStageNodeParamVO> list(@RequestBody PageSearchRequest<PipelineStageNodeParamCondition> pageSearchRequest){

		PageContent<PipelineStageNodeParamVO> pageContent = pipelineStageNodeParamRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}






}
