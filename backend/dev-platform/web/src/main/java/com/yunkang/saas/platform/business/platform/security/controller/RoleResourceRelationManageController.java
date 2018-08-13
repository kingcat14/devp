package com.yunkang.saas.platform.business.platform.security.controller;


import com.yunkang.saas.platform.business.platform.security.domain.RoleResourceRelation;
import com.yunkang.saas.platform.business.platform.security.service.RoleResourceRelationService;
import com.yunkang.saas.platform.business.platform.security.vo.RoleResourceCheckTreeNode;
import com.yunkang.saas.platform.business.resource.domain.Resource;
import com.yunkang.saas.platform.business.resource.dto.ResourceCondition;
import com.yunkang.saas.platform.business.resource.service.ResourceService;
import com.yunkang.saas.platform.business.resource.vo.ResourceVO;
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
@RequestMapping(value = "/platform/security/roleResourceRelation")
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
			@RequestParam(required=false,value="roleId",defaultValue = "-1") Long roleId,
			@RequestParam(required=false,value="appId",defaultValue = "-1") Long appId
	){
		/*
		 * 1.查找当前APP的所有资源
		 * 2.查找当前角色的所有资源
		 * 3.匹配一下
		 */
		Resource parentResource = null;
		if(id == -1){
			parentResource = new Resource();
			parentResource.setParentCode(-1L);
			parentResource.setAppId(appId);
		}else{
			parentResource = resourceService.find(id);
		}
		//先查询父节点
		List<RoleResourceCheckTreeNode> resourceTreeNodes = getRoleResourceCheckTreeNodes(roleId, parentResource);


		return resourceTreeNodes;
	}

	private List<RoleResourceCheckTreeNode> getRoleResourceCheckTreeNodes(@RequestParam(required = false, value = "roleId", defaultValue = "-1") Long roleId, Resource parentResource) {
		//设置父节点ID和所属APPID
		ResourceCondition condition = new ResourceCondition();
		condition.setParentCode(parentResource.getCode());
		condition.setAppId(parentResource.getAppId());

		List<Resource> resourceList = resourceService.findAll(condition);
		LOGGER.info(resourceList.toString());

		//得到该角色的所有已授权资源
		List<RoleResourceRelation> roleResourceRelationList = roleResourceRelationService.findAllForRole(roleId);
		HashMap<Long, RoleResourceRelation> relationMap = new HashMap<>();
		for(RoleResourceRelation relation : roleResourceRelationList){
			relationMap.put(relation.getResourceId(), relation);
		}

		List<RoleResourceCheckTreeNode> resourceTreeNodes = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(resourceList)){
			for(Resource resource : resourceList){
				ResourceVO vo = new ResourceVO();
				BeanUtils.copyProperties(resource, vo);

				RoleResourceCheckTreeNode treeNode = new RoleResourceCheckTreeNode(vo, roleId, relationMap.containsKey(resource.getId()));
				if( relationMap.containsKey(resource.getId())){
					treeNode.setRelationId(relationMap.get(resource.getId()).getId());
				}
				resourceTreeNodes.add(treeNode);
			}
		}
		return resourceTreeNodes;
	}


}
