package net.aicoder.speedcloud.business.pipeline.exec.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


/**
 * 运行计划
 * @author icode
 */
@ApiModel(value = "新增运行计划使用的DTO")
public class PipelineExecNodeCustomAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**编号*/
	@ApiModelProperty(value = "编号", required = false)
	private String code;

    /**运行主体*/
	@ApiModelProperty(value = "运行主体Id", required = false, notes = "")
	private Long nodeId;

    /**运行类型*/
	@ApiModelProperty(value = "运行类型", required = false, notes = "流水线、任务")
	private String nodeType;

	private List<PipelineExecNodeCustomAddDto> subNodeList;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public List<PipelineExecNodeCustomAddDto> getSubNodeList() {
		return subNodeList;
	}
	public void setSubNodeList(List<PipelineExecNodeCustomAddDto> subNodeList) {
		this.subNodeList = subNodeList;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
