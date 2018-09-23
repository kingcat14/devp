package net.aicoder.speedcloud.console.business.speedCloud.pipeline.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.domain.SimpleConfig;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.service.SimpleConfigService;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.console.business.pipeline.dto.PipelineStageSelectableNodeCondition;
import net.aicoder.speedcloud.console.business.pipeline.vo.PipelineStageSelectableNodeVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.service.PipelineStageSelectableNodeRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.valid.PipelineStageSelectableNodeValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理阶段可选节点
 * @author icode
 */
@Api(description = "阶段可选节点", tags = "PipelineStageSelectableNode")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinestageselectablenode")
public class PipelineStageSelectableNodeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageSelectableNodeController.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineStageSelectableNodeRibbonService pipelineStageSelectableNodeRibbonService;

	@Autowired
	PipelineStageSelectableNodeValidator pipelineStageSelectableNodeValidator;

    @Autowired
    private SimpleConfigService simpleConfigService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineStageSelectableNodeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}



	/**
	 * 查询阶段可选节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询阶段可选节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineStageSelectableNodeVO> list(@RequestBody PageSearchRequest<PipelineStageSelectableNodeCondition> pageSearchRequest){

		PipelineStageSelectableNodeCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineStageSelectableNodeCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<PipelineStageSelectableNodeVO> pageContent = pipelineStageSelectableNodeRibbonService.selectable(pageSearchRequest);
		for(PipelineStageSelectableNodeVO vo : pageContent.getContent()){
			initViewProperty(vo);
		}

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}



	private PipelineStageSelectableNodeVO initViewProperty( PipelineStageSelectableNodeVO vo){


		SimpleConfig typeSimpleConfig = simpleConfigService.findByConfigTypeAndCode("PIPELINESTAGESELECTABLENODE-TYPE", vo.getType());

		if(typeSimpleConfig!=null) {

		    SimpleConfigVO typeSimpleConfigVO = new SimpleConfigVO();
		    BeanUtils.copyProperties(typeSimpleConfig, typeSimpleConfigVO);
		    vo.setTypeVO(typeSimpleConfigVO);
		}

	   
        return vo;

	}


}
