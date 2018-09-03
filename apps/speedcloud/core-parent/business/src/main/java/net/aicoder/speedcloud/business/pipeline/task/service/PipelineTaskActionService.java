package net.aicoder.speedcloud.business.pipeline.task.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskActionDao;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskActionSpecification;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskAction;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("pipelineTaskActionService")
public class PipelineTaskActionService  extends GenericCrudService<PipelineTaskAction, Long, PipelineTaskActionCondition, PipelineTaskActionDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionService.class);


	public List<PipelineTaskAction> findByTask(Long taskId){
		return dao.findByTask(taskId);
	}

	@Override
	public Specification<PipelineTaskAction> getSpecification(PipelineTaskActionCondition condition) {
		return new PipelineTaskActionSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineTaskAction.PROPERTY_TASK);
		return sort;
	}
}