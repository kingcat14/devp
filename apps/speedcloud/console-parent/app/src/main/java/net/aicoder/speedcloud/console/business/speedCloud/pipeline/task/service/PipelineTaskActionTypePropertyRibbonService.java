package net.aicoder.speedcloud.console.business.speedCloud.pipeline.task.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.task.PipelineTaskActionTypePropertyRibbon;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionTypePropertyPageResult;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionTypePropertyResult;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyEditDto;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionTypePropertyVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineTaskActionTypePropertyRibbonService")
public class PipelineTaskActionTypePropertyRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionTypePropertyRibbonService.class);


	@Autowired
	private PipelineTaskActionTypePropertyRibbon pipelineTaskActionTypePropertyRibbon;


	public PipelineTaskActionTypePropertyVO add(PipelineTaskActionTypePropertyAddDto addDto){
		PipelineTaskActionTypePropertyResult result = pipelineTaskActionTypePropertyRibbon.add(addDto);

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
		PipelineTaskActionTypePropertyResult result = pipelineTaskActionTypePropertyRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineTaskActionTypePropertyVO merge(Long id, PipelineTaskActionTypePropertyEditDto editDto){
		PipelineTaskActionTypePropertyResult result = pipelineTaskActionTypePropertyRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineTaskActionTypePropertyVO find(Long id){
		PipelineTaskActionTypePropertyResult result = pipelineTaskActionTypePropertyRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineTaskActionTypePropertyVO> list(PageSearchRequest<PipelineTaskActionTypePropertyCondition> pageSearchRequest) {
		PipelineTaskActionTypePropertyPageResult result = pipelineTaskActionTypePropertyRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
