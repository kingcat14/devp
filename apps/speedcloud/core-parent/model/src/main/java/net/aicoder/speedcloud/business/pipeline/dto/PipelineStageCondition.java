package net.aicoder.speedcloud.business.pipeline.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询阶段使用的DTO")
public class PipelineStageCondition extends SaaSCondition{

    @ApiModelProperty(value = "所属流水线")
    private Long pipeline;
	@ApiModelProperty(value = "阶段名称")
	private String name;
	@ApiModelProperty(value = "流转方式", notes = "流转到下一阶段方式:自动、手动")
	private String flowType;


    public Long getPipeline(){
        return pipeline;
    }
    public void setPipeline(Long pipeline) {
        this.pipeline = pipeline;
    }


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getFlowType(){
		return flowType;
	}
	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
