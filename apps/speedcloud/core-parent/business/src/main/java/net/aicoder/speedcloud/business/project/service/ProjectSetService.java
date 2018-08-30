package net.aicoder.speedcloud.business.project.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.project.dao.ProjectSetDao;
import net.aicoder.speedcloud.business.project.dao.ProjectSetSpecification;
import net.aicoder.speedcloud.business.project.domain.ProjectSet;
import net.aicoder.speedcloud.business.project.dto.ProjectSetCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("projectSetService")
public class ProjectSetService  extends GenericCrudService<ProjectSet, Long, ProjectSetCondition, ProjectSetDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectSetService.class);

	@Override
	public Specification<ProjectSet> getSpecification(ProjectSetCondition condition) {
		return new ProjectSetSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, ProjectSet.PROPERTY_TID);
		return sort;
	}
}