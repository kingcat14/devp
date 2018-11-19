package net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypeAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypeCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypeEditDto;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionTypeVO;
import net.aicoder.speedcloud.client.pipeline.task.PipelineTaskActionTypeRibbon;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionTypePageResult;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionTypeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineTaskActionTypeRibbonService")
public class PipelineTaskActionTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionTypeRibbonService.class);


	@Autowired
	private PipelineTaskActionTypeRibbon pipelineTaskActionTypeRibbon;


	public PipelineTaskActionTypeVO add(PipelineTaskActionTypeAddDto addDto){
		PipelineTaskActionTypeResult result = pipelineTaskActionTypeRibbon.add(addDto);

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
		PipelineTaskActionTypeResult result = pipelineTaskActionTypeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineTaskActionTypeVO merge(Long id, PipelineTaskActionTypeEditDto editDto){
		PipelineTaskActionTypeResult result = pipelineTaskActionTypeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineTaskActionTypeVO find(Long id){
		PipelineTaskActionTypeResult result = pipelineTaskActionTypeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineTaskActionTypeVO> list(PageSearchRequest<PipelineTaskActionTypeCondition> pageSearchRequest) {
		PipelineTaskActionTypePageResult result = pipelineTaskActionTypeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TASK", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
