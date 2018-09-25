package net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.exec.PipelineExecNodeParamRibbon;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodeParamPageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodeParamResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecNodeParamVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineExecNodeParamRibbonService")
public class PipelineExecNodeParamRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeParamRibbonService.class);


	@Autowired
	private PipelineExecNodeParamRibbon pipelineExecNodeParamRibbon;


	public PipelineExecNodeParamVO add(PipelineExecNodeParamAddDto addDto){
		PipelineExecNodeParamResult result = pipelineExecNodeParamRibbon.add(addDto);

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
		PipelineExecNodeParamResult result = pipelineExecNodeParamRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineExecNodeParamVO merge(Long id, PipelineExecNodeParamEditDto editDto){
		PipelineExecNodeParamResult result = pipelineExecNodeParamRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineExecNodeParamVO find(Long id){
		PipelineExecNodeParamResult result = pipelineExecNodeParamRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineExecNodeParamVO> list(PageSearchRequest<PipelineExecNodeParamCondition> pageSearchRequest) {
		PipelineExecNodeParamPageResult result = pipelineExecNodeParamRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
