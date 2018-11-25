package net.aicoder.speedcloud.business.pipeline.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询流水线使用的DTO")
public class PipelineCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "流水线名称", notes = "流水线名称")
	private String name;
	@ApiModelProperty(value = "类型")
	private String type;
    @ApiModelProperty(value = "所属产品")
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
