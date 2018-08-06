package com.yunkang.saas.security.local.business.controller;


import com.yunkang.saas.security.local.business.domain.Resource;
import com.yunkang.saas.security.local.business.domain.RoleResourceRelation;
import com.yunkang.saas.security.local.business.service.ResourceService;
import com.yunkang.saas.security.local.business.service.RoleResourceRelationService;
import com.yunkang.saas.security.model.dto.ResourceCondition;
import com.yunkang.saas.security.model.vo.ResourceVO;
import com.yunkang.saas.security.model.vo.RoleResourceCheckTreeNode;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 管理角色资源关系
 * @author icode
 */
@RestController
@RequestMapping(value = "/security/roleResourceRelation")
public class RoleResourceRelationManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleResourceRelationManageController.class);


	@Autowired
	private RoleResourceRelationService roleResourceRelationService;
	@Autowired
	private ResourceService resourceService;

	/**
	 * Method getChildNodes.
	 * @param node String
	 * @param id int
	 * @param id int
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(params = "method=getChildNodes", headers="Accept=application/xml, application/json")
	public @ResponseBody
	List<RoleResourceCheckTreeNode> getChildNodes(
			@RequestParam(required=false,value="node") String node,
			@RequestParam(required=false,value="id",defaultValue = "-1") Long id,
			@RequestParam(required=false,value="roleId",defaultValue = "-1") Long roleId
	){


		ResourceCondition condition = new ResourceCondition();
		condition.setParentId(id);



		List<Resource> resourceList = resourceService.findAll(condition);
		LOGGER.info(resourceList.toString());

		//得到该角色的所有已授权资源
		List<RoleResourceRelation> roleResourceRelationList = roleResourceRelationService.findAllForRole(roleId);
		HashMap<Long, String> relationMap = new HashMap<>();
		for(RoleResourceRelation relation : roleResourceRelationList){
			relationMap.put(relation.getResourceId(),"");
		}

		List<RoleResourceCheckTreeNode> resourceTreeNodes = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(resourceList)){
			for(Resource resource : resourceList){
				ResourceVO vo = new ResourceVO();
				BeanUtils.copyProperties(resource, vo);
				resourceTreeNodes.add(new RoleResourceCheckTreeNode(vo, roleId, relationMap.containsKey(resource.getId())));
			}
		}



		return resourceTreeNodes;
	}




}
