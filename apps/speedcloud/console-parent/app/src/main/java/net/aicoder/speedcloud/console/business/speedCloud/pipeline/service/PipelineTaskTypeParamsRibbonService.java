package net.aicoder.speedcloud.console.business.speedCloud.pipeline.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.PipelineTaskTypeParamsRibbon;
import net.aicoder.speedcloud.client.pipeline.result.PipelineTaskTypeParamsPageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineTaskTypeParamsResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineTaskTypeParamsVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineTaskTypeParamsRibbonService")
public class PipelineTaskTypeParamsRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskTypeParamsRibbonService.class);


	@Autowired
	private PipelineTaskTypeParamsRibbon pipelineTaskTypeParamsRibbon;


	public PipelineTaskTypeParamsVO add(PipelineTaskTypeParamsAddDto addDto){
		PipelineTaskTypeParamsResult result = pipelineTaskTypeParamsRibbon.add(addDto);

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
		PipelineTaskTypeParamsResult result = pipelineTaskTypeParamsRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineTaskTypeParamsVO merge(Long id, PipelineTaskTypeParamsEditDto editDto){
		PipelineTaskTypeParamsResult result = pipelineTaskTypeParamsRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineTaskTypeParamsVO find(Long id){
		PipelineTaskTypeParamsResult result = pipelineTaskTypeParamsRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineTaskTypeParamsVO> list(PageSearchRequest<PipelineTaskTypeParamsCondition> pageSearchRequest) {
		PipelineTaskTypeParamsPageResult result = pipelineTaskTypeParamsRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
