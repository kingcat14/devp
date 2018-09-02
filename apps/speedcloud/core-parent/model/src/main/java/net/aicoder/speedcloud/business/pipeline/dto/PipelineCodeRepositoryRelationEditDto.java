package net.aicoder.speedcloud.business.pipeline.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 流水线代码库关联
 * @author icode
 */
@ApiModel(value = "修改流水线代码库关联使用的DTO")
public class PipelineCodeRepositoryRelationEditDto {


	/**代码库*/
	@ApiModelProperty(value = "代码库", required = false)
	private Long codeRepository;


	/**流水线*/
	@ApiModelProperty(value = "流水线", required = false)
	private Long pipeline;


	/**提交代码触发流水线*/
	@ApiModelProperty(value = "提交代码触发流水线", required = false)
	private Boolean autoStart;



	public Long getCodeRepository(){
        return codeRepository;
    }
    public void setCodeRepository(Long codeRepository) {
        this.codeRepository = codeRepository;
    }


	public Long getPipeline(){
        return pipeline;
    }
    public void setPipeline(Long pipeline) {
        this.pipeline = pipeline;
    }


	public Boolean getAutoStart(){
		return autoStart;
	}
	public void setAutoStart(Boolean autoStart) {
		this.autoStart = autoStart;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
