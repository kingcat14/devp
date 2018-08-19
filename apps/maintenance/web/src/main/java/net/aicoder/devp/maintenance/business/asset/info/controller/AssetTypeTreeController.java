package net.aicoder.devp.maintenance.business.asset.info.controller;

import com.yunkang.saas.platform.business.application.security.SaaSUtil;
import net.aicoder.devp.maintenance.business.asset.info.domain.AssetType;
import net.aicoder.devp.maintenance.business.asset.info.dto.AssetTypeCondition;
import net.aicoder.devp.maintenance.business.asset.info.service.AssetTypeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 管理资产类型
 * @author icode
 */
@RestController
@RequestMapping(value = "/asset/info/assetType")
public class AssetTypeTreeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetTypeTreeController.class);

	@Autowired
	private SaaSUtil saaSUtil;
	@Autowired
	private AssetTypeService assetTypeService;

	/**
	 * 得到整个树
	 * @return
	 */
	@RequestMapping("/tree")
	public List<AssetTypeTreeNode> tree(String parentCode){

		HashMap<String, AssetTypeTreeNode> nodeMap = new HashMap<>();

		List<AssetTypeTreeNode> topNodeList = new ArrayList<>();
		for(AssetTypeTreeNode node : topNodeList){
			nodeMap.put(node.getCode(), node);
		}

		//得到所有类别
		AssetTypeCondition condition = new AssetTypeCondition();
		condition.setTid(saaSUtil.getAccount().getTenantId());
		List<AssetType> assetTypeList = assetTypeService.findAll(condition);


		//转换成节点
		for(AssetType assetType:assetTypeList){
			AssetTypeTreeNode node = new AssetTypeTreeNode();
			BeanUtils.copyProperties(assetType, node);
			nodeMap.put(node.getCode(), node);
			if(node.getParentCode().equals(node.getCode())){//顶层分类的parentCode == code
				topNodeList.add(node);
			}
		}

		//拼装节点
		for(AssetType assetType:assetTypeList){

			//定级节点就直接跳过了
			if(assetType.getCode().equals(assetType.getParentCode())){
				continue;
			}
			AssetTypeTreeNode parentNode = nodeMap.get(assetType.getParentCode());
			if(parentNode != null){
				parentNode.addChild(nodeMap.get(assetType.getCode()));
			}
		}


		if(StringUtils.isNotEmpty(parentCode)){
			ArrayList<AssetTypeTreeNode> result = new ArrayList<>();
			result.add(nodeMap.get(parentCode));
			return result;
		}
		return topNodeList;
	}

}
