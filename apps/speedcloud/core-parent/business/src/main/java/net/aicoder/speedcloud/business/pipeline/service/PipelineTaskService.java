package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineTaskDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineTaskSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineTaskService")
public class PipelineTaskService  extends GenericCrudService<PipelineTask, Long, PipelineTaskCondition, PipelineTaskDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskService.class);

	@Override
	public Specification<PipelineTask> getSpecification(PipelineTaskCondition condition) {
		return new PipelineTaskSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineTask.PROPERTY_STAGE);
		return sort;
	}
}