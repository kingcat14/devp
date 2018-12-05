package net.aicoder.speedcloud.console.business.icode.codegen.domainmodel.cogegen;


import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.console.business.icode.codegen.domain.ComponentConfig;
import net.aicoder.speedcloud.console.business.icode.codegen.domain.DomainConfig;
import net.aicoder.speedcloud.console.business.icode.codegen.domainmodel.cogegen.entity.CodeGenDomainModel;
import net.aicoder.speedcloud.console.business.icode.codegen.domainmodel.cogegen.entity.EntityConfig;
import net.aicoder.speedcloud.console.business.icode.codegen.domainmodel.cogegen.entity.EntityPropertyConfig;
import net.aicoder.speedcloud.console.business.icode.domain.service.EntityRibbonService;
import net.aicoder.speedcloud.console.business.icode.project.service.ComponentRibbonService;
import net.aicoder.speedcloud.console.business.icode.project.service.ProductRibbonService;
import net.aicoder.speedcloud.icode.business.domain.vo.DomainVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityPropertyVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityViewPropertyVO;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentVO;
import net.aicoder.speedcloud.icode.business.project.vo.ProductVO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gonghongrui on 2018/3/30.
 */
@Service
@Slf4j
public class EntityConvertService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityConvertService.class);


	@Autowired
	private ProductRibbonService productRibbonService;

	@Autowired
	private ComponentRibbonService systemService;

	@Autowired
	private EntityRibbonService entityService;

	public CodeGenDomainModel convertToCommonCodeGenModel(String entityId, String componentId) {

		EntityVO entityVO = entityService.getDetail(entityId);

		return convertToCommonCodeGenModel(entityVO, componentId);
	}

	/**
	 * 将DomainModel转换成CodeGenDomainModel
	 * @param domainModel
	 * @return
	 */
	public CodeGenDomainModel convertToCommonCodeGenModel(EntityVO domainModel, String componentId){

		CodeGenDomainModel commonCodeGenModel = new CodeGenDomainModel();

		EntityConfig entityConfig = this.convertDomainModelConfig(domainModel, componentId);

		commonCodeGenModel.setModel(entityConfig);
		commonCodeGenModel.setModule(entityConfig.getModule());
		commonCodeGenModel.setSystem(entityConfig.getSystem());

		return commonCodeGenModel;

	}


	public EntityConfig convertDomainModelConfig(EntityVO domainModel, String componentId){

		/*准备模型配置*/
		EntityConfig entityConfig = this.convertDomainModelConfigWithoutProperty(domainModel, componentId);


		/*准备属性*/
		List<EntityPropertyVO> domainModelPropertyList = domainModel.getPropertyList();
		List<EntityPropertyConfig> domainModelPropertyConfigList = this.convertDomainModelPropertyListToPropertyConfigList(domainModelPropertyList, componentId);
		entityConfig.setAttribute(domainModelPropertyConfigList);

		/*准备行为*/
		entityConfig.setActionList(domainModel.getActionList());


		return entityConfig;
	}


	/**
	 * 转换不带属性的配置
	 * @param domainModel
	 * @return
	 */
	public EntityConfig convertDomainModelConfigWithoutProperty(EntityVO domainModel, String componentId){

		/*准备模型配置*/
		EntityConfig entityConfig = new EntityConfig();
		entityConfig.setId(domainModel.getId());
		entityConfig.setCode(domainModel.getCode());
		entityConfig.setName(domainModel.getName());
		entityConfig.setDescription(domainModel.getDescription());
		entityConfig.setTableName(ImprovedNamingStrategy.INSTANCE.tableName(domainModel.getName()));
		entityConfig.setRootModel(true);

		/*准备领域配置*/
		DomainConfig moduleConfig = new DomainConfig();
		DomainVO module = domainModel.getDomainVO();
		moduleConfig.setCode(module.getCodePath());
		moduleConfig.setTopCode(module.getCode());
		moduleConfig.setName(module.getName());

		/*准备项目配置*/
		ComponentVO system = systemService.find(componentId);
		ComponentConfig projectConfig = new ComponentConfig();
		projectConfig.setName(system.getName());
		projectConfig.setCode(system.getCode());
		projectConfig.setBasePackage(system.getBasePackage());

		/*产品*/
		ProductVO productVO = productRibbonService.find(system.getProduct());
		projectConfig.setProductCode(productVO.getProductCode());

		entityConfig.setSystem(projectConfig);
		entityConfig.setModule(moduleConfig);

		return entityConfig;
	}


	/**
	 * 将属性组转换成属性配置组
	 * @param propertyList
	 * @return
	 */
	private List<EntityPropertyConfig> convertDomainModelPropertyListToPropertyConfigList(List<EntityPropertyVO> propertyList, String componentId){
		List<EntityPropertyConfig> result = new ArrayList<>();

		EntityPropertyConfig tempDomainModelPropertyConfig;
		for(EntityPropertyVO property : propertyList){
			tempDomainModelPropertyConfig = this.convertDomainModelPropertyToPropertyConfig(property, componentId);
			if(tempDomainModelPropertyConfig != null){
				result.add(tempDomainModelPropertyConfig);
			}
		}

		return result;

	}


	/**
	 * 将属性转换成属性配置，主要是处理引用属性
	 * @param domainModelProperty
	 * @return
	 */
	public EntityPropertyConfig convertDomainModelPropertyToPropertyConfig(EntityPropertyVO domainModelProperty, String componentId){

		EntityPropertyConfig domainModelPropertyConfig = new EntityPropertyConfig();

		BeanUtils.copyProperties(domainModelProperty, domainModelPropertyConfig);
		domainModelPropertyConfig.setDisplayName(domainModelProperty.getName());
		domainModelPropertyConfig.setRequired(!domainModelProperty.getNullable());
		domainModelPropertyConfig.setColumnName(ImprovedNamingStrategy.INSTANCE.propertyToColumnName(domainModelProperty.getName()));

		//如果是引用属性
		if(StringUtils.isNotEmpty(domainModelProperty.getRelatedEntityId())){

			//得到引用对象
			EntityVO reference = domainModelProperty.getRelatedEntity();

			//这里直接调用这个方法可能导致死循环(两个DomainModel的属性互相引用时)
			EntityConfig referenceConfig = this.convertDomainModelConfigWithoutProperty(reference, componentId);
			domainModelPropertyConfig.setReference(referenceConfig);

			//得到引用对象的主键
			String referencePrimaryKeyType = domainModelProperty.getRelatedEntityPrimaryKeyType();

			//属性的类型,跟引用属性的主键类型一样
			domainModelPropertyConfig.setType(referencePrimaryKeyType);

			//引用属性

			EntityPropertyVO refProperty = domainModelProperty.getRelatedEntityProperty();
			domainModelPropertyConfig.setReferencePropertyCode(refProperty.getCode());
			domainModelPropertyConfig.setReferencePropertyName(refProperty.getName());

			domainModelPropertyConfig.setReferenceFlag(true);


		}

		EntityViewPropertyVO domainModelViewProperty = domainModelProperty.getViewProperty();
		domainModelPropertyConfig.setViewConfig(domainModelViewProperty);

		return domainModelPropertyConfig;

	}


}
