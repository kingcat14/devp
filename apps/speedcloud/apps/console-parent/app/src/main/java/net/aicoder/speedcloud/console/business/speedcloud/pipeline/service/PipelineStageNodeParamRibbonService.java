package net.aicoder.speedcloud.console.business.speedcloud.pipeline.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageNodeParamVO;
import net.aicoder.speedcloud.client.pipeline.PipelineStageNodeParamRibbon;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageNodeParamPageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageNodeParamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineStageNodeParamRibbonService")
public class PipelineStageNodeParamRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageNodeParamRibbonService.class);


	@Autowired
	private PipelineStageNodeParamRibbon pipelineStageNodeParamRibbon;


	public PipelineStageNodeParamVO add(PipelineStageNodeParamAddDto addDto){
		PipelineStageNodeParamResult result = pipelineStageNodeParamRibbon.add(addDto);

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
		PipelineStageNodeParamResult result = pipelineStageNodeParamRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineStageNodeParamVO merge(Long id, PipelineStageNodeParamEditDto editDto){
		PipelineStageNodeParamResult result = pipelineStageNodeParamRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineStageNodeParamVO find(Long id){
		PipelineStageNodeParamResult result = pipelineStageNodeParamRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineStageNodeParamVO> list(PageSearchRequest<PipelineStageNodeParamCondition> pageSearchRequest) {
		PipelineStageNodeParamPageResult result = pipelineStageNodeParamRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
