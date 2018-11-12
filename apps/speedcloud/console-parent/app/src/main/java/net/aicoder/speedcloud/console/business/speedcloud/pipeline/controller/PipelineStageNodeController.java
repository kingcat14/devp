package net.aicoder.speedcloud.console.business.speedcloud.pipeline.controller;

import com.yunkang.saas.bootstrap.application.business.annotation.SaaSAnnotation;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeCondition;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageNodeVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.service.PipelineStageNodeRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.valid.PipelineStageNodeValidator;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import java.util.*;

/**
 * 管理阶段执行节点
 * @author icode
 */
@Api(description = "阶段执行节点", tags = "PipelineStageNode")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinestagenode")
public class PipelineStageNodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageNodeController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineStageNodeRibbonService pipelineStageNodeRibbonService;

	@Autowired
	PipelineStageNodeValidator pipelineStageNodeValidator;

    @Autowired
    private SimpleConfigService simpleConfigService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineStageNodeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 根据ID查询阶段执行节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询阶段执行节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public PipelineStageNodeVO get(@ApiParam(value = "要查询的阶段执行节点id") @PathVariable Long id) {

		PipelineStageNodeVO vo = pipelineStageNodeRibbonService.find(id);
		return vo;
	}

	/**
	 * 查询阶段执行节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询阶段执行节点列表", httpMethod = "POST")
	@PostMapping("/list") @SaaSAnnotation(conditionClass = PipelineStageNodeCondition.class)
	public PageContent<PipelineStageNodeVO> list(@RequestBody PageSearchRequest<PipelineStageNodeCondition> pageSearchRequest){

		PageContent<PipelineStageNodeVO> pageContent = pipelineStageNodeRibbonService.list(pageSearchRequest);
		for(PipelineStageNodeVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;
	}

	private PipelineStageNodeVO initViewProperty( PipelineStageNodeVO vo){

		SimpleConfig objTypeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINESTAGENODE-OBJTYPE", vo.getObjType());

		if(objTypeSimpleConfig!=null) {

		    SimpleConfigVO objTypeSimpleConfigVO = new SimpleConfigVO();
		    BeanUtils.copyProperties(objTypeSimpleConfig, objTypeSimpleConfigVO);
		    vo.setObjTypeVO(objTypeSimpleConfigVO);
		}

        return vo;

	}


}
