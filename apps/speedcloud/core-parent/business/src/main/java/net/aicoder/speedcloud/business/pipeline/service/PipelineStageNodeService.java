package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineStageNodeDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineStageNodeSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNode;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamAddDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("pipelineStageNodeService")
public class PipelineStageNodeService  extends GenericCrudService<PipelineStageNode, Long, PipelineStageNodeCondition, PipelineStageNodeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageNodeService.class);

	@Autowired
	private PipelineStageNodeParamService pipelineStageNodeParamService;

	@Transactional
	public void create(List<PipelineStageNodeAddDto> pipelineStageNodeAddDtoList){
		if(CollectionUtils.isEmpty(pipelineStageNodeAddDtoList)){
			return;
		}
		for(PipelineStageNodeAddDto addDto:pipelineStageNodeAddDtoList){
			create(addDto);
		}
	}

	@Transactional
	public void create(PipelineStageNodeAddDto addDto){

		PipelineStageNode pipelineStageNode = new PipelineStageNode();
		BeanUtils.copyProperties(addDto, pipelineStageNode);

		this.add(pipelineStageNode);

		List<PipelineStageNodeParamAddDto> paramList = addDto.getParamList();

		if(CollectionUtils.isEmpty(paramList)){
			return;
		}
		for(PipelineStageNodeParamAddDto paramAddDto : paramList){
			paramAddDto.setPipelineStageNode(pipelineStageNode.getId());
		}

		pipelineStageNodeParamService.create(paramList);
	}


	@Transactional
	public int deleteForStage(Long stageId){

		if(stageId == null){
			return 0;
		}

		List<PipelineStageNode> stageNodeList = dao.findByStage(stageId);
		if(CollectionUtils.isEmpty(stageNodeList)){
			return 0;
		}
		for(PipelineStageNode stageNode : stageNodeList){
			this.delete(stageNode.getId());
		}
		return CollectionUtils.size(stageNodeList);
	}

	@Transactional
	public void delete(Long pipelineStageNodeId){
		LOGGER.debug("delete t:{}", pipelineStageNodeId);
		pipelineStageNodeParamService.deleteByNodeId(pipelineStageNodeId);
		dao.delete(pipelineStageNodeId);
	}

	public List<PipelineStageNode> findByStage(Long stageId){
		return dao.findByStage(stageId);
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