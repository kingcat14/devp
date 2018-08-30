package net.aicoder.speedcloud.business.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.project.vo.ProjectSetVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 项目的值对象
*/
@ApiModel(value = "展现项目的值对象")
public class ProjectVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**名称*/
    @ApiModelProperty(value = "名称")
    private String name;


    /**类型*/
    @ApiModelProperty(value = "类型")
    private String type;


    /**公开性*/
    @ApiModelProperty(value = "公开性")
    private String scope;


    /**描述*/
    @ApiModelProperty(value = "描述", notes = "")
    private String description;


    /**上级项目*/
    @ApiModelProperty(value = "上级项目", notes = "")
    private String parent;


    /**所属项目集*/
    @ApiModelProperty(value = "所属项目集", notes = "")
    private Long projectSet;
    private ProjectSetVO projectSetVO;


    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getScope(){
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getParent(){
        return parent;
    }
    public void setParent(String parent) {
        this.parent = parent;
    }

    public Long getProjectSet(){
        return projectSet;
    }
    public void setProjectSet(Long projectSet) {
        this.projectSet = projectSet;
    }
    public ProjectSetVO getProjectSetVO(){
        return projectSetVO;
    }
    public void setProjectSetVO(ProjectSetVO projectSetVO) {
        this.projectSetVO = projectSetVO;
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