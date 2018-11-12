package net.aicoder.speedcloud.console.business.speedcloud.pipeline.controller;

import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageSelectableNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageSelectableNodeParamVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.service.PipelineStageSelectableNodeParamRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.valid.PipelineStageSelectableNodeParamValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 管理阶段可选节点参数
 * @author icode
 */
@Api(description = "阶段可选节点参数", tags = "PipelineStageSelectableNodeParam")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinestageselectablenodeparam")
public class PipelineStageSelectableNodeParamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageSelectableNodeParamController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineStageSelectableNodeParamRibbonService pipelineStageSelectableNodeParamRibbonService;

	@Autowired
	PipelineStageSelectableNodeParamValidator pipelineStageSelectableNodeParamValidator;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineStageSelectableNodeParamValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}





	/**
	 * 查询阶段可选节点参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询阶段可选节点参数列表", httpMethod = "POST")
	@PostMapping("/list") @SaaSAnnotation(conditionClass = PipelineStageSelectableNodeParamCondition.class)
	public PageContent<PipelineStageSelectableNodeParamVO> list(@RequestBody PageSearchRequest<PipelineStageSelectableNodeParamCondition> pageSearchRequest){

		PageContent<PipelineStageSelectableNodeParamVO> pageContent = pipelineStageSelectableNodeParamRibbonService.list(pageSearchRequest);


		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}



}
