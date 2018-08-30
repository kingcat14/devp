package com.kingzoo.kingcat.project.icode4.business.codegen.service;

import com.kingzoo.kingcat.project.icode4.business.codegen.domain.CodeGenModel;
import com.kingzoo.kingcat.project.icode4.business.codegen.domain.CodeGenRequest;
import com.kingzoo.kingcat.project.icode4.business.codegen.domain.Project;
import com.kingzoo.kingcat.project.icode4.business.codegen.domain.ProjectConfig;
import com.kingzoo.kingcat.project.icode4.business.system.cogegen.DomainModelConvertService;
import com.kingzoo.kingcat.project.icode4.business.system.cogegen.entity.CodeGenDomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.service.DomainModelService;
import com.kingzoo.kingcat.project.icode4.business.tplfile.dao.TplCodeDao;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 生成代码
 */
@Service("commonCodeGenConvertService")
public class CommonCodeGenTaskConvertService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonCodeGenTaskConvertService.class);

    @Autowired
    @Qualifier(value="tplCodeDao")
    private TplCodeDao codeTplDao;

    @Autowired
	private ProjectService projectService;

    @Autowired
	private DomainModelService domainModelService;

    @Autowired
    private DomainModelConvertService domainModelConvertService;


	@Transactional
	public CodeGenModel convert(CodeGenRequest codeGenRequest){

		TplCode tplCode = codeTplDao.findOne(codeGenRequest.getTplCodeId());

		Project project = projectService.find(codeGenRequest.getProjectId());
		ProjectConfig projectConfig = convertToProjectConfig(project);
		DomainModel domainModel = domainModelService.find(codeGenRequest.getModelId());

		CodeGenDomainModel codeGenModel = domainModelConvertService.convertToCommonCodeGenModel(domainModel);
		codeGenModel.setTplCode(tplCode);
        codeGenModel.setPath(projectConfig.getProjectPath());
		codeGenModel.setProject(projectConfig);
		return codeGenModel;


	}


	private ProjectConfig convertToProjectConfig(Project project){
		ProjectConfig projectConfig = new ProjectConfig();
		projectConfig.setBasePackage(project.getBasePackage());
		projectConfig.setCode(project.getCode());
		projectConfig.setName(project.getName());
		projectConfig.setNumber(project.getNumber());
        projectConfig.setProjectPath(project.getProjectPath());
		return projectConfig;
	}

}
