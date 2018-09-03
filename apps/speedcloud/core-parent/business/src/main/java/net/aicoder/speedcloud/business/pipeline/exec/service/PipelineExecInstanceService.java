package net.aicoder.speedcloud.business.pipeline.exec.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecInstanceDao;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecInstanceSpecification;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("pipelineExecInstanceService")
public class PipelineExecInstanceService  extends GenericCrudService<PipelineExecInstance, Long, PipelineExecInstanceCondition, PipelineExecInstanceDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceService.class);


	@Override
	public Specification<PipelineExecInstance> getSpecification(PipelineExecInstanceCondition condition) {
		return new PipelineExecInstanceSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineExecInstance.PROPERTY_CODE);
		return sort;
	}
}