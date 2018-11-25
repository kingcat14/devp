package net.aicoder.speedcloud.business.project.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.project.dao.ProjectDao;
import net.aicoder.speedcloud.business.project.dao.ProjectSpecification;
import net.aicoder.speedcloud.business.project.domain.Project;
import net.aicoder.speedcloud.business.project.dto.ProjectCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("projectService")
public class ProjectService  extends GenericCrudService<Project, String, ProjectCondition, ProjectDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

	@Override
	public Specification<Project> getSpecification(ProjectCondition condition) {
		return new ProjectSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Project.PROPERTY_NAME);
		return sort;
	}
}