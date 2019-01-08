package net.aicoder.speedcloud.business.pipeline.template.vo;

import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.business.config.vo.PipelineTaskTypeVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 任务模板的值对象
*/
@ApiModel(value = "展现任务模板的值对象")
@Setter @Getter
public class PipelineTemplateTaskVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "任务名称")
    private String name;


    /**任务类型*/
    @ApiModelProperty(value = "任务类型")
    private String taskType;
    private PipelineTaskTypeVO taskTypeVO;


    /**执行计划*/
    @ApiModelProperty(value = "执行计划", notes = "手工，每日，每周")
    private String execType;
    private SimpleConfigVO execTypeVO;


    @ApiModelProperty(value = "执行开始时间", notes = "数据格式 HH:ss")
    private String taskStartTime;


    @ApiModelProperty(value = "执行日", notes = "1,2,3,4,5,6,7")
    private String taskDayOfWeeks;


    /**任务描述*/
    @ApiModelProperty(value = "任务描述")
    private String description;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}