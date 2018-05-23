package net.aicoder.devp.security.business.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import net.aicoder.devp.security.business.security.controller.vo.ResourceTreeNode;
import net.aicoder.devp.security.business.security.domain.Resource;
import net.aicoder.devp.security.business.security.dto.ResourceCondition;
import net.aicoder.devp.security.business.security.service.ResourceService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
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
				resourceTreeNodes.add(new ResourceTreeNode(resource));
			}
		}

		return resourceTreeNodes;
	}

	@RequestMapping("/tree")
	public PageContent<Resource> tree(
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

		List<Resource> resourcePage = resourceService.findResourceTree(searchCondition);
		PageContent<Resource> pageContent = new PageContent<>(resourcePage, resourcePage.size());

		return pageContent;

	}




}
