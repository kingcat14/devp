package net.aicoder.speedcloud.console.business.icode.codegen.domain;


import freemarker.template.TemplateMethodModelEx;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplCodeVO;

/**
 * @author gonghongrui on 2017/11/16.
 */
@Getter @Setter
public class CodeGenModel<T extends ModelConfig> {

	/**生成文件目录*/
	private String path;

	/**模板文件*/
	private TplCodeVO tplCode;

	/**项目*/
	private ComponentConfig system;

	/**领域*/
	private DomainConfig module;

	/**工程(组件)*/
	private ComponentConfig project;

	/**模型*/
	private T model;

	private TemplateMethodModelEx improvedNamingStrategy;

	public String getModelType(){
		return model.getModelType();
	}

	public T getModel() {
		return model;
	}
	public void setModel(T model) {
		this.model = model;
	}


	public TemplateMethodModelEx getImprovedNamingStrategy() {
		return improvedNamingStrategy;
	}

	public void setImprovedNamingStrategy(TemplateMethodModelEx improvedNamingStrategy) {
		this.improvedNamingStrategy = improvedNamingStrategy;
	}
}
