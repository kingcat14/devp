package net.aicoder.speedcloud.business.config.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.config.dao.PipelineTaskTypeDao;
import net.aicoder.speedcloud.business.config.dao.PipelineTaskTypeSpecification;
import net.aicoder.speedcloud.business.config.domain.PipelineTaskType;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("pipelineTaskTypeService")
@Slf4j
public class PipelineTaskTypeService  extends GenericCrudService<PipelineTaskType, String, PipelineTaskTypeCondition, PipelineTaskTypeDao> {

	@Override
	public Specification<PipelineTaskType> getSpecification(PipelineTaskTypeCondition condition) {
		return new PipelineTaskTypeSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, PipelineTaskType.PROPERTY_CODE, PipelineTaskType.PROPERTY_MODIFY_AT);

		return sort;
	}
}