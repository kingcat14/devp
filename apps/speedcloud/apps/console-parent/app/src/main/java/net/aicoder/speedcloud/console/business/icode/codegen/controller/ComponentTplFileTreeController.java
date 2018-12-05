package net.aicoder.speedcloud.console.business.icode.codegen.controller;


import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.console.business.icode.codegen.controller.vo.ComponentTplFileTreeNode;
import net.aicoder.speedcloud.console.business.icode.domainboard.controller.vo.DomainModelTreeNode;
import net.aicoder.speedcloud.console.business.icode.project.service.ComponentRibbonService;
import net.aicoder.speedcloud.console.business.icode.project.service.ProductRibbonService;
import net.aicoder.speedcloud.console.business.icode.tplfile.service.TplCodeRibbonService;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ProductCondition;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentVO;
import net.aicoder.speedcloud.icode.business.project.vo.ProductVO;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeCondition;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplCodeVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 开发工程使用的模板树
 */
@RestController
@RequestMapping(value = "/codegen/tplFileTree")
@Slf4j
public class ComponentTplFileTreeController {

	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private TplCodeRibbonService tplCodeService;

	@Autowired
	private ProductRibbonService productRibbonService;

	@Autowired
	private ComponentRibbonService componentRibbonService;


	/**
	 * Method getChildNodes.
	 * @param type String
	 * @param objectId int
	 * @return ResponseEntity<String>
	 */
	@RequestMapping("/{id}")
	public
	List<ComponentTplFileTreeNode> getChildNodes(
			@RequestParam(required=false,value="type") String type,
			@RequestParam(required=false,value="objectId", defaultValue = "-1") String objectId
	        ,@PathVariable(name="id") String nodeId){

		List<ComponentTplFileTreeNode> result = new ArrayList<>();

		if(StringUtils.isEmpty(type)){
			result.addAll(this.findProductNode());
		}
		if(StringUtils.equals(DomainModelTreeNode.TYPE_PRODUCT, type)){
			result.addAll(this.findComponentNode(objectId));
		}

		if(StringUtils.equals(DomainModelTreeNode.TYPE_COMPONENT, type)){
			result.addAll(this.findProjectTplCodeList(objectId));
		}
		return result;
	}


	/**
	 * 查找所有产品
	 * @return
	 */
	private List<ComponentTplFileTreeNode> findProductNode(){

		Long tenantId = saaSUtil.getAccount().getTid();

		ProductCondition condition = new ProductCondition();
		condition.setTid(tenantId);

		PageSearchRequest request = new PageSearchRequest();
		request.setPage(0);
		request.setLimit(Integer.MAX_VALUE);
		request.setSearchCondition(condition);

		PageContent<ProductVO> pageContent =  productRibbonService.list(request);
		List<ProductVO> list = pageContent.getContent();

		List<ComponentTplFileTreeNode> result = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(list)){
			list.stream().forEach((productVO)->{
				ComponentTplFileTreeNode node = new ComponentTplFileTreeNode(productVO);
				result.add(node);
			});
		}

		return result;
	}
	/**
	 * 查找所有系统
	 * @return
	 */
	private List<ComponentTplFileTreeNode> findComponentNode(String productId){
		Long tenantId = saaSUtil.getAccount().getTid();

		ComponentCondition condition = new ComponentCondition();
		condition.setProduct(productId);
		condition.setTid(tenantId);
		PageSearchRequest request = new PageSearchRequest();
		request.setPage(0);
		request.setLimit(Integer.MAX_VALUE);
		request.setSearchCondition(condition);

		List<ComponentVO> list = componentRibbonService.list(request).getContent();

		List<ComponentTplFileTreeNode> result = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(list)){
			list.stream().forEach((productVO)->{
				ComponentTplFileTreeNode node = new ComponentTplFileTreeNode(productVO);
				result.add(node);
			});
		}

		return result;
	}

	public List<ComponentTplFileTreeNode> findProjectTplCodeList(String componentId) {
		String tplSetId = componentRibbonService.find(componentId).getTplSet();

		List<ComponentTplFileTreeNode> result = new ArrayList<>();

		if(StringUtils.isEmpty(tplSetId)){
			return result;
		}
		TplCodeCondition searchDto = new TplCodeCondition();
		searchDto.setTplSet(tplSetId);

		List<TplCodeVO> list = tplCodeService.list(searchDto);

		Map<String, ComponentTplFileTreeNode> typeNodeMap = new HashMap<>();


		Collections.sort(list, (TplCodeVO o1, TplCodeVO o2)->
			StringUtils.compare(o1.getType(),o2.getType())
		);

		for(TplCodeVO tplCode : list){

			ComponentTplFileTreeNode node = makeTreeByType(componentId, typeNodeMap, tplCode);

			if(node != null){
				result.add(node);
			}
		}

		for(ComponentTplFileTreeNode node : result){
			unionEmptyFolder(node);
		}
		return result;

	}

	private ComponentTplFileTreeNode makeTreeByType(String componentId, Map<String, ComponentTplFileTreeNode> filePathMap, TplCodeVO tplCode){

		String filePath = tplCode.getType();
		String[] folders = filePath.split("/");

		ComponentTplFileTreeNode resultNode = null;

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
				ComponentTplFileTreeNode typeNode = new ComponentTplFileTreeNode(tplCode.getTplSet(), currentPath);
				typeNode.setName(folder);
				filePathMap.put(currentPath, typeNode);

				//找到上级路径的节点
				ComponentTplFileTreeNode parentNode = filePathMap.get(parentPath);

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


		ComponentTplFileTreeNode codeTplNode = new ComponentTplFileTreeNode(componentId, tplCode);

		//如果不包含，则说明自己就是一个定级节点
		if(!filePathMap.containsKey(tplCode.getType())){
			resultNode = codeTplNode;
		}else {
			ComponentTplFileTreeNode folder = filePathMap.get(parentPath);
			folder.addChild(codeTplNode);
		}

		return resultNode;
	}

	private void unionEmptyFolder(ComponentTplFileTreeNode folder){
		if(folder.getName().contains("POM_BUSINESS")){
			System.out.println("POM_BUSINESS");
		}
		if(folder.isLeaf() || CollectionUtils.isEmpty(folder.getChildren())){
			return;
		}

		List<ComponentTplFileTreeNode> children = folder.getChildren();

		if(CollectionUtils.size(children) == 1 && !children.get(0).isLeaf()){
			ComponentTplFileTreeNode child = children.get(0);

			if(child.getName().contains("POM_BUSINESS")){
				System.out.println("POM_BUSINESS");
			}

			folder.setName(folder.getName() + "/" + child.getName());
			folder.setId(child.getId());
			folder.setType(child.getType());
			folder.setObjectId(child.getObjectId());
			folder.setIconCls(child.getIconCls());
			folder.getChildren().remove(child);
			if(CollectionUtils.isNotEmpty(child.getChildren())) {
				folder.getChildren().addAll(child.getChildren());
			}
			unionEmptyFolder(folder);
		}

		for(ComponentTplFileTreeNode child : children){
			unionEmptyFolder(child);
		}


	}








}