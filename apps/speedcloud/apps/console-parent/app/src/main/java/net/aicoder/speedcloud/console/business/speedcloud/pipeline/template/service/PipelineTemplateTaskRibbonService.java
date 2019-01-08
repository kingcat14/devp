package net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskCondition;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskEditDto;
import net.aicoder.speedcloud.business.pipeline.template.vo.PipelineTemplateTaskVO;
import net.aicoder.speedcloud.client.pipeline.template.PipelineTemplateTaskClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineTemplateTaskRibbonService")
public class PipelineTemplateTaskRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTemplateTaskRibbonService.class);


	@Autowired
	private PipelineTemplateTaskClient pipelineTemplateTaskClient;


	public PipelineTemplateTaskVO add(PipelineTemplateTaskAddDto addDto){
		RestResponse<PipelineTemplateTaskVO> result = pipelineTemplateTaskClient.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TEMPLATE", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		RestResponse<PipelineTemplateTaskVO> result = pipelineTemplateTaskClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TEMPLATE", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineTemplateTaskVO merge(String id, PipelineTemplateTaskEditDto editDto){
		RestResponse<PipelineTemplateTaskVO> result = pipelineTemplateTaskClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TEMPLATE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineTemplateTaskVO find(String id){
		RestResponse<PipelineTemplateTaskVO> result = pipelineTemplateTaskClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TEMPLATE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineTemplateTaskVO> list(PageSearchRequest<PipelineTemplateTaskCondition> pageSearchRequest) {
		RestResponse<PageContent<PipelineTemplateTaskVO>> result = pipelineTemplateTaskClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TEMPLATE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
