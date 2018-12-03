package net.aicoder.speedcloud.console.business.icode.domainboard.controller;


import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.util.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.console.business.icode.domain.service.DomainRibbonService;
import net.aicoder.speedcloud.console.business.icode.domain.service.EntityActionParameterRibbonService;
import net.aicoder.speedcloud.console.business.icode.domain.service.EntityActionRibbonService;
import net.aicoder.speedcloud.console.business.icode.domain.service.EntityRibbonService;
import net.aicoder.speedcloud.console.business.icode.domainboard.controller.vo.DomainModelTreeNode;
import net.aicoder.speedcloud.console.business.icode.project.service.ComponentDomainRelationRibbonService;
import net.aicoder.speedcloud.console.business.icode.project.service.ComponentRibbonService;
import net.aicoder.speedcloud.console.business.icode.project.service.ProductRibbonService;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityCondition;
import net.aicoder.speedcloud.icode.business.domain.vo.DomainVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityVO;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ProductCondition;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentDomainRelationVO;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentVO;
import net.aicoder.speedcloud.icode.business.project.vo.ProductVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理领域对象
 */
@RestController
@RequestMapping(value = "/icode/domainBoard/domainTree")
@Slf4j
public class DomainModelTreeController {


	@Autowired
	private SaaSUtil saaSUtil;

	/**
	 * Method getChildNodes.
	 * @param type String
	 * @param id int
	 * @return ResponseEntity<String>
	 */
	@RequestMapping("/{id}")
	public List<DomainModelTreeNode> getChildNodes(
			@RequestParam(required=false,value="type") String type,
			@RequestParam(required=false,value="objectId", defaultValue = "-1") String objectId
	        ,@PathVariable(name="id") String nodeId){

		List<DomainModelTreeNode> result = new ArrayList<>();

		if(StringUtils.isEmpty(type)){
			result.addAll(this.findProductNode());
		}
		if(StringUtils.equals(DomainModelTreeNode.TYPE_PRODUCT, type)){
			result.addAll(this.findComponentNode(objectId));
		}
		if(StringUtils.equals(DomainModelTreeNode.TYPE_COMPONENT, type)){
			result.addAll(this.findTopModuleNode(objectId));
		}

		if(StringUtils.equals(DomainModelTreeNode.TYPE_MODULE, type)){
			result.addAll(this.findSubModuleNode(objectId));
			result.addAll(this.findEntityNode(objectId));
		}
//
		if(StringUtils.equals(DomainModelTreeNode.TYPE_ENTITY, type)){
			result.addAll(this.findActionNode(nodeId));
		}
//
		if(StringUtils.equals(DomainModelTreeNode.TYPE_ENTITY_ACTION, type)){
			result.addAll(this.findParameterNode(nodeId));
		}

		return result;
	}

	/**
	 * 查找所有产品
	 * @return
	 */
	private List<DomainModelTreeNode> findProductNode(){

		Long tenantId = saaSUtil.getAccount().getTid();

		ProductCondition condition = new ProductCondition();
		condition.setTid(tenantId);

		PageSearchRequest request = new PageSearchRequest();
		request.setPage(0);
		request.setLimit(Integer.MAX_VALUE);
		request.setSearchCondition(condition);

		PageContent<ProductVO> pageContent =  productRibbonService.list(request);
		List<ProductVO> list = pageContent.getContent();

		List<DomainModelTreeNode> result = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(list)){
			list.stream().forEach((productVO)->{
				DomainModelTreeNode node = new DomainModelTreeNode(productVO);
				result.add(node);
			});
		}

		return result;
	}
	/**
	 * 查找所有系统
	 * @return
	 */
	private List<DomainModelTreeNode> findComponentNode(String productId){
		Long tenantId = saaSUtil.getAccount().getTid();

		ComponentCondition condition = new ComponentCondition();
		condition.setProduct(productId);
		condition.setTid(tenantId);
		PageSearchRequest request = new PageSearchRequest();
		request.setPage(0);
		request.setLimit(Integer.MAX_VALUE);
		request.setSearchCondition(condition);

		List<ComponentVO> list = componentRibbonService.list(request).getContent();

		List<DomainModelTreeNode> result = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(list)){
			list.stream().forEach((productVO)->{
				DomainModelTreeNode node = new DomainModelTreeNode(productVO);
				result.add(node);
			});
		}

		return result;
	}

	private List<DomainModelTreeNode> findTopModuleNode(String componentId){

		Long tenantId = saaSUtil.getAccount().getTid();

		ComponentDomainRelationCondition condition = new ComponentDomainRelationCondition();
		condition.setComponent(componentId);
		condition.setTid(tenantId);

		PageSearchRequest request = new PageSearchRequest();
		request.setPage(0);
		request.setLimit(Integer.MAX_VALUE);
		request.setSearchCondition(condition);

		List<ComponentDomainRelationVO> list = relationRibbonService.list(request).getContent();

		List<DomainModelTreeNode> result = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(list)){
			list.stream().forEach((relationVO)->{
				DomainModelTreeNode node = new DomainModelTreeNode(relationVO.getDomainVO());
				//因为领域和组件是多对多关系，所以这里不能再用领域的ID了
				node.setId(IDGenerator.uuid());
				result.add(node);
			});
		}

		return result;
	}

	private List<DomainModelTreeNode> findSubModuleNode(String domainId){
		Long tenantId = saaSUtil.getAccount().getTid();

		DomainCondition condition = new DomainCondition();
		condition.setParent(domainId);
		condition.setTid(tenantId);

		PageSearchRequest request = new PageSearchRequest();
		request.setPage(0);
		request.setLimit(Integer.MAX_VALUE);
		request.setSearchCondition(condition);

		List<DomainVO> list = domainRibbonService.list(request).getContent();

		List<DomainModelTreeNode> result = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(list)){
			list.stream().forEach((domainVO)->{
				DomainModelTreeNode node = new DomainModelTreeNode(domainVO);
				result.add(node);
			});
		}

		return result;
	}

	private List<DomainModelTreeNode> findEntityNode(String domainId){
		Long tenantId = saaSUtil.getAccount().getTid();

		EntityCondition condition = new EntityCondition();
		condition.setDomain(domainId);
		condition.setTid(tenantId);

		PageSearchRequest request = new PageSearchRequest();
		request.setPage(0);
		request.setLimit(Integer.MAX_VALUE);
		request.setSearchCondition(condition);

		List<EntityVO> list = entityRibbonService.list(request).getContent();

		List<DomainModelTreeNode> result = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(list)){
			list.stream().forEach((entityVO)->{
				DomainModelTreeNode node = new DomainModelTreeNode(entityVO);
				result.add(node);
			});
		}

		return result;
	}

	private List<DomainModelTreeNode> findActionNode(String entityId){
		Long tenantId = saaSUtil.getAccount().getTid();

		EntityActionCondition condition = new EntityActionCondition();
		condition.setEntity(entityId);
		condition.setTid(tenantId);

		PageSearchRequest request = new PageSearchRequest();
		request.setPage(0);
		request.setLimit(Integer.MAX_VALUE);
		request.setSearchCondition(condition);

		List<EntityActionVO> list = actionRibbonService.list(request).getContent();

		List<DomainModelTreeNode> result = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(list)){
			list.stream().forEach((actionVO)->{
				DomainModelTreeNode node = new DomainModelTreeNode(actionVO);
				result.add(node);
			});
		}

		return result;
	}

	private List<DomainModelTreeNode> findParameterNode(String actionId){

		List<DomainModelTreeNode> result = new ArrayList<>();

		EntityActionVO actionVO = actionRibbonService.find(actionId);

		String request = actionVO.getRequest();

		if(StringUtils.isNotEmpty(request)){
			EntityActionParameterVO resultVO = parameterRibbonService.find(request);
			DomainModelTreeNode node = new DomainModelTreeNode(resultVO, true);
			result.add(node);
		}


		String response = actionVO.getResponse();

		if(StringUtils.isNotEmpty(response)){
			EntityActionParameterVO responseVO = parameterRibbonService.find(response);
			DomainModelTreeNode node = new DomainModelTreeNode(responseVO, false);
			result.add(node);
		}


		return result;
	}


	@Autowired
	private ProductRibbonService productRibbonService;

	@Autowired
	private ComponentRibbonService componentRibbonService;

	@Autowired
	private ComponentDomainRelationRibbonService relationRibbonService;

	@Autowired
	private DomainRibbonService domainRibbonService;

	@Autowired
	private EntityRibbonService entityRibbonService;

	@Autowired
	private EntityActionRibbonService actionRibbonService;

	@Autowired
	private EntityActionParameterRibbonService parameterRibbonService;



}