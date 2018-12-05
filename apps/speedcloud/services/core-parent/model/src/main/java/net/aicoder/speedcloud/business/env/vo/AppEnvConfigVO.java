package net.aicoder.speedcloud.business.env.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.business.config.vo.EnvLevelVO;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 产品环境的值对象
*/
@ApiModel(value = "展现产品环境的值对象")
@Setter @Getter
public class AppEnvConfigVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**所属产品（项目）*/
    @ApiModelProperty(value = "所属产品（项目）")
    private String project;
    private ProjectVO projectVO;


    @ApiModelProperty(value = "环境名称")
    private String name;


    /**环境级别*/
    @ApiModelProperty(value = "环境级别")
    private String level;
    private EnvLevelVO levelVO;


    @ApiModelProperty(value = "顺序号")
    private Integer seq;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}