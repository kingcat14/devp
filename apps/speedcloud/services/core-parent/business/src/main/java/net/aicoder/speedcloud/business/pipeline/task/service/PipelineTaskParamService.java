package net.aicoder.speedcloud.business.pipeline.task.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskParamDao;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskParamSpecification;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskParam;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("pipelineTaskParamService")
public class PipelineTaskParamService  extends GenericCrudService<PipelineTaskParam, Long, PipelineTaskParamCondition, PipelineTaskParamDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskParamService.class);

	public List<PipelineTaskParam> findByTask(Long taskId){
		return dao.findByTask(taskId);
	}

	public  int deleteByTaskId(Long taskId){
		return dao.deleteByTask(taskId);
	}

	@Override
	public Specification<PipelineTaskParam> getSpecification(PipelineTaskParamCondition condition) {
		return new PipelineTaskParamSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineTaskParam.PROPERTY_TASK);
		return sort;
	}
}