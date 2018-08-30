package com.kingzoo.kingcat.project.icode4.business.codegen.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.codegen.controller.vo.ProjectAddRequest;
import com.kingzoo.kingcat.project.icode4.business.codegen.domain.Project;
import com.kingzoo.kingcat.project.icode4.business.codegen.service.ProjectService;
import com.kingzoo.kingcat.project.icode4.business.codegen.valid.ProjectValidator;
import com.kingzoo.kingcat.project.icode4.business.codegen.vo.ProjectCondition;
import com.kingzoo.kingcat.project.icode4.business.codegen.vo.ProjectVO;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplSet;
import com.kingzoo.kingcat.project.icode4.business.tplfile.service.TplSetService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理工程
 * @author icode
 */
@RestController
@RequestMapping(value = "/codegen/project")
public class ProjectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);


	@Autowired
	private ProjectService projectService;

	@Autowired
	private TplSetService tplSetService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new ProjectValidator());
	}

	/**
	 * 新增工程
	 * @param projectAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ProjectVO add(@RequestBody @Valid ProjectAddRequest projectAddRequest){
		Project project = new Project();
		BeanUtils.copyProperties(projectAddRequest, project);

		projectService.add(project);

		return  initViewProperty(project);
	}

	/**
	 * 删除工程,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete project :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			projectService.delete(id);
		}

	}

	/**
	 * 更新工程
	 * @param project
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public ProjectVO update(@RequestBody @Valid Project project, @PathVariable String id){
		project.setId(id);
		projectService.merge(project);

		ProjectVO vo = initViewProperty(project);
		return  vo;
	}

	/**
	 * 根据ID查询工程
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public ProjectVO get(@PathVariable String id) {

		Project project = projectService.find(id);

		ProjectVO vo = initViewProperty(project);
		return vo;
	}

	/**
	 * 查询工程列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<ProjectVO> list(@RequestBody PageSearchRequest<ProjectCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<Project> page = projectService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<ProjectVO> voList = new ArrayList<>();
		for(Project project : page.getContent()){
			voList.add(initViewProperty(project));
		}

		PageContent<ProjectVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private ProjectVO initViewProperty(Project project){
	    ProjectVO vo = new ProjectVO();

        BeanUtils.copyProperties(project, vo);

        if(StringUtils.isNotEmpty(project.getTplSetId())){
			TplSet tplSet = tplSetService.find(project.getTplSetId());
			if(tplSet != null){
				vo.setTplSetName(tplSet.getName());
			}
		}

	    //初始化其他对象
        return vo;
	}




}
