package net.aicoder.speedcloud.console.business.speedcloud.deploy.controller;

import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.util.IDGenerator;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefCondition;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCondition;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourceRefVO;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourceTreeNode;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesVO;
import net.aicoder.speedcloud.console.business.speedcloud.deploy.service.DevpSysDpyResourceRefRibbonService;
import net.aicoder.speedcloud.console.business.speedcloud.deploy.service.DevpSysDpyResourcesRibbonService;
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
@RequestMapping(value = "/speedcloud/deploy/devpsysdpyresources")
public class DevpSysDpyResourcesTreeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResourcesTreeController.class);

	@Autowired
	private SaaSUtil saaSUtil;
	@Autowired
	private DevpSysDpyResourcesRibbonService assetTypeService;

	@Autowired
    private DevpSysDpyResourceRefRibbonService resourceRefRibbonService;

	/**
	 * 得到整个树
	 * @return
	 */
	@PostMapping("/tree")
	public List<DevpSysDpyResourceTreeNode> tree(@RequestParam(name = "scheme") Long schemeId){

		HashMap<String, DevpSysDpyResourceTreeNode> nodeMap = new HashMap<>();

		List<DevpSysDpyResourceTreeNode> topNodeList = new ArrayList<>();

		//得到所有类别
		List<DevpSysDpyResourcesVO> assetTypeList = getResourceList(schemeId);

		//转换成节点
		for(DevpSysDpyResourcesVO assetType : assetTypeList){
            DevpSysDpyResourceTreeNode node = new DevpSysDpyResourceTreeNode();
			BeanUtils.copyProperties(assetType, node);
            node.setId(assetType.getId()+"");
            node.setLeaf(true);
            node.setObjId(assetType.getId()+"");
            nodeMap.put(node.getId(), node);

            topNodeList.add(node);
		}


		//开始处理节点关系
        List<DevpSysDpyResourceRefVO> relationList = getRelationList(schemeId);

		for(DevpSysDpyResourceRefVO vo : relationList){
            String resourceId = vo.getResource().toString();
            String refId = vo.getRefResource().toString();
            if(!nodeMap.containsKey(resourceId)||!nodeMap.containsKey(refId)){
                continue;
            }
            DevpSysDpyResourceTreeNode resNode = nodeMap.get(resourceId);
            DevpSysDpyResourceTreeNode refNode = nodeMap.get(refId);

            //处理主节点的关联
            DevpSysDpyResourceTreeNode child = new DevpSysDpyResourceTreeNode();
            BeanUtils.copyProperties(refNode, child);

            child.setLeaf(true);
            child.setId(IDGenerator.uuid());
            child.setObjId(refNode.getId());
            child.setRelationDirection(vo.getDirection());
            child.setRelationId(vo.getId().toString());
            child.setChildren(null);

            resNode.addChild(child);

            //处理引用节点的关联
            DevpSysDpyResourceTreeNode refChild = new DevpSysDpyResourceTreeNode();
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

	private List<DevpSysDpyResourcesVO> getResourceList(Long schemeId){

	    //得到所有类别
        DevpSysDpyResourcesCondition condition = new DevpSysDpyResourcesCondition();
        condition.setTid(saaSUtil.getAccount().getTenantId());
        condition.setScheme(schemeId);

        PageSearchRequest<DevpSysDpyResourcesCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        List<DevpSysDpyResourcesVO> resourceList = assetTypeService.list(pageSearchRequest).getContent();
        return resourceList;
    }
	private List<DevpSysDpyResourceRefVO> getRelationList(Long schemeId){
        //得到所有类别
        DevpSysDpyResourceRefCondition condition = new DevpSysDpyResourceRefCondition();
        condition.setTid(saaSUtil.getAccount().getTenantId());
        condition.setScheme(schemeId);

        PageSearchRequest<DevpSysDpyResourceRefCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        List<DevpSysDpyResourceRefVO> list = resourceRefRibbonService.list(pageSearchRequest).getContent();

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
