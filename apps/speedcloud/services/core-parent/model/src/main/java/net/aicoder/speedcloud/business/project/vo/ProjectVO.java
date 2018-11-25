package net.aicoder.speedcloud.business.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 项目的值对象
*/
@ApiModel(value = "展现项目的值对象")
@Setter @Getter
public class ProjectVO{

    @ApiModelProperty(value = "记录id")
    private String id;


    /**名称*/
    @ApiModelProperty(value = "名称")
    private String name;

    /**代码*/
    @ApiModelProperty(value = "代码")
    private String code;


    /**类型*/
    @ApiModelProperty(value = "类型")
    private String type;


    /**公开性*/
    @ApiModelProperty(value = "公开性")
    private String scope;


    /**描述*/
    @ApiModelProperty(value = "描述", notes = "")
    private String description;


    /**上级项目*/
    @ApiModelProperty(value = "上级项目", notes = "")
    private String parent;


    /**所属项目集*/
    @ApiModelProperty(value = "所属项目集", notes = "")
    private String projectSet;
    private ProjectSetVO projectSetVO;


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}