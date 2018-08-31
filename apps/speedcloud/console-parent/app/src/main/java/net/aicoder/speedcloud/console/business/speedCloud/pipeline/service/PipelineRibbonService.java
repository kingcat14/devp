package net.aicoder.speedcloud.console.business.speedCloud.pipeline.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.PipelineRibbon;
import net.aicoder.speedcloud.client.pipeline.result.PipelinePageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineRibbonService")
public class PipelineRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineRibbonService.class);


	@Autowired
	private PipelineRibbon pipelineRibbon;


	public PipelineVO add(PipelineAddDto addDto){
		PipelineResult result = pipelineRibbon.add(addDto);

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
		PipelineResult result = pipelineRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineVO merge(Long id, PipelineEditDto editDto){
		PipelineResult result = pipelineRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineVO find(Long id){
		PipelineResult result = pipelineRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineVO> list(PageSearchRequest<PipelineCondition> pageSearchRequest) {
		PipelinePageResult result = pipelineRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
