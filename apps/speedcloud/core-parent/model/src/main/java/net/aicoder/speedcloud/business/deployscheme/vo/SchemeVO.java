package net.aicoder.speedcloud.business.deployscheme.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 部署方案的值对象
*/
@ApiModel(value = "展现部署方案的值对象")
public class SchemeVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**方案名称*/
    @ApiModelProperty(value = "方案名称", notes = "[方案名称]")
    private String name;


    /**方案代码*/
    @ApiModelProperty(value = "方案代码", notes = "[方案代码]")
    private String code;


    /**方案别名*/
    @ApiModelProperty(value = "方案别名", notes = "[方案别名]")
    private String alias;


    /**方案描述*/
    @ApiModelProperty(value = "方案描述", notes = "[方案描述]")
    private String description;


    /**方案类型*/
    @ApiModelProperty(value = "方案类型", notes = "[类型]-开发,测试,验证,生产")
    private String type;


    @ApiModelProperty(value = "已生效", notes = "[状态]")
    private Boolean status;


    /**备注*/
    @ApiModelProperty(value = "备注", notes = "[备注]")
    private String notes;


    /**所属项目（产品）*/
    @ApiModelProperty(value = "所属项目（产品）", notes = "[所属项目]")
    private Long project;
    private ProjectVO projectVO;


    /**所属环境*/
    @ApiModelProperty(value = "所属环境", notes = "[所属环境]")
    private Long env;
    private AppEnvConfigVO envVO;


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
    public ProjectVO getProjectVO(){
        return projectVO;
    }
    public void setProjectVO(ProjectVO projectVO) {
        this.projectVO = projectVO;
    }

    public Long getEnv(){
        return env;
    }
    public void setEnv(Long env) {
        this.env = env;
    }
    public AppEnvConfigVO getEnvVO(){
        return envVO;
    }
    public void setEnvVO(AppEnvConfigVO envVO) {
        this.envVO = envVO;
    }


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}