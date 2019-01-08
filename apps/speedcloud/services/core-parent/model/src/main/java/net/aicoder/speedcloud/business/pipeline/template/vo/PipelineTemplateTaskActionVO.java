package net.aicoder.speedcloud.business.pipeline.template.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.business.pipeline.task.vo.PipelineTaskActionTypeVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 操作模板的值对象
*/
@ApiModel(value = "展现操作模板的值对象")
@Setter @Getter
public class PipelineTemplateTaskActionVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**所属任务*/
    @ApiModelProperty(value = "所属任务")
    private String task;
    private PipelineTemplateTaskVO taskVO;


    @ApiModelProperty(value = "操作说明")
    private String memo;


    @ApiModelProperty(value = "操作名称")
    private String name;


    @ApiModelProperty(value = "执行顺序")
    private Integer execIndex;


    /**操作类型*/
    @ApiModelProperty(value = "操作类型")
    private String type;
    private PipelineTaskActionTypeVO typeVO;


    /**脚本内容*/
    @ApiModelProperty(value = "脚本内容", notes = "脚本内容")
    private String content;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}