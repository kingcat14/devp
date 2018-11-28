package net.aicoder.speedcloud.console.business.icode.project.controller;


import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.console.business.icode.domainboard.controller.vo.DomainModelTreeNode;
import net.aicoder.speedcloud.console.business.icode.project.service.ComponentRibbonService;
import net.aicoder.speedcloud.console.business.icode.project.service.ProductRibbonService;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ProductCondition;
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
@RequestMapping(value = "/icode/componentboard/tree")
@Slf4j
public class ComponentlTreeController {


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

		ComponentCondition condition = new ComponentCondition();
		condition.setProduct(productId);

		PageSearchRequest request = new PageSearchRequest();
		request.setPage(0);
		request.setLimit(Integer.MAX_VALUE);
		request.setSearchCondition(condition);

		List<ComponentVO> list = componentRibbonService.list(request).getContent();

		List<DomainModelTreeNode> result = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(list)){
			list.stream().forEach((productVO)->{
				DomainModelTreeNode node = new DomainModelTreeNode(productVO);
				node.setLeaf(true);
				result.add(node);
			});
		}

		return result;
	}

	@Autowired
	private ProductRibbonService productRibbonService;

	@Autowired
	private ComponentRibbonService componentRibbonService;


}