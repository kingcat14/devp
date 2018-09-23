package net.aicoder.speedcloud.console.business.speedCloud.pipeline.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskCondition;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskVO;
import net.aicoder.speedcloud.client.pipeline.task.PipelineTaskRibbon;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskPageResult;
import net.aicoder.speedcloud.console.business.pipeline.dto.PipelineStageSelectableNodeCondition;
import net.aicoder.speedcloud.console.business.pipeline.vo.PipelineStageSelectableNodeVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("pipelineStageSelectableNodeRibbonService")
public class PipelineStageSelectableNodeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageSelectableNodeRibbonService.class);

	@Autowired
	private PipelineTaskRibbon pipelineTaskRibbon;

	public PageContent<PipelineStageSelectableNodeVO> selectable(PageSearchRequest<PipelineStageSelectableNodeCondition> pageSearchRequest) {

		if(StringUtils.equalsIgnoreCase(pageSearchRequest.getSearchCondition().getType(), "COMPILE")){
			return selectableTask(pageSearchRequest);
		}

		return selectableTask(pageSearchRequest);
	}

	private PageContent<PipelineStageSelectableNodeVO> selectableTask(PageSearchRequest<PipelineStageSelectableNodeCondition> pageSearchRequest) {

		PageSearchRequest<PipelineTaskCondition> taskPageSearchRequest = new PageSearchRequest<>();
		taskPageSearchRequest.setPage(0);
		taskPageSearchRequest.setLimit(Integer.MAX_VALUE);


		PipelineTaskCondition taskCondition = new PipelineTaskCondition();
		taskCondition.setTaskType(pageSearchRequest.getSearchCondition().getType());
		taskCondition.setTid(pageSearchRequest.getSearchCondition().getTid());
		taskPageSearchRequest.setSearchCondition(taskCondition);

		PipelineTaskPageResult result = pipelineTaskRibbon.list(taskPageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE", result.getCode()+"", result.getMessage());
		}

		List<PipelineStageSelectableNodeVO> resultList = new ArrayList<>();
		PipelineStageSelectableNodeVO nodeVO = null;
		for(PipelineTaskVO taskVO : result.getData().getContent()){
			nodeVO = new PipelineStageSelectableNodeVO();
			nodeVO.setName(taskVO.getName());
//			nodeVO.setType("TASK");
			nodeVO.setType(taskVO.getTaskType());
			nodeVO.setId(taskVO.getId());
			resultList.add(nodeVO);
		}

		return new PageContent<>(resultList, Long.parseLong(result.getData().getContent().size()+""));
	}
}
