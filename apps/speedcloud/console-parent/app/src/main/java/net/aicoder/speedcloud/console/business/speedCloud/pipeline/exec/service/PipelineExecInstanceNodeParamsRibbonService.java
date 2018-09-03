package net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.exec.PipelineExecInstanceNodeParamsRibbon;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceNodeParamsPageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceNodeParamsResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamsCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamsAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamsEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceNodeParamsVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineExecInstanceNodeParamsRibbonService")
public class PipelineExecInstanceNodeParamsRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceNodeParamsRibbonService.class);


	@Autowired
	private PipelineExecInstanceNodeParamsRibbon pipelineExecInstanceNodeParamsRibbon;


	public PipelineExecInstanceNodeParamsVO add(PipelineExecInstanceNodeParamsAddDto addDto){
		PipelineExecInstanceNodeParamsResult result = pipelineExecInstanceNodeParamsRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		PipelineExecInstanceNodeParamsResult result = pipelineExecInstanceNodeParamsRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineExecInstanceNodeParamsVO merge(Long id, PipelineExecInstanceNodeParamsEditDto editDto){
		PipelineExecInstanceNodeParamsResult result = pipelineExecInstanceNodeParamsRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineExecInstanceNodeParamsVO find(Long id){
		PipelineExecInstanceNodeParamsResult result = pipelineExecInstanceNodeParamsRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineExecInstanceNodeParamsVO> list(PageSearchRequest<PipelineExecInstanceNodeParamsCondition> pageSearchRequest) {
		PipelineExecInstanceNodeParamsPageResult result = pipelineExecInstanceNodeParamsRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
