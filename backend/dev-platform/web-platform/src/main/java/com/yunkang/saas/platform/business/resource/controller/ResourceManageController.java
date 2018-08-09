package com.yunkang.saas.platform.business.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.platform.business.platform.security.vo.ResourceTreeNode;
import com.yunkang.saas.platform.business.resource.domain.Resource;
import com.yunkang.saas.platform.business.resource.dto.ResourceCondition;
import com.yunkang.saas.platform.business.resource.service.ResourceService;
import com.yunkang.saas.platform.business.resource.service.ResourceUtil;
import com.yunkang.saas.platform.business.resource.vo.ResourceVO;
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
@RequestMapping(value = "/platform/security/resource")
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
		PageContent<ResourceTreeNode> pageContent = new PageContent<>(ResourceUtil.convert(resourcePage), resourcePage.size());

		return pageContent;

	}








}
