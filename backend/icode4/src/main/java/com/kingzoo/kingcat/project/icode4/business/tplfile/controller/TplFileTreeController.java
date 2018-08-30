package com.kingzoo.kingcat.project.icode4.business.tplfile.controller;

import com.kingzoo.kingcat.project.icode4.business.tplfile.controller.vo.TplFileTreeNode;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplCode;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplSet;
import com.kingzoo.kingcat.project.icode4.business.tplfile.service.TplCodeService;
import com.kingzoo.kingcat.project.icode4.business.tplfile.service.TplSetService;
import com.kingzoo.kingcat.project.icode4.business.tplfile.vo.TplCodeCondition;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理业务对象定义
 */
@RestController
@RequestMapping(value = "/tplfile/tplFileTree")
public class TplFileTreeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TplFileTreeController.class);

	@Autowired
	private TplSetService tplSetService;

	@Autowired
	private TplCodeService tplCodeService;



	/**
	 * Method getChildNodes.
	 * @param type String
	 * @param id int
	 * @return ResponseEntity<String>
	 */
	@RequestMapping("/{id}")
	public
	List<TplFileTreeNode> getChildNodes(
			@RequestParam(required=false,value="type") String type,
			@RequestParam(required=false,value="id", defaultValue = "-1") String id
	        ,@PathVariable(name="id") String nodeId){


		List<TplFileTreeNode> result = new ArrayList<>();


		if(StringUtils.isEmpty(type)){
			result.addAll(this.findTplSetNode());
		}

		if(StringUtils.equals(TplFileTreeNode.TYPE_TPL_SET, type)){
//			result.add(new TplFileTreeNode(nodeId, "PROJECT"));
//			result.add(new TplFileTreeNode(nodeId, "CODE"));
			result.addAll(this.findTplCode(nodeId));
		}

		if(StringUtils.equals(TplFileTreeNode.TYPE_TPL_TYPE, type)){
			String[] aa = nodeId.split("_");
			result.addAll(this.findTplCode(aa[0], aa[1]));
		}



		return result;
	}

	/**
	 * 查找所有系统
	 * @return
	 */
	private List<TplFileTreeNode> findTplSetNode(){
		List<TplSet> list = tplSetService.findAll(null);

		List<TplFileTreeNode> result = new ArrayList<>();
		for(TplSet tplSet : list){
			TplFileTreeNode node = new TplFileTreeNode(tplSet);
			result.add(node);
		}

		return result;
	}




	private List<TplFileTreeNode> findTplCode(String tplSetId, String type){
		TplCodeCondition searchDto = new TplCodeCondition();
		searchDto.setType(type);
		searchDto.setTplSetId(tplSetId);

		List<TplCode> list = tplCodeService.findAll(searchDto);

		List<TplFileTreeNode> result = new ArrayList<>();
		for(TplCode tplCode : list){
			TplFileTreeNode node = new TplFileTreeNode(tplCode);
			result.add(node);
		}

		return result;
	}

	private List<TplFileTreeNode> findTplCode(String tplSetId){
		TplCodeCondition searchDto = new TplCodeCondition();
		searchDto.setTplSetId(tplSetId);
		List<TplCode> list = tplCodeService.findAll(searchDto);


		Map<String, TplFileTreeNode> typeNodeMap = new HashMap<>();
		List<TplFileTreeNode> result = new ArrayList<>();

		for(TplCode tplCode : list){

			TplFileTreeNode typeNode = typeNodeMap.get(tplCode.getType());
			if(typeNode == null){
				typeNode = new TplFileTreeNode(tplSetId, tplCode.getType());
				typeNodeMap.put(tplCode.getType(), typeNode);
				result.add(typeNodeMap.get(tplCode.getType()));
			}

			TplFileTreeNode node = new TplFileTreeNode(tplCode);
			typeNode.addChild(node);

		}

		return result;
	}


}