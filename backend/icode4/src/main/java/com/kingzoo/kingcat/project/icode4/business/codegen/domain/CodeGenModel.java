package com.kingzoo.kingcat.project.icode4.business.codegen.domain;

import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplCode;
import freemarker.template.TemplateMethodModelEx;

/**
 * @author gonghongrui on 2017/11/16.
 */
public class CodeGenModel<T extends ModelConfig> {

	/**
	 * 生成文件目录
	 */
	private String path;
	/**
	 * 模板文件
	 */
	private TplCode tplCode;

	/**模块*/
	private ModuleConfig module;

	/**项目*/
	private SystemConfig system;

	/**工程（开发工程）*/
	private ProjectConfig project;

	/**模型*/
	private T model;

	private TemplateMethodModelEx improvedNamingStrategy;


	public String getModelType(){
		return model.getModelType();
	}

	public T getModel() {
		return model;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public TplCode getTplCode() {
		return tplCode;
	}

	public void setTplCode(TplCode tplCode) {
		this.tplCode = tplCode;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public ProjectConfig getProject() {
		return project;
	}

	public void setProject(ProjectConfig project) {
		this.project = project;
	}

	public ModuleConfig getModule() {
		return module;
	}

	public void setModule(ModuleConfig module) {
		this.module = module;
	}

	public SystemConfig getSystem() {
		return system;
	}

	public void setSystem(SystemConfig system) {
		this.system = system;
	}



	public TemplateMethodModelEx getImprovedNamingStrategy() {
		return improvedNamingStrategy;
	}

	public void setImprovedNamingStrategy(TemplateMethodModelEx improvedNamingStrategy) {
		this.improvedNamingStrategy = improvedNamingStrategy;
	}
}
