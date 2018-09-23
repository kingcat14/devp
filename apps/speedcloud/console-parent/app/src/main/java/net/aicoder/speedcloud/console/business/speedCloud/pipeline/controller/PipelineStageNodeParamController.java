package net.aicoder.speedcloud.console.business.speedCloud.pipeline.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageNodeParamVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.service.PipelineStageNodeParamRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.valid.PipelineStageNodeParamValidator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

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
	@PostMapping("/list")
	public PageContent<PipelineStageNodeParamVO> list(@RequestBody PageSearchRequest<PipelineStageNodeParamCondition> pageSearchRequest){

		PipelineStageNodeParamCondition condition = pageSearchRequest.getSearchCondition();
		if(condition==null){
			condition = new PipelineStageNodeParamCondition();
			pageSearchRequest.setSearchCondition(condition);
		}
        pageSearchRequest.getSearchCondition().setTid(saaSUtil.getAccount().getTenantId());
		PageContent<PipelineStageNodeParamVO> pageContent = pipelineStageNodeParamRibbonService.list(pageSearchRequest);

		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}






}
