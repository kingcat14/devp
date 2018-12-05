package net.aicoder.speedcloud.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用类型的值对象
*/
@ApiModel(value = "展现应用类型的值对象")
@Setter @Getter
public class ApplicationTypeVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "排序")
    private Integer idx;


    @ApiModelProperty(value = "代码")
    private String code;


    @ApiModelProperty(value = "名称")
    private String name;


    @ApiModelProperty(value = "种类")
    private String category;


    @ApiModelProperty(value = "默认图标")
    private String icon;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}