package net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.task.PipelineTaskParamRibbon;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskParamPageResult;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskParamResult;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamEditDto;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskParamVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineTaskParamRibbonService")
public class PipelineTaskParamRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskParamRibbonService.class);


	@Autowired
	private PipelineTaskParamRibbon pipelineTaskParamRibbon;


	public PipelineTaskParamVO add(PipelineTaskParamAddDto addDto){
		PipelineTaskParamResult result = pipelineTaskParamRibbon.add(addDto);

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
		PipelineTaskParamResult result = pipelineTaskParamRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineTaskParamVO merge(Long id, PipelineTaskParamEditDto editDto){
		PipelineTaskParamResult result = pipelineTaskParamRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineTaskParamVO find(Long id){
		PipelineTaskParamResult result = pipelineTaskParamRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineTaskParamVO> list(PageSearchRequest<PipelineTaskParamCondition> pageSearchRequest) {
		PipelineTaskParamPageResult result = pipelineTaskParamRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
