package net.aicoder.speedcloud.console.business.speedcloud.pipeline.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineCodeRepositoryRelationVO;
import net.aicoder.speedcloud.client.pipeline.PipelineCodeRepositoryRelationRibbon;
import net.aicoder.speedcloud.client.pipeline.result.PipelineCodeRepositoryRelationPageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineCodeRepositoryRelationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineCodeRepositoryRelationRibbonService")
public class PipelineCodeRepositoryRelationRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineCodeRepositoryRelationRibbonService.class);


	@Autowired
	private PipelineCodeRepositoryRelationRibbon pipelineCodeRepositoryRelationRibbon;


	public PipelineCodeRepositoryRelationVO add(PipelineCodeRepositoryRelationAddDto addDto){
		PipelineCodeRepositoryRelationResult result = pipelineCodeRepositoryRelationRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		PipelineCodeRepositoryRelationResult result = pipelineCodeRepositoryRelationRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineCodeRepositoryRelationVO merge(Long id, PipelineCodeRepositoryRelationEditDto editDto){
		PipelineCodeRepositoryRelationResult result = pipelineCodeRepositoryRelationRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineCodeRepositoryRelationVO find(Long id){
		PipelineCodeRepositoryRelationResult result = pipelineCodeRepositoryRelationRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineCodeRepositoryRelationVO> list(PageSearchRequest<PipelineCodeRepositoryRelationCondition> pageSearchRequest) {
		PipelineCodeRepositoryRelationPageResult result = pipelineCodeRepositoryRelationRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
