package net.aicoder.speedcloud.console.business.icode.codegen.service;


import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.bootstrap.platform.business.account.domain.Account;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.console.business.icode.codegen.domain.CodeGenModel;
import net.aicoder.speedcloud.console.business.icode.codegen.domain.ComponentConfig;
import net.aicoder.speedcloud.console.business.icode.codegen.domainmodel.cogegen.EntityConvertService;
import net.aicoder.speedcloud.console.business.icode.codegen.domainmodel.cogegen.ParameterConvertService;
import net.aicoder.speedcloud.console.business.icode.codegen.dto.CodeGenTaskAddDto;
import net.aicoder.speedcloud.console.business.icode.project.service.ComponentLocalLocationRibbonService;
import net.aicoder.speedcloud.console.business.icode.project.service.ComponentRibbonService;
import net.aicoder.speedcloud.console.business.icode.tplfile.service.TplCodeRibbonService;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentLocalLocationVO;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentVO;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplCodeVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 生成代码
 */
@Service("commonCodeGenConvertService")
@Slf4j
public class CommonCodeGenTaskConvertService {

	@Autowired
	private SaaSUtil saaSUtil;

    @Autowired
    private TplCodeRibbonService codeTplDao;

    @Autowired
	private ComponentRibbonService componentRibbonService;

    @Autowired
	private ComponentLocalLocationRibbonService locationRibbonService;

    @Autowired
    private EntityConvertService entityConvertService;

    @Autowired
	private ParameterConvertService parameterConvertService;

	@Transactional
	public CodeGenModel convert(CodeGenTaskAddDto taskVO){

		String modelComponentId = taskVO.getModelComponentId();
		CodeGenModel codeGenModel = null;

		//准备对象
		if(StringUtils.equals(taskVO.getModelType(), "ENTITY")) {
			codeGenModel = entityConvertService.convertToCommonCodeGenModel(taskVO.getModelId(), modelComponentId);
		}else {
			codeGenModel = parameterConvertService.convertToCodeGenModel(taskVO.getModelId(), modelComponentId);
		}
		//准备代码模板
		TplCodeVO tplCode = codeTplDao.find(taskVO.getTplCodeId());
		codeGenModel.setTplCode(tplCode);

		//准备目标工程
		ComponentVO targetComponent = componentRibbonService.find(taskVO.getTargetComponentId());
		ComponentConfig projectConfig = convertToProjectConfig(targetComponent);
		codeGenModel.setPath(projectConfig.getLocalPath());
		codeGenModel.setProject(projectConfig);

		return codeGenModel;

	}

	private ComponentConfig convertToProjectConfig(ComponentVO project){

		ComponentConfig projectConfig = new ComponentConfig();

		//projectConfig.setGroupCode();
		projectConfig.setBasePackage(project.getBasePackage());
		projectConfig.setCode(project.getCode());
		projectConfig.setName(project.getName());
		projectConfig.setNumber(project.getNumber());

		ComponentLocalLocationVO locationVO = findComponentLocationForAccount(project.getId());

        projectConfig.setLocalPath(locationVO.getLocation());

        return projectConfig;
	}

	private ComponentLocalLocationVO findComponentLocationForAccount(String componentId){
		Account account = saaSUtil.getAccount();
		return locationRibbonService.findByComponentIdAndTenantIdAndAccountId(componentId, account.getTid(), account.getId());
	}
}
