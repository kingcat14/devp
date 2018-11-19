package net.aicoder.speedcloud.business.pipeline.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询流水线代码库关联使用的DTO")
public class PipelineCodeRepositoryRelationCondition extends SaaSCondition{

    @ApiModelProperty(value = "代码库")
    private Long codeRepository;
    @ApiModelProperty(value = "流水线")
    private Long pipeline;
	@ApiModelProperty(value = "提交代码触发流水线")
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
