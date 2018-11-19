package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import net.aicoder.speedcloud.business.pipeline.dto.*;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("pipelineService")
public class PipelineService  extends GenericCrudService<Pipeline, Long, PipelineCondition, PipelineDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineService.class);

	@Autowired
	private PipelineStageService pipelineStageService;


	@Autowired
	private PipelineParamService pipelineParamService;

	/**
	 * 创建一个流水线
	 * @param pipelineAddDto
	 * @return
	 */
	@Transactional
	public Pipeline create(PipelineAddDto pipelineAddDto){

		/*
		 * 0.TODO 检查流水线的节点是不是会导致循环引用 流水线A-->流水线B-->流水线C-->流水线A
		 * 1.创建流水线
		 * 2.创建流水线参数
		 * 3.创建流水线阶段
		 */

		//0.
		//捞出流水线中所有类型为流线的节点,盘点其不为自身后，递归检查节点包含的其他节点

		//1.
		Pipeline pipeline = new Pipeline();
		BeanUtils.copyProperties(pipelineAddDto, pipeline);
		this.add(pipeline);

		//2.
		if(CollectionUtils.isNotEmpty(pipelineAddDto.getParamList())){
			for(PipelineParamAddDto addDto : pipelineAddDto.getParamList()){
				addDto.setPipeline(pipeline.getId());
			}
		}
		pipelineParamService.create(pipelineAddDto.getParamList());

		//3.
		if(CollectionUtils.isNotEmpty(pipelineAddDto.getStageList())){
			for(PipelineStageAddDto addDto : pipelineAddDto.getStageList()){
				addDto.setPipeline(pipeline.getId());
			}
		}
		pipelineStageService.create(pipelineAddDto.getStageList());

		return pipeline;

	}



	@Transactional
	public void delete(Long id){
		pipelineParamService.deleteForPipeline(id);
		pipelineStageService.deleteForPipeline(id);
		dao.delete(id);
	}


	@Transactional
	public Pipeline update(PipelineEditDto pipelineEditDto){

		//1.
		Pipeline pipeline = dao.findOne(pipelineEditDto.getId());
		BeanUtils.copyProperties(pipelineEditDto, pipeline);
		this.merge(pipeline);

		//2.
		pipelineParamService.deleteForPipeline(pipelineEditDto.getId());
		if(CollectionUtils.isNotEmpty(pipelineEditDto.getParamList())){
			for(PipelineParamAddDto addDto : pipelineEditDto.getParamList()){
				addDto.setPipeline(pipeline.getId());
			}
		}
		pipelineParamService.create(pipelineEditDto.getParamList());

		//3.
		pipelineStageService.deleteForPipeline(pipelineEditDto.getId());
		if(CollectionUtils.isNotEmpty(pipelineEditDto.getStageList())){
			for(PipelineStageAddDto addDto : pipelineEditDto.getStageList()){
				addDto.setPipeline(pipeline.getId());
			}
		}
		pipelineStageService.create(pipelineEditDto.getStageList());

		return pipeline;
	}

	@Override
	public Specification<Pipeline> getSpecification(PipelineCondition condition) {
		return new PipelineSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Pipeline.PROPERTY_NAME);
		return sort;
	}
}