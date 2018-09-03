package net.aicoder.speedcloud.business.pipeline.exec.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecInstanceNodeParamDao;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecInstanceNodeParamSpecification;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNodeParam;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineExecInstanceNodeParamService")
public class PipelineExecInstanceNodeParamService  extends GenericCrudService<PipelineExecInstanceNodeParam, Long, PipelineExecInstanceNodeParamCondition, PipelineExecInstanceNodeParamDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceNodeParamService.class);

	@Override
	public Specification<PipelineExecInstanceNodeParam> getSpecification(PipelineExecInstanceNodeParamCondition condition) {
		return new PipelineExecInstanceNodeParamSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineExecInstanceNodeParam.PROPERTY_NODE);
		return sort;
	}
}