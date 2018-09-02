package net.aicoder.speedcloud.business.pipeline.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 流水线
 * @author icode
 */
@ApiModel(value = "新增流水线使用的DTO")
public class PipelineAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**流水线名称*/
	@ApiModelProperty(value = "流水线名称", required = false, notes = "流水线名称")
	private String name;

    /**类型*/
	@ApiModelProperty(value = "类型", required = false)
    private String type;

    /**所属产品*/
	@ApiModelProperty(value = "所属产品", required = false)
	private Long project;


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

	public Long getProject(){
        return project;
    }
    public void setProject(Long project) {
        this.project = project;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
