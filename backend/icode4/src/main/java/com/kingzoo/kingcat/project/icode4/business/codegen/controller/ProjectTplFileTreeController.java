package com.kingzoo.kingcat.project.icode4.business.codegen.controller;

import com.kingzoo.kingcat.project.icode4.business.codegen.controller.vo.ProjectTplFileTreeNode;
import com.kingzoo.kingcat.project.icode4.business.codegen.domain.Project;
import com.kingzoo.kingcat.project.icode4.business.codegen.service.ProjectService;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplCode;
import com.kingzoo.kingcat.project.icode4.business.tplfile.service.TplCodeService;
import com.kingzoo.kingcat.project.icode4.business.tplfile.service.TplSetService;
import com.kingzoo.kingcat.project.icode4.business.tplfile.vo.TplCodeCondition;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发工程使用的模板树
 */
@RestController
@RequestMapping(value = "/codegen/project/tplFileTree")
public class ProjectTplFileTreeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectTplFileTreeController.class);

	@Autowired
	private TplSetService tplSetService;

	@Autowired
	private TplCodeService tplCodeService;

	@Autowired
	private ProjectService projectService;


	/**
	 * Method getChildNodes.
	 * @param type String
	 * @param id int
	 * @return ResponseEntity<String>
	 */
	@RequestMapping("/{id}")
	public
	List<ProjectTplFileTreeNode> getChildNodes(
			@RequestParam(required=false,value="type") String type,
			@RequestParam(required=false,value="id", defaultValue = "-1") String id
	        ,@PathVariable(name="id") String nodeId){


		List<ProjectTplFileTreeNode> result = new ArrayList<>();

		if(StringUtils.isEmpty(type)){
			result.addAll(this.findProjectNode());
		}

		//如果是
		if(StringUtils.equals(ProjectTplFileTreeNode.TYPE_PROJECT, type)){
			result.addAll(this.findCodeTplTypes(nodeId));
//			result.add(new ProjectTplFileTreeNode(nodeId, "PROJECT"));
//			result.add(new ProjectTplFileTreeNode(nodeId, "CODE"));
		}

		if(StringUtils.equals(ProjectTplFileTreeNode.TYPE_TPL_TYPE, type)){
			String[] aa = nodeId.split("_");


			result.addAll(this.findTplCodeNode(aa[0], aa[1]));
		}


		return result;
	}




	/**
	 * 查找所有开发工程
	 * @return
	 */
	private List<ProjectTplFileTreeNode> findProjectNode(){

		List<Project> list = projectService.findAll(null);

		List<ProjectTplFileTreeNode> result = new ArrayList<>();
		for(Project project : list){
			ProjectTplFileTreeNode node = new ProjectTplFileTreeNode(project);
			result.add(node);
		}

		return result;
	}

	public List<ProjectTplFileTreeNode> findCodeTplTypes(String projectId){
		String tplSetId = projectService.find(projectId).getTplSetId();

		List<String> types = tplCodeService.findCodeTplTypes(tplSetId);
		List<ProjectTplFileTreeNode> result = new ArrayList<>();
		for(String type : types){
			result.add(new ProjectTplFileTreeNode(projectId, type));
		}
		return result;

	}


	private List<ProjectTplFileTreeNode> findTplCodeNode(String projectId, String type){

		String tplSetId = projectService.find(projectId).getTplSetId();

		TplCodeCondition searchDto = new TplCodeCondition();
		searchDto.setType(type);
		searchDto.setTplSetId(tplSetId);

		List<TplCode> list = tplCodeService.findAll(searchDto);

		List<ProjectTplFileTreeNode> result = new ArrayList<>();
		for(TplCode tplCode : list){
			ProjectTplFileTreeNode node = new ProjectTplFileTreeNode(projectId, tplCode);
			result.add(node);
		}

		return result;
	}


}