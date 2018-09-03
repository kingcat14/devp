package net.aicoder.speedcloud.business.pipeline.exec.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecInstanceNodeParamsDao;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecInstanceNodeParamsSpecification;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNodeParams;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamsCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineExecInstanceNodeParamsService")
public class PipelineExecInstanceNodeParamsService  extends GenericCrudService<PipelineExecInstanceNodeParams, Long, PipelineExecInstanceNodeParamsCondition, PipelineExecInstanceNodeParamsDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceNodeParamsService.class);

	@Override
	public Specification<PipelineExecInstanceNodeParams> getSpecification(PipelineExecInstanceNodeParamsCondition condition) {
		return new PipelineExecInstanceNodeParamsSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineExecInstanceNodeParams.PROPERTY_TASK_TYPE);
		return sort;
	}
}