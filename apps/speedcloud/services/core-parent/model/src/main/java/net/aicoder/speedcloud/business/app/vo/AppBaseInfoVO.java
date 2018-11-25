package net.aicoder.speedcloud.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用的值对象
*/
@ApiModel(value = "展现应用的值对象")
@Setter @Getter
public class AppBaseInfoVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**名称*/
    @ApiModelProperty(value = "名称")
    private String name;


    /**应用类型*/
    @ApiModelProperty(value = "应用类型")
    private String type;


    /**状态*/
    @ApiModelProperty(value = "状态")
    private String status;


    /**描述*/
    @ApiModelProperty(value = "描述")
    private String description;


    /**注册时间*/
    @ApiModelProperty(value = "注册时间")
    private String registTime;


    /**所属项目*/
    @ApiModelProperty(value = "所属项目")
    private String project;
    private ProjectVO projectVO;



    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}