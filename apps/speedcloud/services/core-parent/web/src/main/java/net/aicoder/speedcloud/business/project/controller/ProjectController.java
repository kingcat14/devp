package net.aicoder.speedcloud.business.project.controller;

import com.yunkang.saas.bootstrap.jms.sender.SaaSMessageSender;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.project.domain.Project;
import net.aicoder.speedcloud.business.project.domain.ProjectSet;
import net.aicoder.speedcloud.business.project.dto.ProjectAddDto;
import net.aicoder.speedcloud.business.project.dto.ProjectCondition;
import net.aicoder.speedcloud.business.project.dto.ProjectEditDto;
import net.aicoder.speedcloud.business.project.event.ProjectEventTopic;
import net.aicoder.speedcloud.business.project.service.ProjectService;
import net.aicoder.speedcloud.business.project.service.ProjectSetService;
import net.aicoder.speedcloud.business.project.valid.ProjectValidator;
import net.aicoder.speedcloud.business.project.vo.ProjectSetVO;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
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
import java.util.Date;
import java.util.List;

/**
 * 管理项目
 * @author icode
 */
@Api(description = "项目", tags = "Project")
@RestController
@RequestMapping(value = "/speedcloud/project/project")
public class ProjectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectSetService projectSetService;

	@Autowired
	private ProjectValidator projectValidator;

	@Autowired
	private SaaSMessageSender saaSMessageSender;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(projectValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增项目
	 * @param projectAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增项目", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ProjectVO add(@RequestBody @Valid ProjectAddDto projectAddDto){
		Project project = new Project();
		BeanUtils.copyProperties(projectAddDto, project);

		projectService.add(project);
		ProjectVO vo = initViewProperty(project);

		saaSMessageSender.sendTopic(ProjectEventTopic.CREATE, project.getTid(), vo);

		return vo;
	}

	/**
	 * 删除项目,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除项目", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete project :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			projectService.delete(id);
		}

	}

	/**
	 * 更新项目
	 * @param projectEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产项目(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	ProjectVO update(@RequestBody @Valid ProjectEditDto projectEditDto, @PathVariable String id){

		Project project = projectService.find(id);
		BeanUtils.copyProperties(projectEditDto, project);
		project.setId(id);
		projectService.merge(project);

		ProjectVO vo = initViewProperty(project);

		saaSMessageSender.sendTopic(ProjectEventTopic.UPDATE, project.getTid(), vo);

		return  vo;
	}

	/**
	 * 根据ID查询项目
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询项目", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  ProjectVO get(@PathVariable String id) {

		Project project = projectService.find(id);

		ProjectVO vo = initViewProperty(project);
		return vo;
	}

	/**
	 * 查询项目列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询项目列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ProjectVO> list(@RequestBody PageSearchRequest<ProjectCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<Project> page = projectService.find(pageSearchRequest.getSearchCondition(), pageRequest);

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


	    //初始化其他对象
	    initProjectSetPropertyGroup(vo, project);
        return vo;


	}


	private void initProjectSetPropertyGroup(ProjectVO projectVO, Project project){
	
		ProjectSet projectSet = projectSetService.find(project.getProjectSet());
		if(projectSet == null){
			return;
		}
		ProjectSetVO projectSetVO = new ProjectSetVO();
		BeanUtils.copyProperties(projectSet, projectSetVO);

		projectVO.setProjectSetVO(projectSetVO);

	}


}
