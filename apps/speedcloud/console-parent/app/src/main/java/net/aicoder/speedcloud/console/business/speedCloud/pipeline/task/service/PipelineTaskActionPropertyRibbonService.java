package net.aicoder.speedcloud.console.business.speedCloud.pipeline.task.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.task.PipelineTaskActionPropertyRibbon;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionPropertyPageResult;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionPropertyResult;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyEditDto;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionPropertyVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineTaskActionPropertyRibbonService")
public class PipelineTaskActionPropertyRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionPropertyRibbonService.class);


	@Autowired
	private PipelineTaskActionPropertyRibbon pipelineTaskActionPropertyRibbon;


	public PipelineTaskActionPropertyVO add(PipelineTaskActionPropertyAddDto addDto){
		PipelineTaskActionPropertyResult result = pipelineTaskActionPropertyRibbon.add(addDto);

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
		PipelineTaskActionPropertyResult result = pipelineTaskActionPropertyRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineTaskActionPropertyVO merge(Long id, PipelineTaskActionPropertyEditDto editDto){
		PipelineTaskActionPropertyResult result = pipelineTaskActionPropertyRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineTaskActionPropertyVO find(Long id){
		PipelineTaskActionPropertyResult result = pipelineTaskActionPropertyRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineTaskActionPropertyVO> list(PageSearchRequest<PipelineTaskActionPropertyCondition> pageSearchRequest) {
		PipelineTaskActionPropertyPageResult result = pipelineTaskActionPropertyRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
