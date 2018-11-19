package net.aicoder.speedcloud.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用的值对象
*/
@ApiModel(value = "展现应用的值对象")
public class AppBaseInfoVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**名称*/
    @ApiModelProperty(value = "名称")
    private String name;


    /**应用类型*/
    @ApiModelProperty(value = "应用类型")
    private String type;


    /**状态*/
    @ApiModelProperty(value = "状态")
    private String status;


    /**描述*/
    @ApiModelProperty(value = "描述")
    private String description;


    /**注册时间*/
    @ApiModelProperty(value = "注册时间")
    private String registTime;


    /**所属项目*/
    @ApiModelProperty(value = "所属项目")
    private Long project;
    private ProjectVO projectVO;


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

    public String getStatus(){
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegistTime(){
        return registTime;
    }
    public void setRegistTime(String registTime) {
        this.registTime = registTime;
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