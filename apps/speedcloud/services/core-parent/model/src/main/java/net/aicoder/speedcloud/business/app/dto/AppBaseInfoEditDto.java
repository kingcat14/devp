package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 应用（系统）
 * @author icode
 */
@ApiModel(value = "修改应用（系统）使用的DTO")
@Setter @Getter
public class AppBaseInfoEditDto {


	/**所属项目*/
	@ApiModelProperty(value = "所属项目", required = false)
	private String project;


	/**应用类型*/
	@ApiModelProperty(value = "应用类型", required = false)
	private String type;


	/**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;


	/**代码*/
	@ApiModelProperty(value = "代码", required = false)
	private String code;


	/**状态*/
	@ApiModelProperty(value = "状态", required = false)
	private String status;


	/**描述*/
	@ApiModelProperty(value = "描述", required = false)
	private String description;


	/**注册时间*/
	@ApiModelProperty(value = "注册时间", required = false)
	private String registTime;



	public String getProject(){
        return project;
    }
    public void setProject(String project) {
        this.project = project;
    }


	public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
