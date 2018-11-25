package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 应用
 * @author icode
 */
@ApiModel(value = "新增应用使用的DTO")
public class AppBaseInfoAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;

    /**应用类型*/
	@ApiModelProperty(value = "应用类型", required = false)
	private String type;

    /**状态*/
	@ApiModelProperty(value = "状态", required = false)
	private String status;

    /**描述*/
	@ApiModelProperty(value = "描述", required = false)
	private String description;

    /**注册时间*/
	@ApiModelProperty(value = "注册时间", required = false)
	private String registTime;

    /**所属项目*/
	@ApiModelProperty(value = "所属项目", required = false)
	private String project;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

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

	public String getProject(){
        return project;
    }
    public void setProject(String project) {
        this.project = project;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
