package net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.exec.PipelineExecInstanceNodeParamRibbon;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceNodeParamPageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceNodeParamResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceNodeParamVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineExecInstanceNodeParamRibbonService")
public class PipelineExecInstanceNodeParamRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceNodeParamRibbonService.class);


	@Autowired
	private PipelineExecInstanceNodeParamRibbon pipelineExecInstanceNodeParamRibbon;


	public PipelineExecInstanceNodeParamVO add(PipelineExecInstanceNodeParamAddDto addDto){
		PipelineExecInstanceNodeParamResult result = pipelineExecInstanceNodeParamRibbon.add(addDto);

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
		PipelineExecInstanceNodeParamResult result = pipelineExecInstanceNodeParamRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineExecInstanceNodeParamVO merge(Long id, PipelineExecInstanceNodeParamEditDto editDto){
		PipelineExecInstanceNodeParamResult result = pipelineExecInstanceNodeParamRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineExecInstanceNodeParamVO find(Long id){
		PipelineExecInstanceNodeParamResult result = pipelineExecInstanceNodeParamRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineExecInstanceNodeParamVO> list(PageSearchRequest<PipelineExecInstanceNodeParamCondition> pageSearchRequest) {
		PipelineExecInstanceNodeParamPageResult result = pipelineExecInstanceNodeParamRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
