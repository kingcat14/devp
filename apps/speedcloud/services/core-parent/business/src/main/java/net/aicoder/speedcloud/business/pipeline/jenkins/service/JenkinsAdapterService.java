package net.aicoder.speedcloud.business.pipeline.jenkins.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.pipeline.jenkins.dao.JenkinsAdapterDao;
import net.aicoder.speedcloud.business.pipeline.jenkins.dao.JenkinsAdapterSpecification;
import net.aicoder.speedcloud.business.pipeline.jenkins.domain.JenkinsAdapter;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterCondition;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("jenkinsAdapterService")
@Slf4j
public class JenkinsAdapterService  extends GenericCrudService<JenkinsAdapter, String, JenkinsAdapterCondition, JenkinsAdapterDao> {

	@Override
	public Specification<JenkinsAdapter> getSpecification(JenkinsAdapterCondition condition) {
		return new JenkinsAdapterSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, JenkinsAdapter.PROPERTY_MODIFY_AT);

		return sort;
	}
}