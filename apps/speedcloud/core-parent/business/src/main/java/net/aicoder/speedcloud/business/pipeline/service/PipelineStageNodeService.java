package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineStageNodeDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineStageNodeSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNode;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("pipelineStageNodeService")
public class PipelineStageNodeService  extends GenericCrudService<PipelineStageNode, Long, PipelineStageNodeCondition, PipelineStageNodeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageNodeService.class);

	public void create(List<PipelineStageNodeAddDto> pipelineStageNodeAddDtoList){
		if(CollectionUtils.isEmpty(pipelineStageNodeAddDtoList)){
			return;
		}
		PipelineStageNode pipelineStageNode = null;
		for(PipelineStageNodeAddDto addDto:pipelineStageNodeAddDtoList){
			pipelineStageNode = new PipelineStageNode();
			BeanUtils.copyProperties(addDto, pipelineStageNode);
			this.add(pipelineStageNode);
		}
	}

	public int deleteForStage(Long stageId){
		return dao.deleteByStage(stageId);
	}

	@Override
	public Specification<PipelineStageNode> getSpecification(PipelineStageNodeCondition condition) {
		return new PipelineStageNodeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineStageNode.PROPERTY_NAME);
		return sort;
	}
}