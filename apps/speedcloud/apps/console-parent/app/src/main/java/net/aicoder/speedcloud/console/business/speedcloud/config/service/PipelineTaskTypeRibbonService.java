package net.aicoder.speedcloud.console.business.speedcloud.config.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeAddDto;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeCondition;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeEditDto;
import net.aicoder.speedcloud.business.config.vo.PipelineTaskTypeVO;
import net.aicoder.speedcloud.client.config.PipelineTaskTypeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineTaskTypeRibbonService")
public class PipelineTaskTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskTypeRibbonService.class);


	@Autowired
	private PipelineTaskTypeClient pipelineTaskTypeClient;


	public PipelineTaskTypeVO add(PipelineTaskTypeAddDto addDto){
		RestResponse<PipelineTaskTypeVO> result = pipelineTaskTypeClient.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		RestResponse<PipelineTaskTypeVO> result = pipelineTaskTypeClient.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineTaskTypeVO merge(String id, PipelineTaskTypeEditDto editDto){
		RestResponse<PipelineTaskTypeVO> result = pipelineTaskTypeClient.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineTaskTypeVO find(String id){
		RestResponse<PipelineTaskTypeVO> result = pipelineTaskTypeClient.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineTaskTypeVO> list(PageSearchRequest<PipelineTaskTypeCondition> pageSearchRequest) {
		RestResponse<PageContent<PipelineTaskTypeVO>> result = pipelineTaskTypeClient.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
