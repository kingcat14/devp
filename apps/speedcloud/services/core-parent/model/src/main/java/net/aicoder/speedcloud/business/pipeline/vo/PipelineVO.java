package net.aicoder.speedcloud.business.pipeline.vo;

import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


/**
* 流水线的值对象
*/
@ApiModel(value = "展现流水线的值对象")
public class PipelineVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**流水线名称*/
    @ApiModelProperty(value = "流水线名称", notes = "流水线名称")
    private String name;


    /**类型*/
    @ApiModelProperty(value = "类型")
    private String type;
    private SimpleConfigVO typeVO;


    /**所属产品*/
    @ApiModelProperty(value = "所属产品")
    private Long project;
    private ProjectVO projectVO;


    private List<PipelineStageVO> stageVOList;
    private List<PipelineParamVO> paramVOList;


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
    public SimpleConfigVO getTypeVO(){
        return typeVO;
    }
    public void setTypeVO(SimpleConfigVO typeVO) {
        this.typeVO = typeVO;
    }

    public Long getProject(){
        return project;
    }
    public void setProject(Long project) {
        this.project = project;
    }
    public ProjectVO getProjectVO(){
        return projectVO;
    }
    public void setProjectVO(ProjectVO projectVO) {
        this.projectVO = projectVO;
    }

    public List<PipelineStageVO> getStageVOList() {
        return stageVOList;
    }
    public void setStageVOList(List<PipelineStageVO> stageVOList) {
        this.stageVOList = stageVOList;
    }

    public List<PipelineParamVO> getParamVOList() {
        return paramVOList;
    }
    public void setParamVOList(List<PipelineParamVO> paramVOList) {
        this.paramVOList = paramVOList;
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