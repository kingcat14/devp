package net.aicoder.speedcloud.business.pipeline.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询阶段使用的DTO")
public class PipelineStageCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
    @ApiModelProperty(value = "所属流水线")
    private Long pipeline;
	@ApiModelProperty(value = "阶段名称")
	private String name;
	@ApiModelProperty(value = "流转方式", notes = "流转到下一阶段方式:自动、手动")
	private String flowType;
	@ApiModelProperty(value = "执行方式", notes = "并行、串行、")
	private String execMode;


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


	public String getExecMode(){
		return execMode;
	}
	public void setExecMode(String execMode) {
		this.execMode = execMode;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
