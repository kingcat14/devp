package com.kingzoo.kingcat.project.icode4.business.codegen.service;

import com.kingzoo.kingcat.commons.framework.util.IDGenerator;
import com.kingzoo.kingcat.project.icode4.business.codegen.dao.ProjectDao;
import com.kingzoo.kingcat.project.icode4.business.codegen.domain.Project;
import com.kingzoo.kingcat.project.icode4.business.codegen.vo.ProjectCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("projectService")
public class ProjectService {

	private static final Logger LOGGER = LoggerFactory.getLogger(Project.class);

	@Autowired
	@Qualifier("projectDao")
	private ProjectDao projectDao;

	@Transactional
	public void add(Project project){
		project.setId(IDGenerator.get());
		projectDao.insert(project);
	}

	@Transactional
	public void add(List<Project> projectList){
		for(Project project:projectList){
			this.add(project);
		}
	}

	@Transactional
	public void delete(Project project){
	    LOGGER.debug("delete project:{}", project);
		projectDao.delete(project.getId());
	}

	@Transactional
	public void delete(String id){
		if(StringUtils.isEmpty(id)){
			LOGGER.warn("try delete Project by empty id. Code need check");
			return;
		}
	    LOGGER.debug("delete project:{}", id);
		projectDao.delete(id);
	}

	@Transactional
	public void delete(List<Project> projects){
		for(Project project: projects){
			delete(project.getId());
		}
	}

	@Transactional
	public void merge(Project project){
		projectDao.save(project);
	}

	@Transactional
	public void merge(List<Project> projectList){
		for(Project project:projectList){
			this.merge(project);
		}
	}

	@Transactional(readOnly=true)
	public Project find(String id){
	    Project project = null;
		if(StringUtils.isNotEmpty(id)) {
            project = projectDao.findOne(id);
        }

		return project;
	}

	@Transactional(readOnly=true)
	public Project findOne(ProjectCondition projectCondition){

		List<Project> projectList = projectDao.findAll(projectCondition);

		Project result = null;
		if(CollectionUtils.isNotEmpty(projectList)){
			result = projectList.get(0);
		}
		return result;
	}

	@Transactional(readOnly=true)
	public List<Project> findAll(ProjectCondition projectCondition){

		List<Project> projectList = projectDao.findAll(projectCondition, getDefaultSort());

		return projectList;
	}

	@Transactional(readOnly=true)
	public Page<Project> find(Pageable pageable){
		Page<Project> projectList = projectDao.findAll(pageable, null, getDefaultSort());
		return projectList;
	}

	@Transactional(readOnly=true)
	public Page<Project> find(Pageable pageable, ProjectCondition projectCondition){
		Page<Project> projectList = projectDao.findAll(pageable, projectCondition, getDefaultSort());
		return projectList;
	}

	@Transactional(readOnly=true)
	public long count(ProjectCondition projectCondition){
		long count = projectDao.count(projectCondition);
		return count;
	}

	private Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.ASC, Project.PROPERTY_GROUP_CODE, Project.PROPERTY_NUMBER);
		return sort;
	}





}