package net.aicoder.speedcloud.icode.business.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 组件本地路径的值对象
*/
@ApiModel(value = "展现组件本地路径的值对象")
@Setter @Getter
public class ComponentLocalLocationVO {

    @ApiModelProperty(value = "记录id")
    private String id;

    /**组件*/
    @ApiModelProperty(value = "组件")
    private String component;
    private ComponentVO componentVO;


    @ApiModelProperty(value = "本地路径")
    private String location;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}