package net.aicoder.speedcloud.console.business.icode.domain.controller;


import com.yunkang.saas.common.framework.web.controller.PageContent;
import net.aicoder.speedcloud.console.business.icode.domain.service.PropertyTypeRibbonService;
import net.aicoder.speedcloud.icode.business.domain.vo.PropertyTypeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gonghongrui on 2018/3/29.
 */
@RestController
@RequestMapping(value = "/icode/domain/referencePropertyType")
public class ReferencePropertyTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReferencePropertyTypeController.class);

	@Autowired
	private PropertyTypeRibbonService propertyTypeRibbonService;

	/**
	 * 查询属性类型列表
	 * @return
	 */
	@GetMapping
	public PageContent<PropertyTypeVO> list(@RequestParam(required=false, value = "component") String component,
											@RequestParam(required=false, value = "domainId") String domainId){


		//得到所有基本类型
		List<PropertyTypeVO> propertyTypeList = propertyTypeRibbonService.findAll();

		//得到所有的引用类型
		List<PropertyTypeVO> referenceTypeList = propertyTypeRibbonService.reference(component);

		List<PropertyTypeVO> voList = new ArrayList<>();
		voList.addAll(propertyTypeList);
		voList.addAll(referenceTypeList);



		PageContent<PropertyTypeVO> pageContent = new PageContent<>(voList, voList.size());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,voList.size());
		return pageContent;

	}

}
