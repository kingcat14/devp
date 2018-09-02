package net.aicoder.speedcloud.console.business.speedCloud.pipeline.task.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.task.PipelineTaskActionRibbon;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionPageResult;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionResult;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionEditDto;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineTaskActionRibbonService")
public class PipelineTaskActionRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionRibbonService.class);


	@Autowired
	private PipelineTaskActionRibbon pipelineTaskActionRibbon;


	public PipelineTaskActionVO add(PipelineTaskActionAddDto addDto){
		PipelineTaskActionResult result = pipelineTaskActionRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		PipelineTaskActionResult result = pipelineTaskActionRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineTaskActionVO merge(Long id, PipelineTaskActionEditDto editDto){
		PipelineTaskActionResult result = pipelineTaskActionRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineTaskActionVO find(Long id){
		PipelineTaskActionResult result = pipelineTaskActionRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineTaskActionVO> list(PageSearchRequest<PipelineTaskActionCondition> pageSearchRequest) {
		PipelineTaskActionPageResult result = pipelineTaskActionRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
