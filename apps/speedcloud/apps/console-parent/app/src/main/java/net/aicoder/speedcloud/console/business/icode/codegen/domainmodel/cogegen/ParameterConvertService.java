package net.aicoder.speedcloud.console.business.icode.codegen.domainmodel.cogegen;


import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.console.business.icode.codegen.domain.CodeGenModel;
import net.aicoder.speedcloud.console.business.icode.codegen.domain.ComponentConfig;
import net.aicoder.speedcloud.console.business.icode.codegen.domain.DomainConfig;
import net.aicoder.speedcloud.console.business.icode.codegen.domainmodel.cogegen.entity.ValueConfig;
import net.aicoder.speedcloud.console.business.icode.domain.service.EntityActionParameterRibbonService;
import net.aicoder.speedcloud.console.business.icode.project.service.ComponentRibbonService;
import net.aicoder.speedcloud.icode.business.domain.vo.DomainVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterPropertyVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterVO;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghongrui on 2018/3/30.
 */
@Service
@Slf4j
public class ParameterConvertService {

	@Autowired
	private EntityActionParameterRibbonService entityService;

	@Autowired
	private ComponentRibbonService componentRibbonService;

	public CodeGenModel<ValueConfig> convertToCodeGenModel(String modelId, String componentId) {

		EntityActionParameterVO parameterVO = entityService.getDetail(modelId);

		return convertToCommonCodeGenModel(parameterVO, componentId);
	}

	/**
	 * 将DomainModel转换成CodeGenDomainModel
	 * @param parameter
	 * @return
	 */
	public CodeGenModel<ValueConfig> convertToCommonCodeGenModel(EntityActionParameterVO parameter, String componentId){

		CodeGenModel<ValueConfig> commonCodeGenModel = new CodeGenModel();

		ValueConfig entityConfig = this.convertToConfig(parameter, componentId);

		commonCodeGenModel.setModel(entityConfig);
		commonCodeGenModel.setModule(entityConfig.getModule());
		commonCodeGenModel.setSystem(entityConfig.getSystem());

		return commonCodeGenModel;

	}

	public ValueConfig convertToConfig(EntityActionParameterVO parameter, String componentId){

		/*准备模型配置*/
		ValueConfig entityConfig = this.convertDomainModelConfigWithoutProperty(parameter, componentId);


		/*准备属性*/
		List<EntityActionParameterPropertyVO> domainModelPropertyList = parameter.getPropertyList();

		//添加所有的属性的明细
		entityConfig.setAttribute(domainModelPropertyList);

		return entityConfig;
	}

	/**
	 * 转换不带属性的配置
	 * @param parameter
	 * @return
	 */
	public ValueConfig convertDomainModelConfigWithoutProperty(EntityActionParameterVO parameter, String componentId){

		/*准备模型配置*/
		ValueConfig entityConfig = new ValueConfig();
		entityConfig.setId(parameter.getId());
		entityConfig.setCode(parameter.getCode());
		entityConfig.setName(parameter.getName());
		entityConfig.setDescription(parameter.getDescription());
		entityConfig.setRootModel(true);

		/*准备领域配置*/
		DomainConfig moduleConfig = new DomainConfig();
		DomainVO module = parameter.getDomainVO();
		moduleConfig.setCode(module.getCodePath());
		moduleConfig.setTopCode(module.getCode());
		moduleConfig.setName(module.getName());

		/*准备项目配置*/
		ComponentVO system = componentRibbonService.find(componentId);
		ComponentConfig projectConfig = new ComponentConfig();
		projectConfig.setName(system.getName());
		projectConfig.setCode(system.getCode());
		projectConfig.setBasePackage(system.getBasePackage());

		entityConfig.setSystem(projectConfig);
		entityConfig.setModule(moduleConfig);

		return entityConfig;
	}


}
