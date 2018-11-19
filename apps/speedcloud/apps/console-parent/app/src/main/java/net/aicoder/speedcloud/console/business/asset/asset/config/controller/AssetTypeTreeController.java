package net.aicoder.speedcloud.console.business.asset.asset.config.controller;

import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypeCondition;
import net.aicoder.speedcloud.asset.business.asset.config.vo.AssetTypeVO;
import net.aicoder.speedcloud.console.business.asset.asset.config.service.AssetTypeRibbonService;
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
@RequestMapping(value = "/asset/asset/config/assettype")
public class AssetTypeTreeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetTypeTreeController.class);

	@Autowired
	private SaaSUtil saaSUtil;
	@Autowired
	private AssetTypeRibbonService assetTypeRibbonService;

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
		condition.setTid(saaSUtil.getAccount().getTid());
		PageSearchRequest<AssetTypeCondition> pageSearchRequest = new PageSearchRequest<>();
		pageSearchRequest.setSearchCondition(condition);
		pageSearchRequest.setPage(0);
		pageSearchRequest.setLimit(Integer.MAX_VALUE);

		List<AssetTypeVO> assetTypeList = assetTypeRibbonService.list(pageSearchRequest).getContent();


		//转换成节点
		for(AssetTypeVO assetType : assetTypeList){
			AssetTypeTreeNode node = new AssetTypeTreeNode();
			BeanUtils.copyProperties(assetType, node);
			nodeMap.put(node.getCode(), node);
			if(node.getParentCode().equals(node.getCode())){//顶层分类的parentCode == code
				topNodeList.add(node);
			}
		}

		//拼装节点
		for(AssetTypeVO assetType:assetTypeList){

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
