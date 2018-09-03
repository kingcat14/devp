package net.aicoder.speedcloud.business.pipeline.exec.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecInstanceNodeDao;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecInstanceNodeSpecification;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNode;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineExecInstanceNodeService")
public class PipelineExecInstanceNodeService  extends GenericCrudService<PipelineExecInstanceNode, Long, PipelineExecInstanceNodeCondition, PipelineExecInstanceNodeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceNodeService.class);

	@Override
	public Specification<PipelineExecInstanceNode> getSpecification(PipelineExecInstanceNodeCondition condition) {
		return new PipelineExecInstanceNodeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineExecInstanceNode.PROPERTY_CODE);
		return sort;
	}
}