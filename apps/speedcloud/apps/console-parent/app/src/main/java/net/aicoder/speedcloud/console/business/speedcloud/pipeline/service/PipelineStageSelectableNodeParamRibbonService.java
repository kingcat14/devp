package net.aicoder.speedcloud.console.business.speedcloud.pipeline.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageSelectableNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamCondition;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskParamVO;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineStageSelectableNodeParamVO;
import net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.service.PipelineTaskParamRibbonService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("pipelineStageSelectableNodeParamRibbonService")
public class PipelineStageSelectableNodeParamRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageSelectableNodeParamRibbonService.class);


	@Autowired
	private PipelineTaskParamRibbonService pipelineTaskParamRibbonService;





	public PageContent<PipelineStageSelectableNodeParamVO> list(PageSearchRequest<PipelineStageSelectableNodeParamCondition> pageSearchRequest) {
		if(StringUtils.equalsIgnoreCase(pageSearchRequest.getSearchCondition().getNodeType(), "TASK")){
			return taskParam(pageSearchRequest);
		}

		return taskParam(pageSearchRequest);
	}

	public PageContent<PipelineStageSelectableNodeParamVO> taskParam(PageSearchRequest<PipelineStageSelectableNodeParamCondition> pageSearchRequest) {

		PipelineTaskParamCondition condition = new PipelineTaskParamCondition();
		condition.setTid(pageSearchRequest.getSearchCondition().getTid());
		condition.setTask(pageSearchRequest.getSearchCondition().getNodeId());

		PageSearchRequest<PipelineTaskParamCondition> taskParamPageSearchRequest = new PageSearchRequest<>();
		taskParamPageSearchRequest.setLimit(Integer.MAX_VALUE);
		taskParamPageSearchRequest.setPage(0);
		taskParamPageSearchRequest.setSearchCondition(condition);


		PageContent<PipelineTaskParamVO> taskParamVOPageContent = pipelineTaskParamRibbonService.list(taskParamPageSearchRequest);

		List<PipelineTaskParamVO> taskParamVOList = taskParamVOPageContent.getContent();

		List<PipelineStageSelectableNodeParamVO> resultList = new ArrayList<>();
		PipelineStageSelectableNodeParamVO nodeParamVO;
		for(PipelineTaskParamVO taskParamVO : taskParamVOList){
			nodeParamVO = new PipelineStageSelectableNodeParamVO();
			BeanUtils.copyProperties(taskParamVO, nodeParamVO);
			nodeParamVO.setNodeId(taskParamVO.getId());
			nodeParamVO.setNodeType(pageSearchRequest.getSearchCondition().getNodeType());
			resultList.add(nodeParamVO);
		}

		return new PageContent<>(resultList, resultList.size());

	}
}
