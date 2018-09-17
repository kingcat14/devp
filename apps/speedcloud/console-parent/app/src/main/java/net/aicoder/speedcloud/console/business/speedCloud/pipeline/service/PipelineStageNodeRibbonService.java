package net.aicoder.speedcloud.console.business.speedCloud.pipeline.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.PipelineStageNodeRibbon;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageNodePageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageNodeResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageNodeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineStageNodeRibbonService")
public class PipelineStageNodeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageNodeRibbonService.class);


	@Autowired
	private PipelineStageNodeRibbon pipelineStageNodeRibbon;


	public PipelineStageNodeVO add(PipelineStageNodeAddDto addDto){
		PipelineStageNodeResult result = pipelineStageNodeRibbon.add(addDto);

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
		PipelineStageNodeResult result = pipelineStageNodeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineStageNodeVO merge(Long id, PipelineStageNodeEditDto editDto){
		PipelineStageNodeResult result = pipelineStageNodeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineStageNodeVO find(Long id){
		PipelineStageNodeResult result = pipelineStageNodeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineStageNodeVO> list(PageSearchRequest<PipelineStageNodeCondition> pageSearchRequest) {
		PipelineStageNodePageResult result = pipelineStageNodeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
