package com.yunkang.saas.platform.business.application.security.controller;


import com.yunkang.saas.platform.business.application.security.SaaSUtil;
import com.yunkang.saas.platform.business.platform.security.domain.RoleResourceRelation;
import com.yunkang.saas.platform.business.platform.security.service.RoleResourceRelationService;
import com.yunkang.saas.platform.business.platform.security.vo.RoleResourceCheckTreeNode;
import com.yunkang.saas.platform.business.resource.domain.Resource;
import com.yunkang.saas.platform.business.resource.dto.ResourceCondition;
import com.yunkang.saas.platform.business.resource.service.ResourceService;
import com.yunkang.saas.platform.business.resource.service.ResourceUtil;
import com.yunkang.saas.platform.business.resource.vo.ResourceVO;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RestController("applicationRoleResourceRelationManageController")
@RequestMapping(value = "/application/security/roleResourceRelation")
public class RoleResourceRelationManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleResourceRelationManageController.class);

	@Autowired
	private RoleResourceRelationService roleResourceRelationService;
	@Autowired
	private ResourceService resourceService;

	@Autowired
	private SaaSUtil saaSUtil;

	/**
	 * Method getChildNodes.
	 * @param id int
	 * @param id int
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(params = "method=getChildNodes", headers="Accept=application/xml, application/json")
	public @ResponseBody
	List<RoleResourceCheckTreeNode> getChildNodes(
			@RequestParam(required=false,value="id",defaultValue = "-1") Long id,
			@RequestParam(required=false,value="roleId",defaultValue = "-1") Long roleId
	){


		//不是根节点不处理

		Resource parentResource;
		if(id == -1){
			parentResource = new Resource();
			parentResource.setAppId(saaSUtil.getAppId());
		}else{
			parentResource = resourceService.find(id);
		}
		/*
		 * 1.得到系统的所有资源
		 * 2.得到当前角色已经选择的资源
		 * 3.计算哪些是已经选择的
		 */
		//1.
		ResourceCondition resourceCondition = new ResourceCondition();
		resourceCondition.setHidden(false);
		resourceCondition.setAppId(saaSUtil.getAppId());
		resourceCondition.setParentCode(parentResource.getParentCode());//如果是根节点，则会查询所有的子节点
		List<Resource> resourceList = resourceService.findAll(resourceCondition);
		//先排个序
		ResourceUtil.sortResourceList(resourceList);

		//2.
		List<RoleResourceRelation> roleResourceRelationList = roleResourceRelationService.findAllForRole(roleId);

		//3.
		HashMap<Long, RoleResourceCheckTreeNode> hashMap = new HashMap<>();
		List<RoleResourceCheckTreeNode> allResource = new ArrayList<>();
		for(Resource resource : resourceList){
			ResourceVO vo = new ResourceVO();
			BeanUtils.copyProperties(resource, vo);

			RoleResourceCheckTreeNode node = new RoleResourceCheckTreeNode(vo, roleId);
			BeanUtils.copyProperties(vo, node);

			allResource.add(node);
			hashMap.put(node.getResourceId(), node);
		}

		for(RoleResourceRelation relation : roleResourceRelationList){
			RoleResourceCheckTreeNode node = hashMap.get(relation.getResourceId());
			if(node != null){
				node.setRelationId(relation.getId());
				node.setChecked(true);
			}
		}

		return this.convert(allResource, id);

	}

	public static List<RoleResourceCheckTreeNode> convert(List<RoleResourceCheckTreeNode> resourceList, Long rootId){

		HashMap<Long, RoleResourceCheckTreeNode> hashMap = new HashMap<>();

		List<RoleResourceCheckTreeNode> result = new ArrayList<>();

		for(RoleResourceCheckTreeNode resource : resourceList){

			if(resource.getParentCode() == rootId){
				result.add(resource);
			}

			hashMap.put(resource.getCode(), resource);
		}

		for(RoleResourceCheckTreeNode node : resourceList){
			if(hashMap.containsKey(node.getParentCode())){
				hashMap.get(node.getParentCode()).addChild(node);
			}
		}

		return result;
	}




}
