package net.aicoder.speedcloud.business.pipeline.exec.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecNodeVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 运行实例节点参数的值对象
*/
@ApiModel(value = "展现运行实例节点参数的值对象")
public class PipelineExecNodeParamVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**所属节点*/
    @ApiModelProperty(value = "所属节点", notes = "")
    private Long node;
    private PipelineExecNodeVO nodeVO;


    /**属性名称*/
    @ApiModelProperty(value = "属性名称")
    private String name;


    /**属性代码*/
    @ApiModelProperty(value = "属性代码")
    private String code;


    /**属性值*/
    @ApiModelProperty(value = "属性值", notes = "执行时最终使用的值")
    private String value;


    @ApiModelProperty(value = "排序")
    private Integer viewOrder;


    /**类型*/
    @ApiModelProperty(value = "类型")
    private String type;


    public Long getNode(){
        return node;
    }
    public void setNode(Long node) {
        this.node = node;
    }
    public PipelineExecNodeVO getNodeVO(){
        return nodeVO;
    }
    public void setNodeVO(PipelineExecNodeVO nodeVO) {
        this.nodeVO = nodeVO;
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

    public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
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