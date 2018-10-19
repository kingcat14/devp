package net.aicoder.speedcloud.console.business.speedcloud.pipeline.exec.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.exec.PipelineExecNodeLogRibbon;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodeLogPageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodeLogResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeLogCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeLogAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeLogEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecNodeLogVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineExecNodeLogRibbonService")
public class PipelineExecNodeLogRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeLogRibbonService.class);


	@Autowired
	private PipelineExecNodeLogRibbon pipelineExecNodeLogRibbon;



	public PipelineExecNodeLogVO find(Long id){
		PipelineExecNodeLogResult result = pipelineExecNodeLogRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineExecNodeLogVO> list(PageSearchRequest<PipelineExecNodeLogCondition> pageSearchRequest) {
		PipelineExecNodeLogPageResult result = pipelineExecNodeLogRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
