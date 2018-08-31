package net.aicoder.speedcloud.console.business.speedCloud.pipeline.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.PipelineTaskTypeRibbon;
import net.aicoder.speedcloud.client.pipeline.result.PipelineTaskTypePageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineTaskTypeResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineTaskTypeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineTaskTypeRibbonService")
public class PipelineTaskTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskTypeRibbonService.class);


	@Autowired
	private PipelineTaskTypeRibbon pipelineTaskTypeRibbon;


	public PipelineTaskTypeVO add(PipelineTaskTypeAddDto addDto){
		PipelineTaskTypeResult result = pipelineTaskTypeRibbon.add(addDto);

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
		PipelineTaskTypeResult result = pipelineTaskTypeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineTaskTypeVO merge(Long id, PipelineTaskTypeEditDto editDto){
		PipelineTaskTypeResult result = pipelineTaskTypeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineTaskTypeVO find(Long id){
		PipelineTaskTypeResult result = pipelineTaskTypeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineTaskTypeVO> list(PageSearchRequest<PipelineTaskTypeCondition> pageSearchRequest) {
		PipelineTaskTypePageResult result = pipelineTaskTypeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
