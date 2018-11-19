package net.aicoder.speedcloud.business.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 项目
 * @author icode
 */
@ApiModel(value = "修改项目使用的DTO")
public class ProjectEditDto {


	/**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;


	/**类型*/
	@ApiModelProperty(value = "类型", required = false)
	private String type;


	/**公开性*/
	@ApiModelProperty(value = "公开性", required = false)
	private String scope;


	/**描述*/
	@ApiModelProperty(value = "描述", required = false, notes = "")
	private String description;


	/**上级项目*/
	@ApiModelProperty(value = "上级项目", required = false, notes = "")
	private String parent;


	/**所属项目集*/
	@ApiModelProperty(value = "所属项目集", required = false, notes = "")
	private Long projectSet;



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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
