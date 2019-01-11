package net.aicoder.speedcloud.business.pipeline.jenkins.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.pipeline.jenkins.dao.JobMappingDao;
import net.aicoder.speedcloud.business.pipeline.jenkins.dao.JobMappingSpecification;
import net.aicoder.speedcloud.business.pipeline.jenkins.domain.JobMapping;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("jobMappingService")
@Slf4j
public class JobMappingService  extends GenericCrudService<JobMapping, String, JobMappingCondition, JobMappingDao> {

	@Override
	public Specification<JobMapping> getSpecification(JobMappingCondition condition) {
		return new JobMappingSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, JobMapping.PROPERTY_MODIFY_AT);

		return sort;
	}
}