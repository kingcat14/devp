package net.aicoder.speedcloud.console.business.speedCloud.pipeline.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskCondition;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskVO;
import net.aicoder.speedcloud.client.pipeline.PipelineStageNodeRibbon;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageNodePageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageNodeResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeEditDto;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageNodeVO;

import net.aicoder.speedcloud.client.pipeline.task.PipelineTaskRibbon;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskPageResult;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("pipelineStageNodeRibbonService")
public class PipelineStageNodeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageNodeRibbonService.class);


	@Autowired
	private PipelineStageNodeRibbon pipelineStageNodeRibbon;

	@Autowired
	private PipelineTaskRibbon pipelineTaskRibbon;


	public PipelineStageNodeVO add(PipelineStageNodeAddDto addDto){
		PipelineStageNodeResult result = pipelineStageNodeRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		PipelineStageNodeResult result = pipelineStageNodeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineStageNodeVO merge(Long id, PipelineStageNodeEditDto editDto){
		PipelineStageNodeResult result = pipelineStageNodeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineStageNodeVO find(Long id){
		PipelineStageNodeResult result = pipelineStageNodeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineStageNodeVO> list(PageSearchRequest<PipelineStageNodeCondition> pageSearchRequest) {
		PipelineStageNodePageResult result = pipelineStageNodeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<PipelineStageNodeVO> selectable(PageSearchRequest<PipelineStageNodeCondition> pageSearchRequest) {

		PageSearchRequest<PipelineTaskCondition> taskPageSearchRequest = new PageSearchRequest<>();
		taskPageSearchRequest.setPage(1);
		taskPageSearchRequest.setLimit(Integer.MAX_VALUE);

		PipelineTaskCondition taskCondition = new PipelineTaskCondition();
		taskCondition.setTaskType(pageSearchRequest.getSearchCondition().getNodeType());
		taskCondition.setTid(pageSearchRequest.getSearchCondition().getTid());

		PipelineTaskPageResult result = pipelineTaskRibbon.list(taskPageSearchRequest);


		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}


		List<PipelineStageNodeVO> resultList = new ArrayList<>();
		PipelineStageNodeVO nodeVO = null;
		for(PipelineTaskVO taskVO : result.getData().getContent()){
			nodeVO = new PipelineStageNodeVO();
			nodeVO.setNodeType(taskVO.getTaskType());
			nodeVO.setNodeId(taskVO.getId());
			nodeVO.setId(taskVO.getId());
			resultList.add(nodeVO);
		}


		return new PageContent<>(resultList, Long.parseLong(result.getData().getContent().size()+""));
	}

}
