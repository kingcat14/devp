package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineStageDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineStageSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNode;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeAddDto;
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


@Service("pipelineStageService")
public class PipelineStageService  extends GenericCrudService<PipelineStage, Long, PipelineStageCondition, PipelineStageDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageService.class);

	@Autowired
	private PipelineStageNodeService pipelineStageNodeService;
	/**
	 * 创建流水线阶段
	 * @param pipelineStageAddDtoList
	 */
	@Transactional
	public void create(List<PipelineStageAddDto> pipelineStageAddDtoList){

		if(CollectionUtils.isEmpty(pipelineStageAddDtoList)){
			return;
		}

		for(PipelineStageAddDto addDto : pipelineStageAddDtoList){
			create(addDto);
		}
	}

	@Transactional
	public void create(PipelineStageAddDto pipelineStageAddDto){


		PipelineStage pipelineStage = new PipelineStage();;

		BeanUtils.copyProperties(pipelineStageAddDto, pipelineStage);
		this.add(pipelineStage);

		if(CollectionUtils.isNotEmpty(pipelineStageAddDto.getNodeList())){
			for(PipelineStageNodeAddDto pipelineStageNodeAddDto : pipelineStageAddDto.getNodeList()){
				pipelineStageNodeAddDto.setStage(pipelineStage.getId());
			}
			pipelineStageNodeService.create(pipelineStageAddDto.getNodeList());
		}

	}

	@Transactional
	public int deleteForPipeline(Long pipelineId){
		if(pipelineId == null){
			return 0;
		}

		List<PipelineStage> stageList = dao.findByPipeline(pipelineId);
		if(CollectionUtils.isEmpty(stageList)){
			return 0;
		}
		for(PipelineStage stage : stageList){
			this.delete(stage.getId());
		}
		return CollectionUtils.size(stageList);


	}



	@Transactional
	public void delete(PipelineStage pipelineStage){
		this.delete(pipelineStage.getId());
	}

	@Transactional
	public void delete(Long pipelineStageId){
		LOGGER.debug("delete t:{}", pipelineStageId);
		pipelineStageNodeService.deleteForStage(pipelineStageId);
		dao.delete(pipelineStageId);
	}


	public List<PipelineStage> findForPipeline(Long pipelineId){
		return dao.findByPipeline(pipelineId);
	}

	@Override
	public Specification<PipelineStage> getSpecification(PipelineStageCondition condition) {
		return new PipelineStageSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineStage.PROPERTY_PIPELINE);
		return sort;
	}
}