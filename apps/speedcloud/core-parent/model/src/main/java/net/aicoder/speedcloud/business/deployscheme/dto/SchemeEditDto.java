package net.aicoder.speedcloud.business.deployscheme.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 部署方案
 * @author icode
 */
@ApiModel(value = "修改部署方案使用的DTO")
public class SchemeEditDto {


	/**方案名称*/
	@ApiModelProperty(value = "方案名称", required = false, notes = "[方案名称]")
	private String name;


	/**方案代码*/
	@ApiModelProperty(value = "方案代码", required = false, notes = "[方案代码]")
	private String code;


	/**方案别名*/
	@ApiModelProperty(value = "方案别名", required = false, notes = "[方案别名]")
	private String alias;


	/**方案描述*/
	@ApiModelProperty(value = "方案描述", required = false, notes = "[方案描述]")
	private String description;


	/**方案类型*/
	@ApiModelProperty(value = "方案类型", required = false, notes = "[类型]-开发,测试,验证,生产")
	private String type;


	/**版本*/
	@ApiModelProperty(value = "版本", required = false, notes = "[版本]")
	private String version;


	/**版本标识后缀*/
	@ApiModelProperty(value = "版本标识后缀", required = false, notes = "[版本标识后缀]")
	private String verPostfix;


	/**已生效*/
	@ApiModelProperty(value = "已生效", required = false, notes = "[状态]")
	private Boolean status;


	/**备注*/
	@ApiModelProperty(value = "备注", required = false, notes = "[备注]")
	private String notes;


	/**所属项目（产品）*/
	@ApiModelProperty(value = "所属项目（产品）", required = false, notes = "[所属项目]")
	private Long project;


	/**所属环境*/
	@ApiModelProperty(value = "所属环境", required = false, notes = "[所属环境]")
	private Long env;



	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public String getAlias(){
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public String getVersion(){
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}


	public String getVerPostfix(){
		return verPostfix;
	}
	public void setVerPostfix(String verPostfix) {
		this.verPostfix = verPostfix;
	}


	public Boolean getStatus(){
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}


	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}


	public Long getProject(){
        return project;
    }
    public void setProject(Long project) {
        this.project = project;
    }


	public Long getEnv(){
        return env;
    }
    public void setEnv(Long env) {
        this.env = env;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
