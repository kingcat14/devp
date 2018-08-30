package com.kingzoo.kingcat.project.icode4.business.system.cogegen;

import com.kingzoo.kingcat.project.icode4.business.system.service.*;
import com.kingzoo.kingcat.project.icode4.util.ImprovedNamingStrategy;
import com.kingzoo.kingcat.project.icode4.business.codegen.domain.ModuleConfig;
import com.kingzoo.kingcat.project.icode4.business.codegen.domain.SystemConfig;
import com.kingzoo.kingcat.project.icode4.business.system.cogegen.entity.CodeGenDomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.cogegen.entity.DomainModelConfig;
import com.kingzoo.kingcat.project.icode4.business.system.cogegen.entity.DomainModelPropertyConfig;
import com.kingzoo.kingcat.project.icode4.business.system.domain.*;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import com.kingzoo.kingcat.project.icode4.business.system.vo.DomainModelPropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author gonghongrui on 2018/3/30.
 */
@Service
public class DomainModelConvertService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DomainModelConvertService.class);


	@Autowired
	private ModuleService moduleService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private DomainModelService domainModelService;
	@Autowired
	private DomainModelPropertyService domainModelPropertyService;
	@Autowired
	private DomainModelViewPropertyService domainModelViewPropertyService;

	public List<CodeGenDomainModel> convertToCommonCodeGenModelList(List<String> domainModelIdList){

		List<DomainModel> domainModelList = new ArrayList<>();

		//得到所有的对象
		DomainModel domainModel = null;
		for(String id:domainModelIdList){
			domainModel = domainModelService.find(id);
			if(domainModel !=null ) {
				domainModelList.add(domainModel);
			}else{
				LOGGER.error("给出的ID找不到DomainModel:{}", id);
			}
		}


		//将领域对象转换成生成代码用的配置对象
		List<CodeGenDomainModel> result = new ArrayList<>();
		for(DomainModel tableInfo : domainModelList){
			result.add(this.convertToCommonCodeGenModel(tableInfo));
		}


		return result;
	}

	public DomainModelConfig convertDomainModelConfig(DomainModel domainModel){

		/*准备模型配置*/
		DomainModelConfig domainModelConfig = this.convertDomainModelConfigWithoutProperty(domainModel);


		/*准备属性*/
		DomainModelPropertyCondition domainModelPropertyCondition = new DomainModelPropertyCondition();
		domainModelPropertyCondition.setDomainModelId(domainModel.getId());
		List<DomainModelProperty> domainModelPropertyList = domainModelPropertyService.findAll(domainModelPropertyCondition);
		List<DomainModelPropertyConfig> domainModelPropertyConfigList = this.convertDomainModelPropertyListToPropertyConfigList(domainModelPropertyList);

		//添加所有的属性的明细
		domainModelConfig.setAttribute(domainModelPropertyConfigList);

		return domainModelConfig;
	}


	/**
	 * 转换不带属性的配置
	 * @param domainModel
	 * @return
	 */
	public DomainModelConfig convertDomainModelConfigWithoutProperty(DomainModel domainModel){

		/*准备模型配置*/
		DomainModelConfig domainModelConfig = new DomainModelConfig();
		domainModelConfig.setId(domainModel.getId());
		domainModelConfig.setCode(domainModel.getCode());
		domainModelConfig.setName(domainModel.getName());
		domainModelConfig.setDescription(domainModel.getDescription());
		domainModelConfig.setTableName(ImprovedNamingStrategy.INSTANCE.tableName(domainModel.getName()));
		domainModelConfig.setRootModel(true);

		/*准备领域配置*/
		ModuleConfig moduleConfig = new ModuleConfig();
		Module module = moduleService.find(domainModel.getModuleId());
		Module topModule = moduleService.findTopModule(module.getId());
		moduleConfig.setCode(moduleService.findModuleCodePath(module.getId()));
		moduleConfig.setTopCode(topModule.getCode());
		moduleConfig.setName(module.getName());

		/*准备项目配置*/
		System system = systemService.find(topModule.getSystemId());
		SystemConfig projectConfig = new SystemConfig();
		projectConfig.setName(system.getName());
		projectConfig.setCode(system.getCode());
		projectConfig.setBasePackage(system.getBasePackage());

		domainModelConfig.setSystem(projectConfig);
		domainModelConfig.setModule(moduleConfig);

		return domainModelConfig;
	}

	/**
	 * 将DomainModel转换成CodeGenDomainModel
	 * @param domainModel
	 * @return
	 */
	public CodeGenDomainModel convertToCommonCodeGenModel(DomainModel domainModel){

		CodeGenDomainModel commonCodeGenModel = new CodeGenDomainModel();

		DomainModelConfig domainModelConfig = this.convertDomainModelConfig(domainModel);

		commonCodeGenModel.setModel(domainModelConfig);
		commonCodeGenModel.setModule(domainModelConfig.getModule());
		commonCodeGenModel.setSystem(domainModelConfig.getSystem());

		return commonCodeGenModel;

	}

	/**
	 * 将属性组转换成属性配置组
	 * @param propertyList
	 * @return
	 */
	private List<DomainModelPropertyConfig> convertDomainModelPropertyListToPropertyConfigList(List<DomainModelProperty> propertyList){
		List<DomainModelPropertyConfig> result = new ArrayList<>();

		DomainModelPropertyConfig tempDomainModelPropertyConfig;
		for(DomainModelProperty property : propertyList){
			tempDomainModelPropertyConfig = this.convertDomainModelPropertyToPropertyConfig(property);
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
	public DomainModelPropertyConfig convertDomainModelPropertyToPropertyConfig(DomainModelProperty domainModelProperty){

		DomainModelPropertyConfig domainModelPropertyConfig = new DomainModelPropertyConfig();

		BeanUtils.copyProperties(domainModelProperty, domainModelPropertyConfig);
		domainModelPropertyConfig.setDisplayName(domainModelProperty.getName());
		domainModelPropertyConfig.setRequired(!domainModelProperty.getNullable());
		domainModelPropertyConfig.setColumnName(ImprovedNamingStrategy.INSTANCE.propertyToColumnName(domainModelProperty.getName()));

		//如果是引用属性
		if(StringUtils.isNotEmpty(domainModelProperty.getRelatedDomainModelId())){

			//得到引用对象
			String referenceId = domainModelProperty.getRelatedDomainModelId();
			DomainModel reference = domainModelService.find(referenceId);

			//这里直接调用这个方法可能导致死循环(两个DomainModel的属性互相引用时)
			DomainModelConfig referenceConfig = this.convertDomainModelConfigWithoutProperty(reference);
			domainModelPropertyConfig.setReference(referenceConfig);

			//得到引用对象的主键
			DomainModelProperty referencePrimaryKey = domainModelPropertyService.findPrimaryKeyFormModel(referenceId);

			//属性的类型,跟引用属性的主键类型一样
			domainModelPropertyConfig.setType(referencePrimaryKey.getType());

			//引用属性
			String refPropertyId = domainModelProperty.getRelatedDomainModelPropertyId();

			if(refPropertyId != null && !"-1".equals(refPropertyId)){
				DomainModelProperty refProperty = domainModelPropertyService.find(refPropertyId);
				domainModelPropertyConfig.setReferencePropertyCode(refProperty.getCode());
				domainModelPropertyConfig.setReferencePropertyName(refProperty.getName());
			}

			domainModelPropertyConfig.setReferenceFlag(true);



			//类型是引用名
//			domainModelPropertyConfig.setType(reference.getName());
		}

		DomainModelViewProperty domainModelViewProperty = domainModelViewPropertyService.find(domainModelProperty.getId());
		if(domainModelViewProperty == null){
			domainModelViewProperty = domainModelViewPropertyService.create(domainModelProperty);
		}
		domainModelPropertyConfig.setViewConfig(domainModelViewProperty);

		return domainModelPropertyConfig;

	}


}
