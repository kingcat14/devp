package net.aicoder.speedcloud.business.pipeline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.app.vo.CodeRepositoryVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 流水线代码库关联的值对象
*/
@ApiModel(value = "展现流水线代码库关联的值对象")
public class PipelineCodeRepositoryRelationVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**代码库*/
    @ApiModelProperty(value = "代码库")
    private Long codeRepository;
    private CodeRepositoryVO codeRepositoryVO;


    /**流水线*/
    @ApiModelProperty(value = "流水线")
    private Long pipeline;
    private PipelineVO pipelineVO;


    @ApiModelProperty(value = "提交代码触发流水线")
    private Boolean autoStart;


    public Long getCodeRepository(){
        return codeRepository;
    }
    public void setCodeRepository(Long codeRepository) {
        this.codeRepository = codeRepository;
    }
    public CodeRepositoryVO getCodeRepositoryVO(){
        return codeRepositoryVO;
    }
    public void setCodeRepositoryVO(CodeRepositoryVO codeRepositoryVO) {
        this.codeRepositoryVO = codeRepositoryVO;
    }

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

    public Boolean getAutoStart(){
        return autoStart;
    }
    public void setAutoStart(Boolean autoStart) {
        this.autoStart = autoStart;
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