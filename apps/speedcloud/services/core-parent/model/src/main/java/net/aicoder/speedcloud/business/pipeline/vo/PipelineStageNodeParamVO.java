package net.aicoder.speedcloud.business.pipeline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 阶段执行节点参数的值对象
*/
@ApiModel(value = "展现阶段执行节点参数的值对象")
public class PipelineStageNodeParamVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**参数名称*/
    @ApiModelProperty(value = "参数名称")
    private String name;


    /**参数值*/
    @ApiModelProperty(value = "参数值")
    private String value;


    @ApiModelProperty(value = "展现顺序")
    private Integer viewOrder;


    /**阶段执行节点*/
    @ApiModelProperty(value = "阶段执行节点", notes = "")
    private Long pipelineStageNode;
    private PipelineStageNodeVO pipelineStageNodeVO;


    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getValue(){
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public Integer getViewOrder(){
        return viewOrder;
    }
    public void setViewOrder(Integer viewOrder) {
        this.viewOrder = viewOrder;
    }

    public Long getPipelineStageNode(){
        return pipelineStageNode;
    }
    public void setPipelineStageNode(Long pipelineStageNode) {
        this.pipelineStageNode = pipelineStageNode;
    }
    public PipelineStageNodeVO getPipelineStageNodeVO(){
        return pipelineStageNodeVO;
    }
    public void setPipelineStageNodeVO(PipelineStageNodeVO pipelineStageNodeVO) {
        this.pipelineStageNodeVO = pipelineStageNodeVO;
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