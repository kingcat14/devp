package net.aicoder.speedcloud.console.business.speedCloud.pipeline.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.PipelineStageTaskRelationRibbon;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageTaskRelationPageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageTaskRelationResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageTaskRelationVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineStageTaskRelationRibbonService")
public class PipelineStageTaskRelationRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageTaskRelationRibbonService.class);


	@Autowired
	private PipelineStageTaskRelationRibbon pipelineStageTaskRelationRibbon;


	public PipelineStageTaskRelationVO add(PipelineStageTaskRelationAddDto addDto){
		PipelineStageTaskRelationResult result = pipelineStageTaskRelationRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		PipelineStageTaskRelationResult result = pipelineStageTaskRelationRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineStageTaskRelationVO merge(Long id, PipelineStageTaskRelationEditDto editDto){
		PipelineStageTaskRelationResult result = pipelineStageTaskRelationRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineStageTaskRelationVO find(Long id){
		PipelineStageTaskRelationResult result = pipelineStageTaskRelationRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineStageTaskRelationVO> list(PageSearchRequest<PipelineStageTaskRelationCondition> pageSearchRequest) {
		PipelineStageTaskRelationPageResult result = pipelineStageTaskRelationRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
