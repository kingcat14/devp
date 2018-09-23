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
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.dto.*;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineVO;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.service.PipelineRibbonService;
import net.aicoder.speedcloud.console.business.speedCloud.pipeline.valid.PipelineValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理流水线
 * @author icode
 */
@Api(description = "流水线", tags = "Pipeline")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipeline")
public class PipelineAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineAction.class);


	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private PipelineRibbonService pipelineRibbonService;

	@Autowired
	PipelineValidator pipelineValidator;

    @Autowired
    private SimpleConfigService simpleConfigService;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增流水线
	 * @param pipelineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增流水线", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineVO add(@RequestBody PipelineAddDto pipelineAddDto){

		long tid = saaSUtil.getAccount().getTenantId();
    	pipelineAddDto.setTid(saaSUtil.getAccount().getTenantId());

    	//处理参数的tid
    	if(CollectionUtils.isNotEmpty(pipelineAddDto.getParamList())){
    		for(PipelineParamAddDto paramAddDto : pipelineAddDto.getParamList()){
				paramAddDto.setTid(tid);
			}
		}

		handleParamTid(pipelineAddDto.getParamList());
		handleStageTid(pipelineAddDto.getStageList());


		return  pipelineRibbonService.add(pipelineAddDto);
	}



	/**
	 * 更新流水线
	 * @param pipelineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产流水线(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public PipelineVO update(@RequestBody PipelineEditDto pipelineEditDto, @ApiParam(value = "要查询的流水线id") @PathVariable Long id){

		handleParamTid(pipelineEditDto.getParamList());
		handleStageTid(pipelineEditDto.getStageList());


		PipelineVO vo = pipelineRibbonService.merge(id, pipelineEditDto);

		return  vo;
	}


	private void handleParamTid(List<PipelineParamAddDto> pipelineParamAddDtoList){
		Long tid = saaSUtil.getAccount().getTenantId();
		//处理参数的tid
		if(CollectionUtils.isEmpty(pipelineParamAddDtoList)){
			return;
		}
		for(PipelineParamAddDto paramAddDto : pipelineParamAddDtoList){
			paramAddDto.setTid(tid);
		}
	}
	private void handleStageTid(List<PipelineStageAddDto> stageList){
		Long tid = saaSUtil.getAccount().getTenantId();
		//处理阶段的tid
		if(CollectionUtils.isEmpty(stageList)){
			return;
		}
		for(PipelineStageAddDto stageAddDto : stageList){
			stageAddDto.setTid(tid);
			handStageNodeTid(stageAddDto.getNodeList());
		}
	}

	private void handStageNodeTid(List<PipelineStageNodeAddDto> stageNodeAddDtoList){
		Long tid = saaSUtil.getAccount().getTenantId();
		//处理阶段节点的tid
		if(CollectionUtils.isEmpty(stageNodeAddDtoList)){
			return;
		}

		for(PipelineStageNodeAddDto stageNodeAddDto : stageNodeAddDtoList){
			stageNodeAddDto.setTid(tid);
			handleStageNodeParamTid(stageNodeAddDto.getParamList());
		}
	}

	private void handleStageNodeParamTid(List<PipelineStageNodeParamAddDto> pipelineStageNodeParamAddDtoList){
		Long tid = saaSUtil.getAccount().getTenantId();
		//处理参数的tid
		if(CollectionUtils.isEmpty(pipelineStageNodeParamAddDtoList)){
			return;
		}
		for(PipelineStageNodeParamAddDto paramAddDto : pipelineStageNodeParamAddDtoList){
			paramAddDto.setTid(tid);
		}
	}

}
