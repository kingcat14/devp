package com.kingzoo.kingcat.project.icode4.business.codegen.service;

import com.google.common.io.Files;
import com.kingzoo.kingcat.project.icode4.util.ImprovedNamingStrategyOfMethod;
import com.kingzoo.kingcat.project.icode4.business.codegen.domain.*;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplCode;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;

/**
 * 生成代码
 */
@Service("commonCodeGenTaskService")
public class CommonCodeGenTaskService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonCodeGenTaskService.class);

    private Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

    StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();


	@Transactional
	public void add(CodeGenModel codeGenModel){

		//TODO 这样做并发的时候有问题
		cfg.setTemplateLoader(stringTemplateLoader);


        String rootPath = codeGenModel.getPath();

		TplCode tplCode = codeGenModel.getTplCode();
		stringTemplateLoader.putTemplate(tplCode.getId()+"", tplCode.getContent());
        genCode(tplCode, codeGenModel);

	}

    /**
     * 生成代码(单个模板和对象)
     * @param tpl
     * @param model
     */
    public void genCode(TplCode tpl, CodeGenModel model) {

		stringTemplateLoader.putTemplate(tpl.getId()+"", tpl.getContent());

		model.setImprovedNamingStrategy(new ImprovedNamingStrategyOfMethod());

	    String fileName = this.getAbsoluteFileName(model);
	    File file = new File(fileName);
	    if(file.exists()){

		    if(!tpl.getOverridable()){
			    LOGGER.info("TPL({}, id:{}) can not override. ignor it.", tpl.getCode(), tpl.getId());
			    return;
		    }else {
			    file.delete();
		    }
	    }
	    try {

		    Files.createParentDirs(file);
		    file.createNewFile();
			LOGGER.info("create file:{}", file.getAbsolutePath());
	    } catch (IOException e) {
		    LOGGER.error("", e);
	    }

	    try(FileOutputStream fileOut = new FileOutputStream(file)) {

            Writer out = new OutputStreamWriter(fileOut, "UTF-8");

 	        Template temp = cfg.getTemplate(tpl.getId()+"");
	        temp.process(model, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            LOGGER.error("生成文件出错:"+tpl.getCode(), e);
        }
    }

    protected String getAbsoluteFileName(CodeGenModel commonCodeGenModel){

        SystemConfig system = commonCodeGenModel.getSystem();
        ModuleConfig module = commonCodeGenModel.getModule();
	    ModelConfig domainModel = commonCodeGenModel.getModel();
		ProjectConfig projectConfig = commonCodeGenModel.getProject();
	    String filePath = File.separator + commonCodeGenModel.getTplCode().getFilePath();

	    filePath = formatPathAndName(filePath, system, module, domainModel, projectConfig);

	    filePath = filePath.replace(".",File.separator);

	    filePath = commonCodeGenModel.getPath() + filePath;

	    String fileName = filePath + File.separator+ commonCodeGenModel.getTplCode().getFileName();
		fileName = formatPathAndName(fileName, system, module, domainModel, projectConfig);
//	    fileName = fileName.replaceAll("\\$\\{model.code\\}", StringUtils.capitalize(domainModel.getCode()));
//	    fileName = fileName.replaceAll("\\$\\{model.code\\?lower_case\\}", StringUtils.lowerCase(domainModel.getCode()));
//	    fileName = fileName.replaceAll("\\$\\{module.code\\}", StringUtils.uncapitalize(module.getCode()));
//	    fileName = fileName.replaceAll("\\$\\{system.code\\}", StringUtils.uncapitalize(system.getCode()));
//
//	    fileName = fileName.replaceAll("\\$\\{system.code\\?cap_first\\}", StringUtils.capitalize(system.getCode()));

	    return fileName;
    }

    private String formatPathAndName(String target, SystemConfig systemConfig, ModuleConfig moduleConfig, ModelConfig modelConfig, ProjectConfig projectConfig){

        String filePath = target;
		filePath = filePath.replaceAll("\\$\\{model.code\\?cap_first\\}", StringUtils.capitalize(modelConfig.getCode()));
		filePath = filePath.replaceAll("\\$\\{model.code\\?uncap_first\\}", StringUtils.uncapitalize(modelConfig.getCode()));
		filePath = filePath.replaceAll("\\$\\{model.code\\?lower_case\\}", StringUtils.lowerCase(modelConfig.getCode()));
		filePath = filePath.replaceAll("\\$\\{model.code\\}", modelConfig.getCode());

		filePath = filePath.replaceAll("\\$\\{module.code\\?cap_first\\}", StringUtils.capitalize(moduleConfig.getCode()));
		filePath = filePath.replaceAll("\\$\\{module.code\\?uncap_first\\}", StringUtils.uncapitalize(moduleConfig.getCode()));
		filePath = filePath.replaceAll("\\$\\{module.code\\?lower_case\\}", StringUtils.lowerCase(moduleConfig.getCode()));
		filePath = filePath.replaceAll("\\$\\{module.code\\}", moduleConfig.getCode());

		filePath = filePath.replaceAll("\\$\\{module.topCode\\?cap_first\\}", StringUtils.capitalize(moduleConfig.getTopCode()));
		filePath = filePath.replaceAll("\\$\\{module.topCode\\?uncap_first\\}", StringUtils.uncapitalize(moduleConfig.getTopCode()));
		filePath = filePath.replaceAll("\\$\\{module.topCode\\?lower_case\\}", StringUtils.lowerCase(moduleConfig.getTopCode()));
		filePath = filePath.replaceAll("\\$\\{module.topCode\\}", moduleConfig.getTopCode());

		filePath = filePath.replaceAll("\\$\\{system.code\\?uncap_first\\}", StringUtils.uncapitalize(systemConfig.getCode()));
		filePath = filePath.replaceAll("\\$\\{system.code\\?cap_first\\}", StringUtils.capitalize(systemConfig.getCode()));
		filePath = filePath.replaceAll("\\$\\{system.code\\?lower_case\\}", StringUtils.lowerCase(systemConfig.getCode()));
		filePath = filePath.replaceAll("\\$\\{system.code}", systemConfig.getCode());

		filePath = filePath.replaceAll("\\$\\{system.basePackage\\}", systemConfig.getBasePackage());

		filePath = filePath.replaceAll("\\$\\{project.code\\?uncap_first\\}", StringUtils.uncapitalize(projectConfig.getCode()));
		filePath = filePath.replaceAll("\\$\\{project.code\\?cap_first\\}", StringUtils.capitalize(projectConfig.getCode()));
		filePath = filePath.replaceAll("\\$\\{project.code\\?lower_case\\}", StringUtils.lowerCase(projectConfig.getCode()));
		filePath = filePath.replaceAll("\\$\\{project.code}", projectConfig.getCode());

		filePath = filePath.replaceAll("\\$\\{project.basePackage\\}", projectConfig.getBasePackage());
		return filePath;
	}

}
