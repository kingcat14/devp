package net.aicoder.speedcloud.business.project.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询项目使用的DTO")
public class ProjectCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "类型")
	private String type;
	@ApiModelProperty(value = "公开性")
	private String scope;
	@ApiModelProperty(value = "描述", notes = "")
	private String description;
	@ApiModelProperty(value = "上级项目", notes = "")
	private String parent;
    @ApiModelProperty(value = "所属项目集", notes = "")
    private Long projectSet;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTidMin(){
		return tidMin;
	}
	public void setTidMin(Long tidMin) {
		this.tidMin = tidMin;
	}

	public Long getTidMax(){
		return tidMax;
	}
	public void setTidMax(Long tidMax) {
		this.tidMax = tidMax;
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
