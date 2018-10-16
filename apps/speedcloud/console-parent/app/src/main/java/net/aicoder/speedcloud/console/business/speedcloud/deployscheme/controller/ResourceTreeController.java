package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.controller;

import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.util.IDGenerator;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationCondition;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceRelationVO;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceTreeNode;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceVO;
import net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service.ResourceRelationRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.deployscheme.service.ResourceRibbonService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 管理资产类型
 * @author icode
 */
@RestController
@RequestMapping(value = "/speedcloud/deployscheme/resource")
public class ResourceTreeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceTreeController.class);

	@Autowired
	private SaaSUtil saaSUtil;
	@Autowired
	private ResourceRibbonService resourceRibbonService;

	@Autowired
    private ResourceRelationRibbonService resourceRefRibbonService;

	/**
	 * 得到整个树
	 * @return
	 */
	@PostMapping("/tree")
	public List<ResourceTreeNode> tree(@RequestParam(name = "scheme") Long schemeId){

		HashMap<String, ResourceTreeNode> nodeMap = new HashMap<>();

		List<ResourceTreeNode> topNodeList = new ArrayList<>();

		//得到所有类别
		List<ResourceVO> assetTypeList = getResourceList(schemeId);

		//转换成节点
		for(ResourceVO assetType : assetTypeList){
            ResourceTreeNode node = new ResourceTreeNode();
			BeanUtils.copyProperties(assetType, node);
            node.setId(assetType.getId()+"");
            node.setLeaf(true);
            node.setObjId(assetType.getId()+"");
            nodeMap.put(node.getId(), node);

            topNodeList.add(node);
		}


		//开始处理节点关系
        List<ResourceRelationVO> relationList = getRelationList(schemeId);

		for(ResourceRelationVO vo : relationList){
            String resourceId = vo.getResource().toString();
            String refId = vo.getRefResource().toString();
            if(!nodeMap.containsKey(resourceId)||!nodeMap.containsKey(refId)){
                continue;
            }
            ResourceTreeNode resNode = nodeMap.get(resourceId);
            ResourceTreeNode refNode = nodeMap.get(refId);

            //处理主节点的关联
            ResourceTreeNode child = new ResourceTreeNode();
            BeanUtils.copyProperties(refNode, child);

            child.setLeaf(true);
            child.setId(IDGenerator.uuid());
            child.setObjId(refNode.getId());
            child.setRelationDirection(vo.getDirection());
            child.setRelationId(vo.getId().toString());
            child.setChildren(null);

            resNode.addChild(child);

            //处理引用节点的关联
            ResourceTreeNode refChild = new ResourceTreeNode();
            BeanUtils.copyProperties(resNode, refChild);
            refChild.setChildren(null);

            refChild.setLeaf(true);
            refChild.setId(refId+"-"+resourceId);
            refChild.setObjId(resNode.getId());
            refChild.setRelationDirection(reserveDirection(vo.getDirection()));
            refChild.setRelationId(vo.getId().toString());

            refNode.addChild(refChild);

        }


		return topNodeList;
	}

	private List<ResourceVO> getResourceList(Long schemeId){

	    //得到所有类别
        ResourceCondition condition = new ResourceCondition();
        condition.setTid(saaSUtil.getAccount().getTenantId());
        condition.setScheme(schemeId);

        PageSearchRequest<ResourceCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        List<ResourceVO> resourceList = resourceRibbonService.list(pageSearchRequest).getContent();
        return resourceList;
    }
	private List<ResourceRelationVO> getRelationList(Long schemeId){
        //得到所有类别
        ResourceRelationCondition condition = new ResourceRelationCondition();
        condition.setTid(saaSUtil.getAccount().getTenantId());
        condition.setScheme(schemeId);

        PageSearchRequest<ResourceRelationCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        List<ResourceRelationVO> list = resourceRefRibbonService.list(pageSearchRequest).getContent();

        return list;
    }

    private String reserveDirection(String direction){

	    String result = direction;

	    if(StringUtils.equalsIgnoreCase("FORWARD", direction)){
            result = "BACKWARD";
        }
        if(StringUtils.equalsIgnoreCase("BACKWARD", direction)){
            result = "FORWARD";
        }
        return result;
    }

}
