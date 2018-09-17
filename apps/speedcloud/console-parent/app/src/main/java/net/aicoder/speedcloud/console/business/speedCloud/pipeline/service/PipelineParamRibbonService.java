package net.aicoder.speedcloud.console.business.speedCloud.pipeline.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.PipelineParamRibbon;
import net.aicoder.speedcloud.client.pipeline.result.PipelineParamPageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineParamResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineParamVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineParamRibbonService")
public class PipelineParamRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineParamRibbonService.class);


	@Autowired
	private PipelineParamRibbon pipelineParamRibbon;


	public PipelineParamVO add(PipelineParamAddDto addDto){
		PipelineParamResult result = pipelineParamRibbon.add(addDto);

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
		PipelineParamResult result = pipelineParamRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineParamVO merge(Long id, PipelineParamEditDto editDto){
		PipelineParamResult result = pipelineParamRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineParamVO find(Long id){
		PipelineParamResult result = pipelineParamRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineParamVO> list(PageSearchRequest<PipelineParamCondition> pageSearchRequest) {
		PipelineParamPageResult result = pipelineParamRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
