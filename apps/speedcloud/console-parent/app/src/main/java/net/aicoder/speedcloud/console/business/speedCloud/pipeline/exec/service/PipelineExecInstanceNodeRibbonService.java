package net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.exec.PipelineExecInstanceNodeRibbon;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceNodePageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceNodeResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceNodeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineExecInstanceNodeRibbonService")
public class PipelineExecInstanceNodeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceNodeRibbonService.class);


	@Autowired
	private PipelineExecInstanceNodeRibbon pipelineExecInstanceNodeRibbon;


	public PipelineExecInstanceNodeVO add(PipelineExecInstanceNodeAddDto addDto){
		PipelineExecInstanceNodeResult result = pipelineExecInstanceNodeRibbon.add(addDto);

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
		PipelineExecInstanceNodeResult result = pipelineExecInstanceNodeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineExecInstanceNodeVO merge(Long id, PipelineExecInstanceNodeEditDto editDto){
		PipelineExecInstanceNodeResult result = pipelineExecInstanceNodeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineExecInstanceNodeVO find(Long id){
		PipelineExecInstanceNodeResult result = pipelineExecInstanceNodeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineExecInstanceNodeVO> list(PageSearchRequest<PipelineExecInstanceNodeCondition> pageSearchRequest) {
		PipelineExecInstanceNodePageResult result = pipelineExecInstanceNodeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
