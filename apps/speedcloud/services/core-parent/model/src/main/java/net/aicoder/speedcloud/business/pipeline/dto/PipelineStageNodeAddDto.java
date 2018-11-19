package net.aicoder.speedcloud.business.pipeline.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


/**
 * 阶段执行节点
 * @author icode
 */
@ApiModel(value = "新增阶段执行节点使用的DTO")
public class PipelineStageNodeAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;

    /**阶段*/
	@ApiModelProperty(value = "阶段", required = false)
	private Long stage;

    /**节点类型*/
	@ApiModelProperty(value = "节点类型", required = false, notes = "Task和Pipeline")
    private String objType;

    /**节点对象*/
	@ApiModelProperty(value = "节点对象", required = false, notes = "节点关联的对象的ID")
	private Long objId;

    /**执行排序*/
	@ApiModelProperty(value = "执行排序", required = false)
	private Integer execOrder;

	/**节点的参数列表*/
	@ApiModelProperty(value = "节点的参数列表", required = false)
	private List<PipelineStageNodeParamAddDto> paramList;

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

	public Long getStage(){
        return stage;
    }
    public void setStage(Long stage) {
        this.stage = stage;
    }

    public String getObjType(){
        return objType;
    }
    public void setObjType(String objType) {
        this.objType = objType;
    }

	public Long getObjId(){
		return objId;
	}
	public void setObjId(Long objId) {
		this.objId = objId;
	}

	public Integer getExecOrder(){
		return execOrder;
	}
	public void setExecOrder(Integer execOrder) {
		this.execOrder = execOrder;
	}

	public List<PipelineStageNodeParamAddDto> getParamList() {
		return paramList;
	}
	public void setParamList(List<PipelineStageNodeParamAddDto> paramList) {
		this.paramList = paramList;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
