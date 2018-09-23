package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineStageNodeParamDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineStageNodeParamSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNode;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNodeParam;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("pipelineStageNodeParamService")
public class PipelineStageNodeParamService  extends GenericCrudService<PipelineStageNodeParam, Long, PipelineStageNodeParamCondition, PipelineStageNodeParamDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageNodeParamService.class);


	public List<PipelineStageNodeParam> findByNodeId(Long nodeId){
		return dao.findByPipelineStageNode(nodeId);
	}

	public int deleteByNodeId(Long nodeId){
		return dao.deleteByPipelineStageNode(nodeId);
	}


	@Transactional
	public void create(List<PipelineStageNodeParamAddDto> pipelineStageNodeParamAddDtoList){
		if(CollectionUtils.isEmpty(pipelineStageNodeParamAddDtoList)){
			return;
		}
		PipelineStageNodeParam pipelineStageNodeParam;
		for(PipelineStageNodeParamAddDto addDto : pipelineStageNodeParamAddDtoList){
			pipelineStageNodeParam = new PipelineStageNodeParam();
			BeanUtils.copyProperties(addDto, pipelineStageNodeParam);
			this.add(pipelineStageNodeParam);
		}
	}

	@Override
	public Specification<PipelineStageNodeParam> getSpecification(PipelineStageNodeParamCondition condition) {
		return new PipelineStageNodeParamSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineStageNodeParam.PROPERTY_VIEW_ORDER);
		return sort;
	}
}