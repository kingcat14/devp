package com.yunkang.saas.security.local.business.platform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.security.service.business.platform.domain.Resource;
import com.yunkang.saas.security.service.business.platform.service.ResourceService;
import com.yunkang.saas.security.model.dto.ResourceCondition;
import com.yunkang.saas.security.model.vo.ResourceTreeNode;
import com.yunkang.saas.security.model.vo.ResourceVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 管理资源
 * @author icode
 */
@RestController
@RequestMapping(value = "/security/resource")
public class ResourceManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceManageController.class);


	@Autowired
	private ResourceService resourceService;


	/**
	 * Method getChildNodes.
	 * @param node String
	 * @param id int
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(params = "method=getChildNodes", headers="Accept=application/xml, application/json")
	public @ResponseBody
	List<ResourceTreeNode> getChildNodes(
			@RequestParam(required=false,value="node") String node,
			@RequestParam(required=false,value="id",defaultValue = "-1") Long id
	){

		ResourceCondition condition = new ResourceCondition();
		condition.setParentId(id);


		List<Resource> resourceList = resourceService.findAll(condition);
		LOGGER.info(resourceList.toString());

		List<ResourceTreeNode> resourceTreeNodes = new ArrayList<>();
		if(resourceList!=null){
			for(Resource resource:resourceList){
				ResourceVO vo = new ResourceVO();
				BeanUtils.copyProperties(resource, vo);
				resourceTreeNodes.add(new ResourceTreeNode(vo));
			}
		}

		return resourceTreeNodes;
	}

	@RequestMapping("/tree")
	public PageContent<ResourceTreeNode> tree(
			@RequestParam(required=false, value="condition") String condition ){

		ObjectMapper objectMapper = new ObjectMapper();

		ResourceCondition searchCondition = null;
		if(StringUtils.isNotEmpty(condition)){
			try {
				searchCondition = objectMapper.readValue(condition, ResourceCondition.class);
			} catch (IOException e) {
				LOGGER.error("convert searchCondition error,condition:("+condition+")", e);
			}
		}

		List<Resource> resourcePage = resourceService.findAll(searchCondition);
		PageContent<ResourceTreeNode> pageContent = new PageContent<>(convert(resourcePage), resourcePage.size());

		return pageContent;

	}


	/**
	 * 转换成树
	 * @param resourceList
	 * @return
	 */
	public List<ResourceTreeNode> convert(List<Resource> resourceList){

		resourceService.sortResourceList(resourceList);

		HashMap<Long, ResourceTreeNode> hashMap = new HashMap<>();
		List<ResourceTreeNode> allResource = new ArrayList<>();
		List<ResourceTreeNode> result = new ArrayList<>();


		for(Resource resource : resourceList){
			ResourceVO vo = new ResourceVO();
			BeanUtils.copyProperties(resource, vo);

			ResourceTreeNode resourceTreeNode = new ResourceTreeNode(vo);

			if(vo.getParentId()== -1){
				result.add(resourceTreeNode);
			}
			allResource.add(resourceTreeNode);
			hashMap.put(resourceTreeNode.getId(), resourceTreeNode);
		}

		for(ResourceTreeNode node : allResource){
			if(hashMap.containsKey(node.getParentId())){
				hashMap.get(node.getParentId()).addChild(node);
			}
		}

		return result;
	}





}
