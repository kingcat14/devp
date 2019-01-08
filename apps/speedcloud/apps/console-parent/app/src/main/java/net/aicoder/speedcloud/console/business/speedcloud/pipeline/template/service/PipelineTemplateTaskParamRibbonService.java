package net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskParamAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskParamCondition;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskParamEditDto;
import net.aicoder.speedcloud.business.pipeline.template.vo.PipelineTemplateTaskParamVO;
import net.aicoder.speedcloud.client.pipeline.template.PipelineTemplateTaskParamClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineTemplateTaskParamRibbonService")
public class PipelineTemplateTaskParamRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTemplateTaskParamRibbonService.class);


	@Autowired
	private PipelineTemplateTaskParamClient pipelineTemplateTaskParamClient;


	public PipelineTemplateTaskParamVO add(PipelineTemplateTaskParamAddDto addDto){
		RestResponse<PipelineTemplateTaskParamVO> result = pipelineTemplateTaskParamClient.add(addDto);

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
		RestResponse<PipelineTemplateTaskParamVO> result = pipelineTemplateTaskParamClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TEMPLATE", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineTemplateTaskParamVO merge(String id, PipelineTemplateTaskParamEditDto editDto){
		RestResponse<PipelineTemplateTaskParamVO> result = pipelineTemplateTaskParamClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TEMPLATE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineTemplateTaskParamVO find(String id){
		RestResponse<PipelineTemplateTaskParamVO> result = pipelineTemplateTaskParamClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TEMPLATE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineTemplateTaskParamVO> list(PageSearchRequest<PipelineTemplateTaskParamCondition> pageSearchRequest) {
		RestResponse<PageContent<PipelineTemplateTaskParamVO>> result = pipelineTemplateTaskParamClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.TEMPLATE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
