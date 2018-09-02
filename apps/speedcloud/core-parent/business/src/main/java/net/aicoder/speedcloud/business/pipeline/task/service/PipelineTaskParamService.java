package net.aicoder.speedcloud.business.pipeline.task.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskParamDao;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskParamSpecification;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskParam;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineTaskParamService")
public class PipelineTaskParamService  extends GenericCrudService<PipelineTaskParam, Long, PipelineTaskParamCondition, PipelineTaskParamDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskParamService.class);

	@Override
	public Specification<PipelineTaskParam> getSpecification(PipelineTaskParamCondition condition) {
		return new PipelineTaskParamSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineTaskParam.PROPERTY_TASK_TYPE);
		return sort;
	}
}