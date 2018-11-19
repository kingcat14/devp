package net.aicoder.speedcloud.console.business.pipeline.vo;

import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 阶段可选节点的值对象
*/
@ApiModel(value = "展现阶段可选节点的值对象")
public class PipelineStageSelectableNodeVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**名称*/
    @ApiModelProperty(value = "名称")
    private String name;


    /**节点类型*/
    @ApiModelProperty(value = "节点类型", notes = "Task和Pipeline")
    private String type;
    private SimpleConfigVO typeVO;


    /**所属产品*/
    @ApiModelProperty(value = "所属产品")
    private Long project;
    private ProjectVO projectVO;


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