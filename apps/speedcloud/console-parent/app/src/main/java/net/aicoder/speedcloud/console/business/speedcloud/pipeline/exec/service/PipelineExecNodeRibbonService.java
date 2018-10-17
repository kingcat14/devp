package net.aicoder.speedcloud.console.business.speedcloud.pipeline.exec.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeCondition;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecNodeVO;
import net.aicoder.speedcloud.client.pipeline.exec.PipelineExecNodeRibbon;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodePageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineExecNodeRibbonService")
public class PipelineExecNodeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeRibbonService.class);


	@Autowired
	private PipelineExecNodeRibbon pipelineExecNodeRibbon;

	public PipelineExecNodeVO add(PipelineExecNodeAddDto addDto){
		PipelineExecNodeResult result = pipelineExecNodeRibbon.add(addDto);

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
		PipelineExecNodeResult result = pipelineExecNodeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
	}

	public PipelineExecNodeVO find(Long id){
		PipelineExecNodeResult result = pipelineExecNodeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineExecNodeVO> list(PageSearchRequest<PipelineExecNodeCondition> pageSearchRequest) {
		PipelineExecNodePageResult result = pipelineExecNodeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
