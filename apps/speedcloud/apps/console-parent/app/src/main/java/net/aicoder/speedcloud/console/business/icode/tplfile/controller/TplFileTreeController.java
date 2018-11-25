package net.aicoder.speedcloud.console.business.icode.tplfile.controller;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.console.business.icode.tplfile.controller.vo.TplFileTreeNode;
import net.aicoder.speedcloud.console.business.icode.tplfile.service.TplCodeRibbonService;
import net.aicoder.speedcloud.console.business.icode.tplfile.service.TplSetRibbonService;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeCondition;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplCodeVO;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplSetVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
@Slf4j
public class TplFileTreeController {

	@Autowired
	private TplSetRibbonService tplSetService;

	@Autowired
	private TplCodeRibbonService tplCodeService;



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

		TplCodeCondition condition = new TplCodeCondition();

		PageSearchRequest request = new PageSearchRequest();
		request.setPage(0);
		request.setLimit(Integer.MAX_VALUE);
		request.setSearchCondition(condition);

		List<TplSetVO> list = tplSetService.list(request).getContent();

		List<TplFileTreeNode> result = new ArrayList<>();
		for(TplSetVO tplSet : list){
			TplFileTreeNode node = new TplFileTreeNode(tplSet);
			result.add(node);
		}

		return result;
	}

	private List<TplFileTreeNode> findTplCode(String tplSetId, String type){
		TplCodeCondition searchDto = new TplCodeCondition();
		searchDto.setType(type);
		searchDto.setTplSet(tplSetId);

		PageSearchRequest request = new PageSearchRequest();
		request.setPage(0);
		request.setLimit(Integer.MAX_VALUE);
		request.setSearchCondition(searchDto);

		List<TplCodeVO> list = tplCodeService.list(request).getContent();

		List<TplFileTreeNode> result = new ArrayList<>();
		for(TplCodeVO tplCode : list){
			TplFileTreeNode node = new TplFileTreeNode(tplCode);
			result.add(node);
		}

		return result;
	}

	/**
	 *
	 * @param tplSetId
	 * @return
	 */
	private List<TplFileTreeNode> findTplCode(String tplSetId){
		/*
		 * 根据文件的文件路径，拼成一颗树形结构
		 */
		TplCodeCondition searchDto = new TplCodeCondition();
		searchDto.setTplSet(tplSetId);

		PageSearchRequest request = new PageSearchRequest();
		request.setPage(0);
		request.setLimit(Integer.MAX_VALUE);
		request.setSearchCondition(searchDto);

		List<TplCodeVO> list = tplCodeService.list(request).getContent();


		Map<String, TplFileTreeNode> typeNodeMap = new HashMap<>();
		List<TplFileTreeNode> result = new ArrayList<>();

		for(TplCodeVO tplCode : list){

			TplFileTreeNode node = makeTreeByFilePath(typeNodeMap, tplCode);

			if(node != null){
				result.add(node);
			}
		}

		for(TplFileTreeNode node : result){
			unionEmptyFolder(node);
		}
		return result;
	}

	private TplFileTreeNode makeTreeByFilePath(Map<String, TplFileTreeNode> filePathMap, TplCodeVO tplCode){

//		String filePath = tplCode.getFilePath();
		String filePath = tplCode.getType();
		String[] folders = filePath.split("/");

		TplFileTreeNode resultNode = null;

		//先处理路径
		String parentPath = "";
		String currentPath = "";
		for(String folder : folders){

			if(StringUtils.isNotEmpty(currentPath)){
				currentPath += "/" + folder;
			}else{
				currentPath = folder;
			}

			if(!filePathMap.containsKey(currentPath)){
				//生成当前路径的节点
				TplFileTreeNode typeNode = new TplFileTreeNode(tplCode.getTplSet(), currentPath);
				typeNode.setName(folder);
				filePathMap.put(currentPath, typeNode);

				//找到上级路径的节点
				TplFileTreeNode parentNode = filePathMap.get(parentPath);

				if(parentNode != null){
					//当前路径加入上级路径的子节点
					parentNode.addChild(typeNode);
				}else{
					//要么自己就是定级节点
					resultNode = typeNode;
				}
			}

			parentPath = currentPath;

			continue;
		}


		TplFileTreeNode codeTplNode = new TplFileTreeNode(tplCode);

		if(!filePathMap.containsKey(tplCode.getType())){
			resultNode = codeTplNode;
		}else {
			TplFileTreeNode folder = filePathMap.get(parentPath);
			folder.addChild(codeTplNode);
		}

		return resultNode;
	}

	private void unionEmptyFolder(TplFileTreeNode folder){
		if(folder.isLeaf() || CollectionUtils.isEmpty(folder.getChildren())){
			return;
		}


		List<TplFileTreeNode> children = folder.getChildren();

		if(CollectionUtils.size(children) == 1 && !children.get(0).isLeaf()){
			TplFileTreeNode child = children.get(0);
			folder.setName(folder.getName() + "/" + child.getName());
			folder.setId(child.getId());
			folder.setObjectId(child.getObjectId());
			folder.getChildren().remove(child);
			folder.getChildren().addAll(child.getChildren());
			unionEmptyFolder(folder);
		}

		for(TplFileTreeNode child : children){
			unionEmptyFolder(child);
		}


	}

}