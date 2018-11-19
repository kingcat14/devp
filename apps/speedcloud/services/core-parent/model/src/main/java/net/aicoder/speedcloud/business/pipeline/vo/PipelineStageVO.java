package net.aicoder.speedcloud.business.pipeline.vo;

import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


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


    /**执行方式*/
    @ApiModelProperty(value = "执行方式", notes = "并行、串行、")
    private String execMode;
    private SimpleConfigVO execModeVO;


    @ApiModelProperty(value = "执行排序")
    private Integer execOrder;

    @ApiModelProperty(value = "阶段包含的节点")
    private List<PipelineStageNodeVO> pipelineStageNodeVOList;


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

    public String getExecMode(){
        return execMode;
    }
    public void setExecMode(String execMode) {
        this.execMode = execMode;
    }
    public SimpleConfigVO getExecModeVO(){
        return execModeVO;
    }
    public void setExecModeVO(SimpleConfigVO execModeVO) {
        this.execModeVO = execModeVO;
    }

    public Integer getExecOrder(){
        return execOrder;
    }
    public void setExecOrder(Integer execOrder) {
        this.execOrder = execOrder;
    }

    public List<PipelineStageNodeVO> getPipelineStageNodeVOList() {
        return pipelineStageNodeVOList;
    }
    public void setPipelineStageNodeVOList(List<PipelineStageNodeVO> pipelineStageNodeVOList) {
        this.pipelineStageNodeVOList = pipelineStageNodeVOList;
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