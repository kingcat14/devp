package net.aicoder.speedcloud.business.pipeline.exec.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecNodeParamDao;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecNodeParamSpecification;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNodeParam;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("pipelineExecNodeParamService")
public class PipelineExecNodeParamService  extends GenericCrudService<PipelineExecNodeParam, Long, PipelineExecNodeParamCondition, PipelineExecNodeParamDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeParamService.class);

	public List<PipelineExecNodeParam> findByNode(long instanceNode){
		return dao.findByNode(instanceNode);
	}

	@Override
	public Specification<PipelineExecNodeParam> getSpecification(PipelineExecNodeParamCondition condition) {
		return new PipelineExecNodeParamSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineExecNodeParam.PROPERTY_NODE);
		return sort;
	}
}