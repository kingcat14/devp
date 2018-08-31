package net.aicoder.speedcloud.business.pipeline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 阶段的值对象
*/
@ApiModel(value = "展现阶段的值对象")
public class PipelineStageVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**所属流水线*/
    @ApiModelProperty(value = "所属流水线")
    private Long pipeline;
    private PipelineVO pipelineVO;


    /**阶段名称*/
    @ApiModelProperty(value = "阶段名称")
    private String name;


    /**流转方式*/
    @ApiModelProperty(value = "流转方式", notes = "流转到下一阶段方式:自动、手动")
    private String flowType;
    private SimpleConfigVO flowTypeVO;


    public Long getPipeline(){
        return pipeline;
    }
    public void setPipeline(Long pipeline) {
        this.pipeline = pipeline;
    }
    public PipelineVO getPipelineVO(){
        return pipelineVO;
    }
    public void setPipelineVO(PipelineVO pipelineVO) {
        this.pipelineVO = pipelineVO;
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
    public SimpleConfigVO getFlowTypeVO(){
        return flowTypeVO;
    }
    public void setFlowTypeVO(SimpleConfigVO flowTypeVO) {
        this.flowTypeVO = flowTypeVO;
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