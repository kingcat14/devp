package net.aicoder.speedcloud.business.config.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 环境级别的值对象
*/
@ApiModel(value = "展现环境级别的值对象")
@Setter @Getter
public class EnvLevelVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "名称")
    private String name;


    @ApiModelProperty(value = "代码")
    private String code;


    @ApiModelProperty(value = "类型", notes = "环境类型：生产环境、测试环境（开发也是测试环境）")
    private String type;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}