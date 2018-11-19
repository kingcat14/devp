package net.aicoder.speedcloud.console.business.speedcloud.pipeline.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageVO;
import net.aicoder.speedcloud.client.pipeline.PipelineStageRibbon;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStagePageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineStageRibbonService")
public class PipelineStageRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageRibbonService.class);


	@Autowired
	private PipelineStageRibbon pipelineStageRibbon;


	public PipelineStageVO add(PipelineStageAddDto addDto){
		PipelineStageResult result = pipelineStageRibbon.add(addDto);

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
		PipelineStageResult result = pipelineStageRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineStageVO merge(Long id, PipelineStageEditDto editDto){
		PipelineStageResult result = pipelineStageRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineStageVO find(Long id){
		PipelineStageResult result = pipelineStageRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineStageVO> list(PageSearchRequest<PipelineStageCondition> pageSearchRequest) {
		PipelineStagePageResult result = pipelineStageRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
