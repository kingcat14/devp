package net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionCondition;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionEditDto;
import net.aicoder.speedcloud.business.pipeline.template.vo.PipelineTemplateTaskActionVO;
import net.aicoder.speedcloud.client.pipeline.template.PipelineTemplateTaskActionClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineTemplateTaskActionRibbonService")
public class PipelineTemplateTaskActionRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTemplateTaskActionRibbonService.class);


	@Autowired
	private PipelineTemplateTaskActionClient pipelineTemplateTaskActionClient;


	public PipelineTemplateTaskActionVO add(PipelineTemplateTaskActionAddDto addDto){
		RestResponse<PipelineTemplateTaskActionVO> result = pipelineTemplateTaskActionClient.add(addDto);

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
		RestResponse<PipelineTemplateTaskActionVO> result = pipelineTemplateTaskActionClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TEMPLATE", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineTemplateTaskActionVO merge(String id, PipelineTemplateTaskActionEditDto editDto){
		RestResponse<PipelineTemplateTaskActionVO> result = pipelineTemplateTaskActionClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TEMPLATE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineTemplateTaskActionVO find(String id){
		RestResponse<PipelineTemplateTaskActionVO> result = pipelineTemplateTaskActionClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TEMPLATE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineTemplateTaskActionVO> list(PageSearchRequest<PipelineTemplateTaskActionCondition> pageSearchRequest) {
		RestResponse<PageContent<PipelineTemplateTaskActionVO>> result = pipelineTemplateTaskActionClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TEMPLATE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
